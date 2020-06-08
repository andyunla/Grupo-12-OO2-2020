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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import com.sistema.application.services.IProductoService;
import com.sistema.application.services.implementations.UserService;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IUserRepository;

@Controller
@RequestMapping("producto")
public class ProductoController {
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	@Autowired
    @Qualifier("userService")
    private UserService userService;

	@GetMapping("")
	public String productos(Model modelo) {
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelo.addAttribute("currentUser", userDto);
		modelo.addAttribute("productos", productoService.getAllModel());
		modelo.addAttribute("producto", new ProductoModel());
		return ViewRouteHelper.PRODUCTO_ABM;
	}
	
	@PostMapping("agregar")
	public String agregarProducto(@Valid @ModelAttribute("producto") ProductoModel nuevoProducto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return ViewRouteHelper.PRODUCTO_ABM;
		}else {
			productoService.insertOrUpdate(nuevoProducto);
		}
		
		return "redirect:/" + ViewRouteHelper.PRODUCTO_ROOT;
	}
	
	@PostMapping("modificar")
	public String modificarProducto(@Valid @ModelAttribute("producto") ProductoModel productoModificado, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return ViewRouteHelper.PRODUCTO_ABM;
		}else {
			productoService.insertOrUpdate(productoModificado);
		}
		
		return "redirect:/" + ViewRouteHelper.PRODUCTO_ROOT;
	}
	
	@PostMapping("eliminar/{idProducto}")
	public String eliminarProducto(@PathVariable("idProducto") long idProducto, RedirectAttributes redirectAttributes) {
		boolean eliminado = productoService.remove(idProducto);
		redirectAttributes.addFlashAttribute("productoEliminado", eliminado);
		return "redirect:/" + ViewRouteHelper.PRODUCTO_ROOT;
	}	
}
