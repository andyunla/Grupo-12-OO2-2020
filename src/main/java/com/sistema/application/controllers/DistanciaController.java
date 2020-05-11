package com.sistema.application.controllers;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.services.ILocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("distancia")
public class DistanciaController {
     
     @Autowired
     @Qualifier("localService")
     private ILocalService localService;
     
     @GetMapping("")
	public String distancia(Model modelo) {
          modelo.addAttribute("locales", localService.getAll());
          return ViewRouteHelper.DISTANCIA_ROOT;
     }
}