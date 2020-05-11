package com.sistema.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ItemModel;
import com.sistema.application.services.implementations.ProductoService;
import com.sistema.application.services.implementations.ItemService;
import com.sistema.application.services.IProductoService;
import com.sistema.application.services.IItemService;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/item")
public class ItemController {
	
	//Atributos
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("itemService")
	private IItemService itemService;
	
	private int ultimoIdItem = 2;
	
	//Métodos
	@GetMapping("")
	public String items(Model modelo) {
		modelo.addAttribute("items", itemService.getAll());
		modelo.addAttribute("item", new ItemModel());
		return ViewRouteHelper.ITEM_ABM;
	}
	
	
	@PostMapping("agregar")
	public String agregar(@ModelAttribute("item") ItemModel nuevoItem) {
		ultimoIdItem++; // DEBUG: Cambiar lógica apropiada
		nuevoItem.setIdItem(ultimoIdItem);
		itemService.ingresarOActualizar(nuevoItem);
		return "redirect:/" + ViewRouteHelper.ITEM_ROOT;
	}
	
	@PostMapping("modificar")
	public String modificar(@ModelAttribute("item") ItemModel itemModificado) {
		itemService.ingresarOActualizar(itemModificado);
		return "redirect:/" + ViewRouteHelper.ITEM_ROOT;
	}
	
	@PostMapping("eliminar/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		boolean eliminado = itemService.eliminar(id);
		redirectAttributes.addFlashAttribute("itemEliminado", eliminado);
		return "redirect:/" + ViewRouteHelper.ITEM_ROOT;
	}
	
	/*
	 * Copiado del foro - Copiar es malo
	 * 
	@GetMapping("/nuevo")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEM_ABM);
		mAV.addObject("item", new ItemModel() );
		mAV.addObject("productos", productoService.getAll() );
		return mAV;
	}
	
	@PostMapping("/agregar")
	public RedirectView agregar(@ModelAttribute("item") ItemModel itemModel) {
		System.out.println("El id es: " + itemModel.getProductoModel().getIdProducto() );
		itemService.ingresarOActualizar(itemModel);
		return new RedirectView(ViewRouteHelper.ITEM_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView trar(@PathVariable("id") long id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEM_UPDATE);
		mAV.addObject("item", itemService.findById(id) );
		mAV.addObject("productos", productoService.getAll() );
		return mAV;
	}
	*/
	
	
}
