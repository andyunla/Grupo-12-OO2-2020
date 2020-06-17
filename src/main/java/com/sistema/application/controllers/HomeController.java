package com.sistema.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.implementations.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	// GET Example: SERVER/index
	@GetMapping("/index")
	public ModelAndView index(Model modelo) {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		if (modelo.containsAttribute("changoEliminado")) {
               modelAndView.addObject("changoEliminado", modelo.getAttribute("changoEliminado"));
          }
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelAndView.addObject("currentUser", userDto);
		return modelAndView;
	}

	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.HOME_ROOT);
	}
}
