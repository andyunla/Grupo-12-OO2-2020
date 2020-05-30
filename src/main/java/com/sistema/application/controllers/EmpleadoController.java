package com.sistema.application.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.ILocalService;

@Controller
@RequestMapping("empleado")
public class EmpleadoController {
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	@Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;
	
	@GetMapping("")
	public String empleados(Model modelo) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto userDto = userConverter.entityToDto(userRepository.findByUsername(user.getUsername()));
		boolean isGerente = user.getAuthorities().contains(new SimpleGrantedAuthority(UtilHelper.ROLE_GERENTE));
		userDto.setTipoGerente(isGerente);
		modelo.addAttribute("currentUser", userDto);
		List<EmpleadoModel> empleados = empleadoService.getAllModel();
		for(EmpleadoModel e: empleados) {
			e.getLocal().setGerente(null);
		}
		modelo.addAttribute("empleados", empleados);
		List<LocalModel> locales = localService.getAllModel();
		for(LocalModel l: locales) {
			l.getGerente().setLocal(null);
		}
		modelo.addAttribute("locales", locales);
		modelo.addAttribute("empleado", new EmpleadoModel());
		return ViewRouteHelper.EMPLEADO_ABM;
	}
	
	@PostMapping("agregar")
	public String agregar(@Valid @ModelAttribute("empleado") EmpleadoModel nuevoEmpleado, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
        	return ViewRouteHelper.EMPLEADO_ROOT;
      	}
		empleadoService.insertOrUpdate(nuevoEmpleado);
		return "redirect:/" + ViewRouteHelper.EMPLEADO_ROOT;
	}
	
	@PostMapping("modificar")
	public String modificar(@ModelAttribute("empleado") EmpleadoModel empleadoModificado) {
		empleadoService.insertOrUpdate(empleadoModificado);
		return "redirect:/" + ViewRouteHelper.EMPLEADO_ROOT;
	}
	
	@PostMapping("eliminar/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		boolean eliminado = empleadoService.remove(id);
		redirectAttributes.addFlashAttribute("empleadoEliminado", eliminado);
		return "redirect:/" + ViewRouteHelper.EMPLEADO_ROOT;
	}
}
