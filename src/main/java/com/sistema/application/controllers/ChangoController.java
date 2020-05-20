package com.sistema.application.controllers;

import java.util.List;

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

     @GetMapping("/testA")   // ruta: http:localhost:8080/chango/testA
     public String deEntidadAModelo(){
          List<ChangoModel> modelos = changoService.getAllModel();
          for(ChangoModel cm: modelos){
               System.out.println(cm.toStringWithItems());
          }
          return ViewRouteHelper.HOME_ROOT;  //Devuelve al home porque tiene que devolver algo siempre
     }
}