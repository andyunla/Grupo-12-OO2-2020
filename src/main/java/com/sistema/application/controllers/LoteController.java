package com.sistema.application.controllers;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.services.ILoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
@RequestMapping("lote")  
public class LoteController {

     @Autowired
     @Qualifier("loteService")
     private ILoteService loteService;

     @GetMapping("")
	public String lotes(Model modelo) {
          modelo.addAttribute("lotes", loteService.getAllModels2());
          //modelo.addAttribute("lote", new LocalModel());
          return ViewRouteHelper.LOTE_ABM;
     }
}