package com.sistema.application.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.application.dto.ChangePasswordForm;
import com.sistema.application.entities.UserRole;
import com.sistema.application.entities.User;
import com.sistema.application.exception.CustomeFieldValidationException;
import com.sistema.application.exception.UsernameOrIdNotFound;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ChangoModel;

@Controller
public class UserController {

	@Autowired
	@Qualifier("changoSesion")
	private ChangoModel changoSesion;

	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		changoSesion.clear();
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
