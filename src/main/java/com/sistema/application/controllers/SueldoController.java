package com.sistema.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.EmpleadoDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.entities.Empleado;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.IFacturaService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		/*
		//Recorro la lista de empleados y les calculo el sueldo
		for(EmpleadoModel emp: empleadoService.getAllModel() ) {
			emp.setSueldoBasico(localModel.calcularSueldo(emp) );
		}
		*/
		
		//Lo mismo del comentario de arriba pero trabaja con las entidades
		for(Empleado emp: empleadoService.getAll() ) {
			emp.setSueldoBasico(localModel.calcularSueldo(emp) );
		}
		
		//Mando atributos al modelo
		modelAndView.addObject("empleados", empleadoService.getAll() );
		
		
		//Muestro en pantalla
		return modelAndView;
	}
	
	
}
