package com.sistema.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.application.services.IProductoService;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ProductoModel;

@Controller
@PreAuthorize("hasRole('GERENTE')")
@RequestMapping("producto")
public class ProductoController {
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;

	@GetMapping("")
	public String productos(Model modelo) {
		modelo.addAttribute("productos", productoService.getAllModel());
		modelo.addAttribute("producto", new ProductoModel());
		return ViewRouteHelper.PRODUCTO_ABM;
	}
	
	@PostMapping("agregar")
	public String agregarProducto(@ModelAttribute("producto") ProductoModel nuevoProducto) {
		productoService.insertOrUpdate(nuevoProducto);
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
