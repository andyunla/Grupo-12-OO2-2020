package com.sistema.application.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sistema.application.converters.ChangoConverter;
import com.sistema.application.entities.Chango;
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
     @Qualifier("changoConverter")
     private ChangoConverter changoConverter;

     @Autowired
     @Qualifier("productoService")
     private IProductoService productoService;

     // Test provisorio de conversión de las entidades a modelos
     @GetMapping("/toModels")   
     public String deEntidadAModelo(){
          for(ChangoModel cm: changoService.getAllModel()){
               System.out.println(cm.toStringWithItems());
          }
          return "cu/chango";  
     }

     // Test  provisoriode conversión de los modelos a entidades
     @GetMapping("/toEntities")   
     public String deModeloAEntidad(){
          for(ChangoModel cm: changoService.getAllModel()){
               Chango ce = changoConverter.modelToEntity(cm);
               System.out.println(ce.toString());
          }
          return "cu/chango";  
     }

     // Test  provisorio para probar los tests
     @GetMapping("")   
     public String root(){
          return "cu/chango";  
     } 

     // Test provisorio de guardar un nuevo chango sin items
     @PostMapping("crearvacio")
     public String nuevovacio(){
          LocalModel local = localService.getAllModel().get(1);
          ChangoModel nuevoChango = new ChangoModel(local);
          ChangoModel creado = changoService.insertOrUpdate(nuevoChango);
          System.out.println( creado.toStringWithItems() );
          return "cu/chango";
     }

     // Test provisorio de guardar un nuevo chango con items
     @PostMapping("crear")
     public String nuevo(){
          // Traigo un local para crear un chango
          LocalModel local = localService.getAllModel().get(1);
          ChangoModel nuevoChango = new ChangoModel(local);
          // Traigo todos los productos para agregar algunos al chango
          List <ProductoModel> productos = productoService.getAllModel();
          Set<ItemModel> items = new HashSet<ItemModel>();
          items.add(new ItemModel(10, productos.get(0), nuevoChango));
          items.add(new ItemModel(5, productos.get(1), nuevoChango));
          nuevoChango.setListaItems(items);
          // Agrego a la bd
          ChangoModel creado = changoService.insertOrUpdate(nuevoChango);
          System.out.println( creado.toStringWithItems() );
          return "cu/chango";
     }

     // Test provisorio de guardar un nuevo item 
     @PostMapping("item")
     public String nuevoItem(){
          System.out.println("Iniciado item");
          // Traigo un chango
          ChangoModel chango = changoService.getAllModel().get(0);
          // Traigo todos los productos para agregar algunos al chango
          List <ProductoModel> productos = productoService.getAllModel();
          ItemModel item = new ItemModel(14, productos.get(0), chango);
          ItemModel nuevo = itemService.insertOrUpdate(item);
          System.out.println(nuevo);
          return "cu/chango";
     }

     // Test provisorio de guardar items
     @PostMapping("items")
     public String nuevosItem(){
          System.out.println("Iniciado items");
          // Traigo un chango
          ChangoModel chango = changoService.getAllModel().get(0);
          // Traigo todos los productos para agregar algunos al chango
          List <ProductoModel> productos = productoService.getAllModel();
          ItemModel item = new ItemModel(14, productos.get(0), chango);
          ItemModel item2 = new ItemModel(7, productos.get(1), chango);
          Set<ItemModel> items = new HashSet<ItemModel>();
          items.add(item);
          items.add(item2);
          Set<ItemModel> nuevos = itemService.insertOrUpdateMany(items);
          System.out.println(nuevos);
          return "cu/chango";
     }
} 