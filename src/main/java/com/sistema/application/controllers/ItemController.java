package com.sistema.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ItemModel;
import com.sistema.application.services.IItemService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@PreAuthorize("hasRole('EMPLEADO') or hasRole('GERENTE')")
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	@Qualifier("itemService")
	private IItemService itemService;
	

	//Métodos
	@GetMapping("")
	public String items(Model modelo) {
		modelo.addAttribute("items", itemService.getAll() );
		modelo.addAttribute("item", new ItemModel() );
		return ViewRouteHelper.ITEM_ABM;
	}
	
	@PostMapping("agregar")
	public String agregar(@ModelAttribute("item") ItemModel nuevoItem) {
		System.out.print("\n\n" + nuevoItem + "\n\n");
		return "redirect:/" + ViewRouteHelper.ITEM_ROOT;
	}
	
	@PostMapping("modificar")
	public String modificar(@ModelAttribute("item") ItemModel itemModificado) {
		System.out.print("\n\n" + itemModificado + "\n\n");
		return "redirect:/" + ViewRouteHelper.CLIENTE_ROOT;
	}
	
	@PostMapping("eliminar/{id}")
	public String eliminar(@PathVariable("idItem") long idItem, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("localEliminado", true);
        System.out.print("\n\n" + idItem + "\n\n");      // DEBUG: Cambiar lógica apropiada
		return "redirect:/" + ViewRouteHelper.CLIENTE_ROOT;
	}
	
}
