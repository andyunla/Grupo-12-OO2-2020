package com.sistema.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.application.helpers.ViewRouteHelper;

@Controller
public class UserController {  

	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/" + ViewRouteHelper.HOME_ROOT;
	}
	/*
	@GetMapping("/user/{username}")
	public String getUserDetails() {
		boolean eliminado = clienteService.remove(id);
		redirectAttributes.addFlashAttribute("clienteEliminado", eliminado);
		return "redirect:/" + ViewRouteHelper.CLIENTE_ROOT;
	}
	*/
}
