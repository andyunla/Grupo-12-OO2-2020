package com.sistema.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IClienteService;
import com.sistema.application.services.implementations.UserService;


@Controller
@RequestMapping("cliente")
public class ClienteController {
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	@Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;
	@Autowired
    @Qualifier("userService")
    private UserService userService;
	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	@GetMapping("")
	public String clientes(Model modelo) {
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelo.addAttribute("currentUser", userDto);
		modelo.addAttribute("clientes", clienteService.getAllModel());
		modelo.addAttribute("cliente", new ClienteModel());
		return ViewRouteHelper.CLIENTE_ABM;
	}  
	
	@PostMapping("agregar")
	public String agregar(@Valid @ModelAttribute("cliente") ClienteModel nuevoCliente, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ViewRouteHelper.CLIENTE_ABM;
		}else {
			clienteService.insertOrUpdate(nuevoCliente);
		}
		return "redirect:/" + ViewRouteHelper.CLIENTE_ROOT;
	}
	
	@PostMapping("modificar")
	public String modificar(@Valid @ModelAttribute("cliente") ClienteModel clienteModificado, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ViewRouteHelper.CLIENTE_ABM;
		}else {
			clienteService.insertOrUpdate(clienteModificado);
		}
		return "redirect:/" + ViewRouteHelper.CLIENTE_ROOT;
	}
	
	@PostMapping("eliminar/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		boolean eliminado = clienteService.remove(id);
		redirectAttributes.addFlashAttribute("clienteEliminado", eliminado);
		return "redirect:/" + ViewRouteHelper.CLIENTE_ROOT;
	}
}
