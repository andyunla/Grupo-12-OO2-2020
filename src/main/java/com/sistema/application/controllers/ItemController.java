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
import com.sistema.application.services.IProductoService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@GetMapping("/nuevo")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEM_ABM);
		mAV.addObject("item", new ItemModel() );
		mAV.addObject("producto", productoService.getAll() );
		return mAV;
	}
	
	
	
	
}
