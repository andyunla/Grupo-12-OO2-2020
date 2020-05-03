package com.sistema.application.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ProductoModel;

@Controller
@RequestMapping("productos")
public class ProductosController {
	
	//Lista de productos que simula los datos en la base de datos
	private List <ProductoModel> productos = new ArrayList <ProductoModel>( Arrays.asList(
			new ProductoModel(1, "Zapato", "De cuero", 1500, "40"),
			new ProductoModel(2, "Otro Zapato", "Zafa", 900, "42"))
			);
	private int lastId = 2;
	
	@GetMapping("")
	public String productos(Model modelo) {
		modelo.addAttribute("productos", productos);
		modelo.addAttribute("producto", new ProductoModel());
		return ViewRouteHelper.PRODUCTOS;
	}
	
	@PostMapping("agregar")
	public String agregarProducto(@ModelAttribute("producto") ProductoModel nuevoProducto) {
		lastId++;
		nuevoProducto.setId(lastId);
		productos.add(nuevoProducto);
        return "redirect:/productos";
	}
	
	@PostMapping("modificar")
	public String modificarProducto(@ModelAttribute("producto") ProductoModel productoModificado) {
		int i=0;
		boolean encontrado = false;
		while(i < productos.size() && !encontrado) {
			encontrado = productos.get(i).getId() == productoModificado.getId();
			i++;
		}
		if(encontrado) {
			productos.set(i-1, productoModificado);
		}	// En caso de no encontrarlo implementar otra cosa
        return "redirect:/productos";
	}
	
	@PostMapping("eliminar/{idProducto}")
	public String eliminarProducto(@PathVariable("idProducto") int idProducto) {
		int i=0;
		boolean encontrado = false;
		while(i < productos.size() && !encontrado) {
			encontrado = productos.get(i).getId() == idProducto;
			i++;
		}
		if(encontrado) {
			productos.remove(i-1);
		}	// En caso de no encontrarlo implementar otra cosa
        return "redirect:/productos";
	}	
}
