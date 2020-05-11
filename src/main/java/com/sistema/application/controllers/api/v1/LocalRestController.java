package com.sistema.application.controllers.api.v1;

import com.sistema.application.models.LocalModel;
import com.sistema.application.services.ILocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("api/v1/local")
public class LocalRestController { 
     
     @Autowired
     @Qualifier("localService")
     private ILocalService localService;

     @GetMapping("distancia/{idLocal1}/{idLocal2}")
     ResponseEntity <Double> distancia(@PathVariable("idLocal1") long idLocal1,
                @PathVariable("idLocal2") long idLocal2){
          LocalModel local1 = localService.findByIdLocal(idLocal1);
          LocalModel local2 = localService.findByIdLocal(idLocal2);
          double distancia = local1.calcularDistancia(local2);
          return new ResponseEntity <Double>(distancia, HttpStatus.OK);
     }
} 