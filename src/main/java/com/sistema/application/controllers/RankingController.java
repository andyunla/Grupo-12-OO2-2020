package com.sistema.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.ProductoRankingDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.LocalModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.IProductoService;
import com.sistema.application.services.implementations.UserService;
@Controller
@RequestMapping("ranking")
public class RankingController {
	@Autowired
    @Qualifier("userConverter")
    private UserConverter userConverter;
	@Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	@Autowired
    @Qualifier("userService")
    private UserService userService;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@GetMapping("")
	public ModelAndView productoRanking() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.RANKIG_ROOT);
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelAndView.addObject("currentUser", userDto);
		List<ProductoRankingDto> productoRanking = localService.ranking();
		modelAndView.addObject("listaProductos", productoRanking);
		return modelAndView;
	}
}
