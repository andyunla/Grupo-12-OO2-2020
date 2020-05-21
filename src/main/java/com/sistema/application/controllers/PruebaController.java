package com.sistema.application.controllers;

import java.util.List;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// CONTROLADOR USADO A MODO DE TESTEAR 

@Controller
@RequestMapping("chango")
public class PruebaController {
     
     @Autowired
     @Qualifier("changoService")
     private IChangoService changoService;

     @Autowired
     @Qualifier("itemService")
     private IItemService itemService;

     @Autowired
     @Qualifier("localService")
     private ILocalService localService;

     @Autowired
     @Qualifier("productoService")
     private IProductoService productoService;

     @GetMapping("")
     public String index(){
          return "cu/chango";
     }
     // Test provisorio de conversión de las entidades a modelos
     @PostMapping("/crearchango")   
     public String crearChango(){
          LocalModel l = localService.findByIdLocal(1);
          ChangoModel c = new ChangoModel(l);
          ChangoModel nc = changoService.insertOrUpdate(c);
          System.out.println("\n\n" + nc);
          return "cu/chango";
     }

     @GetMapping("/traerchango")   
     public String traerChango(){
          ChangoModel c = changoService.findByIdChango(1);
          System.out.println("\n\n" + c);
          return "cu/chango";
     } 

     @GetMapping("/traeritems")   
     public String traerItems(){
          List<ItemModel> items = itemService.getAllModel();
          for(ItemModel item : items){
               System.out.println(item); 
          }
          return "cu/chango";
     }

     @PostMapping("/crearitem")   
     public String crearItem(){
          ChangoModel c = changoService.findByIdChango(1);
          ProductoModel p = productoService.findByIdProducto(1);
          ItemModel i = new ItemModel(12, p, c);
          ItemModel ni = itemService.insertOrUpdate(i);
          System.out.println(ni);
          return "cu/chango";
     }
}