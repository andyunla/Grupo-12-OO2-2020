package com.sistema.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import com.sistema.application.dto.ProductoRankingDto;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.LocalModel;
import com.sistema.application.services.IProductoService;

public class RankingController {
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	// Para que el model pueda ejecutar los services debe ser usado como una instancia de componente
	@Autowired
	private LocalModel localModel;
	
	@GetMapping("")
	public ModelAndView productoRanking() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LISTA_LOCALES_CERCANOS); //cambiar ruta
		List<ProductoRankingDto> productoRanking = localModel.ranking();	
		mAV.addObject("productoRanking", productoRanking);		
		return mAV;
	}
}
