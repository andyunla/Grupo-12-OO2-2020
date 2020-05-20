package com.sistema.application.controllers;

import com.sistema.application.converters.ChangoConverter;
import com.sistema.application.entities.Chango;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.services.IChangoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chango")
public class ChangoController {
     
     @Autowired
     @Qualifier("changoService")
     private IChangoService changoService;

     @Autowired
     @Qualifier("changoConverter")
     private ChangoConverter changoConverter;

     // Test provisorio de conversión de las entidades a modelos
     @GetMapping("/toModels")   
     public String deEntidadAModelo(){
          for(ChangoModel cm: changoService.getAllModel()){
               System.out.println(cm.toStringWithItems());
          }
          return ViewRouteHelper.HOME_ROOT;  
     }

     // Test  provisoriode conversión de los modelos a entidades
     @GetMapping("/toEntities")   
     public String deModeloAEntidad(){
          for(ChangoModel cm: changoService.getAllModel()){
               Chango ce = changoConverter.modelToEntity(cm);
               System.out.println(ce.toString());
          }
          return ViewRouteHelper.HOME_ROOT;  
     }
}