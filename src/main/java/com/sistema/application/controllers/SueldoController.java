package com.sistema.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.converters.LocalConverter;
import com.sistema.application.dto.EmpleadoDto;
import com.sistema.application.dto.LocalDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.funciones.Funciones;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.LocalModel;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.IFacturaService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.implementations.UserService;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sueldos")
public class SueldoController {

	@Autowired
    @Qualifier("localConverter")
    private LocalConverter localConverter;
	@Autowired
    @Qualifier("userService")
    private UserService userService;
	@Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;
	@Autowired
    @Qualifier("facturaService")
    private IFacturaService facturaService;
	@Autowired
    @Qualifier("localService")
    private ILocalService localService;
	
	//Converters
	@Autowired
    @Qualifier("empleadoConverter")
    private EmpleadoConverter empleadoConverter;

	@GetMapping("")
	public ModelAndView sueldo(Model modelo) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.SUELDO_ROOT);		
		UserDto userDto = userService.getCurrentUser();
		modelAndView.addObject("currentUser", userDto);
		List<EmpleadoDto> vendedores = new ArrayList<EmpleadoDto>();
		if(userDto.isTipoAdmin()) { // Agregamos los vendedores de todos los locales
			List<LocalModel> localesModel = localService.getAllModel();
			for(LocalModel local : localesModel) {
				vendedores.addAll(localService.calcularSueldos(local.getIdLocal(), LocalDate.now()));
			}
		} else { // Los vendedores del local actual
			vendedores = localService.calcularSueldos(userDto.getLocal().getIdLocal(), LocalDate.now());
		}
		modelAndView.addObject("empleados", vendedores);
		return modelAndView;
	}
	
	// Trae los sueldos dado una fecha del tipo aaaa-mm
	@GetMapping("traer/{fecha}")
	public ModelAndView traer(@PathVariable("fecha") String fecha) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.LISTA_SUELDOS);
		UserDto userDto = userService.getCurrentUser();
		List<EmpleadoDto> vendedores = new ArrayList<EmpleadoDto>();
		if(userDto.isTipoAdmin()) { // Agregamos los vendedores de todos los locales
			List<LocalModel> localesModel = localService.getAllModel();
			for(LocalModel local : localesModel) {
				vendedores.addAll(localService.calcularSueldos(local.getIdLocal(), Funciones.mesAFechaCompleta(fecha)));
			}
		} else { // Los vendedores del local actual
			vendedores = localService.calcularSueldos(userDto.getLocal().getIdLocal(), Funciones.mesAFechaCompleta(fecha));
		}
		modelAndView.addObject("empleados", vendedores);
		modelAndView.addObject("isAdmin", userDto.isTipoAdmin());
		return modelAndView;
	}

	// Lleva a la vista del recibo de sueldo segun legajo y mes y año
	@GetMapping("ver-recibo/{legajo}/{fecha}")
	public ModelAndView traerRecibo(@PathVariable("legajo") long legajo, @PathVariable("fecha") String fechaString) {
		ModelAndView mav = new ModelAndView(ViewRouteHelper.RECIBO);	
		UserDto userDto = userService.getCurrentUser();
		LocalDate fecha = Funciones.mesAFechaCompleta(fechaString);
		EmpleadoDto empleado = localService.calcularSueldo(empleadoService.findByLegajo(legajo), fecha);
		LocalModel local = localService.findByIdLocal(empleado.getIdLocal());
		mav.addObject("currentUser", userDto);
		mav.addObject("empleado", empleado);
		mav.addObject("mes", Funciones.traerMesEnLetrasEsp(fecha));  
		mav.addObject("anio", fecha.getYear());
		mav.addObject("local", local);
		return mav;
	}
}
