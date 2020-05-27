package com.sistema.application.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.IPedidoStockService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("chango")
public class ChangoController {

     @Autowired
     @Qualifier("productoService")
     private IProductoService productoService;

     @Autowired
     @Qualifier("localService")
     private ILocalService localService;

     @Autowired
     @Qualifier("localModel")
     private LocalModel localModel;

     @Autowired
     @Qualifier("itemService")
     private IItemService itemService;

     @Autowired
     @Qualifier("pedidoStockService")
     private IPedidoStockService pedidoStockService;

     @Autowired
     @Qualifier("changoService")
     private IChangoService changoService;

     /* NUEVO CHANGO */
     @GetMapping("")
     public String chango(Model modelo) {
          // Uso un local que será extraido del empleado logueado
          LocalModel localActual = localService.findByIdLocal(1);
          // Establezco los atributos de la instancia del local actual a la instancia componente
          localModel.setInstance(localActual);
          // Selecciono de todos los productos solo los que tienen por lo menos 1 en stock en este local 
          List<ProductoModel> productosConStock = new ArrayList<ProductoModel>();
          for (ProductoModel p : productoService.getAllModel()) {
               if (localModel.validarStock(p, 1)) {
                    productosConStock.add(p);    
               }
          }
          // Se envía a la vista un chango nuevo y la lista de productos con stock del local
          ChangoModel nuevoChango = localModel.crearChango();
          modelo.addAttribute("productos", productosConStock);
          modelo.addAttribute("chango", nuevoChango);  
          return ViewRouteHelper.CHANGO_ROOT;
          //TODO Buscar donde se auto eliminarán aquellos changos guardados sin items
     }  
        
     // Agrega un nuevo item al chango, su cantidad será 1
     @PostMapping("nuevo-item/{idChango}/{idProducto}") 
     public ModelAndView agregar(@PathVariable("idChango") long idChango,
          @PathVariable("idProducto") long idProducto) 
     {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEM);
          // Verifico que el producto elegido no esté ya en un item del chango
          if( itemService.findByChangoAndProducto(idChango, idProducto) == null){
               ItemModel item = itemService.insertOrUpdate( 
                    new ItemModel(1, productoService.findByIdProducto(idProducto), changoService.findByIdChango(idChango) ));
               mAV.addObject("item", item);  
               mAV.setStatus(HttpStatus.CREATED);
          } else {
               mAV.setStatus(HttpStatus.NOT_ACCEPTABLE);
          }  
          return mAV;              
     }
 
     // Elimina un item de un chango abierto
     @PostMapping("eliminar-item/{idItem}")
     public ResponseEntity<String> eliminarItem(@PathVariable("idItem") long idItem) {
          if(itemService.remove(idItem)){
               return new ResponseEntity<String>(HttpStatus.OK);
          } else {
               return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

          }
          /* TODO:  
           * Al eliminar un item verificar primero si tiene un pedidoStock con este item de este chango,
           * de tenerlo eliminar el pedido del chango y luego eliminar el pedido
           */
     }
          
     // Agrega o resta la cantidad al item 
     @PostMapping("modificar-item/{idProducto}/{cantidad}")
     public ModelAndView modificar(@ModelAttribute("chango") ChangoModel chango,
               @PathVariable("idProducto") long idProducto, @PathVariable("cantidad") int cantidad) {
          //TODO: a determinar
          return null;
     }

     /* CHANGO ABIERTO: Vista para permitir retomar un chango creado pero aun sin pedido aprobado ni facturado */

     /* CHANGOS: Vista de la lista de changos abiertos y cerrados */

     /* CHANGO CERRADO: Vista de un chango cerrado, no permite modificar */
}