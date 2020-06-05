package com.sistema.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.sistema.application.converters.UserConverter;
import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.dto.EmpleadoDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.LocalModel;
import com.sistema.application.entities.Empleado;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.IFacturaService;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sueldos")
public class SueldoController {

	@Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;	
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;
	
	@Autowired
    @Qualifier("facturaService")
    private IFacturaService facturaService;
	
	@Autowired
    @Qualifier("empleadoConverter")
    private EmpleadoConverter empleadoConverter;
	
	@Autowired
	private LocalModel localModel;
	
	@GetMapping("")
	public ModelAndView sueldo(Model modelo) {
		
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.SUELDO_ROOT);
		
		//Chequea que sea un gerente
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto userDto = userConverter.entityToDto(userRepository.findByUsername(user.getUsername()));
		boolean isGerente = user.getAuthorities().contains(new SimpleGrantedAuthority(UtilHelper.ROLE_GERENTE));
		userDto.setTipoGerente(isGerente);
		modelAndView.addObject("currentUser", userDto);

			
		List<Empleado> empleados = empleadoService.getAll();
		List<EmpleadoDto> vendedores = new ArrayList<EmpleadoDto>();

		int i = 0;
		//Calculo comisiones y sueldo final
		for (Empleado emp : empleados) {
				
			double sueldoFinal = localModel.calcularSueldo(emp);
			double comisionVentaCompleta = localModel.calcularComisionVentaCompleta(emp);
			double comisionVentaExterna = localModel.calcularComisionVentaExterna(emp);
			double comisionStockCedido = localModel.calcularComisionStockCedido(emp);
			
			
			if(!emp.isTipoGerente() && userDto.getIdLocal() == emp.getLocal().getIdLocal() ) {
				vendedores.add(empleadoConverter.entityToDto(emp) );
				vendedores.get(i).setSueldoFinal(sueldoFinal);
				vendedores.get(i).setComisionVentaCompleta(comisionVentaCompleta);
				vendedores.get(i).setComisionVentaExterna(comisionVentaExterna);
				vendedores.get(i).setComisionStockCedido(comisionStockCedido);
				i++;
			}
		}
		
		
		//Mando atributos al modelo
		modelAndView.addObject("empleados", vendedores );
		
		//Muestro en pantalla
		return modelAndView;
	}
	
	
}
