package com.sistema.application.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chango")
public class ChangoController {

     @Autowired  
     private IProductoService productoService;                         
  
     @Autowired             
     private ILocalService localService;     
  
     @Autowired                    
     private LocalModel localModel;   
         
     /* NUEVO CHANGO */
     @GetMapping("") 
     public String chango(Model modelo){
          // Uso un local que será extraido del empleado logueado 
          LocalModel localActual = localService.findByIdLocal(1);
          // seteo los atributos de la instancia del local actual a la instancia componente
          localModel.setInstance(localActual);
          // Traigo los productos y verifico uno por uno si tienen stock en el local
          // para pasarlos como opción a la vista
          List<ProductoModel> productosConStock = new ArrayList<ProductoModel>();
          for(ProductoModel p: productoService.getAllModel()){
               if(localModel.validarStock(p, 1)){
                    productosConStock.add(p);
               }
          }
          //Creo un nuevo chango: luego ver como automatizar la eliminación de los changos abiertos sin items
          ChangoModel nuevoChango = localModel.crearChango();
          modelo.addAttribute("productos", productosConStock);
          modelo.addAttribute("chango", nuevoChango); 
          return ViewRouteHelper.CHANGO_ROOT;
     }

     /* MODIFICAR ITEM: AGREGAR, SUMAR O RESTAR */

     /* CHANGO ABIERTO */

     /* CHANGOS ABIERTOS (LISTA) */ 
}     