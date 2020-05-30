package com.sistema.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.UtilHelper;
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

	@GetMapping("")
	public String productos(Model modelo) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto userDto = userConverter.entityToDto(userRepository.findByUsername(user.getUsername()));
		boolean isGerente = user.getAuthorities().contains(new SimpleGrantedAuthority(UtilHelper.ROLE_GERENTE));
		userDto.setTipoGerente(isGerente);
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
	public String modificarProducto(@ModelAttribute("producto") ProductoModel productoModificado) {
		productoService.insertOrUpdate(productoModificado);
		return "redirect:/" + ViewRouteHelper.PRODUCTO_ROOT;
	}
	
	@PostMapping("eliminar/{idProducto}")
	public String eliminarProducto(@PathVariable("idProducto") long idProducto, RedirectAttributes redirectAttributes) {
		boolean eliminado = productoService.remove(idProducto);
		redirectAttributes.addFlashAttribute("productoEliminado", eliminado);
		return "redirect:/" + ViewRouteHelper.PRODUCTO_ROOT;
	}	
}
