package com.sistema.application.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sistema.application.dto.ProductoStockDto;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;
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
     
     @GetMapping("")
     public String chango(Model modelo) {
          //TODO: Usar un local que será extraido del empleado logueado
          LocalModel localActual = localService.findByIdLocal(1);    
          localModel.setInstance(localActual);
          // Selecciono de todos los productos solo los que tienen por lo menos 1 en stock en el local
          List<ProductoStockDto> productosConStock = new ArrayList<ProductoStockDto>();
          for (ProductoModel p : productoService.getAllModel()) {
		     int stock = localModel.calcularStockLocal(p);
               if ( stock > 0) {
                    productosConStock.add( new ProductoStockDto(p.getIdProducto(), p.getNombre(), p.getTalle(), p.getPrecio(), stock) );
               }
          }
          ChangoModel nuevoChango = localModel.crearChango();
          modelo.addAttribute("productos", productosConStock);
          modelo.addAttribute("chango", nuevoChango);
          return ViewRouteHelper.CHANGO_ROOT;
     }
         
     // Agrega un nuevo item al chango, su cantidad será 1
     @PostMapping("nuevo-item/{idChango}/{idProducto}")
     public ModelAndView agregarItem(@PathVariable("idChango") long idChango, @PathVariable("idProducto") long idProducto) {
          localModel.setInstance(localService.findByIdLocal(1));
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEM);
          // Verifico que el producto elegido no esté ya en un item del chango
          if (itemService.findByChangoAndProducto(idChango, idProducto) == null) {
               ItemModel newItem = new ItemModel(1, productoService.findByIdProducto(idProducto), changoService.findByIdChango(idChango));
               ItemModel item = itemService.insertOrUpdate(newItem);
               mAV.addObject("item", item);
               mAV.setStatus(HttpStatus.CREATED); 
               localModel.restarLote(item.getProductoModel(), 1);
          } else {
               mAV.setStatus(HttpStatus.NOT_ACCEPTABLE);
          }
          return mAV;
     }     

     // Elimina un item de un chango abierto
     @PostMapping("eliminar-item/{idItem}")
     public ResponseEntity<String> eliminarItem(@PathVariable("idItem") long idItem) {
          localModel.setInstance(localService.findByIdLocal(1));
          ItemModel item = itemService.findByIdItem(idItem);
          ProductoModel producto = item.getProductoModel();
          if (itemService.remove(idItem)) {
               localModel.devolverLote(producto, item.getCantidad());
               return new ResponseEntity<String>(HttpStatus.OK);
          } else {
               return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
          }
     }              

     // Cambia la cantidad de un item
     @PostMapping("modificar-item/{idItem}/{nuevaCantidad}")
     public ResponseEntity<String> modificar(@PathVariable("idItem") long idItem,
               @PathVariable("nuevaCantidad") int nuevaCantidad) {
          ItemModel item = itemService.findByIdItem(idItem);
          localModel.setInstance(localService.findByIdLocal(1));
          // Verifico si estoy agregando cantidad al item   
          if (item.getCantidad() < nuevaCantidad) {
               // Verifico si hay stock en el local
               if (localModel.validarStock(item.getProductoModel(), nuevaCantidad - item.getCantidad())) {
                    // Resto a los lotes del local la diferencia entre la nueva cantidad que se sumó
                    localModel.restarLote(item.getProductoModel(), nuevaCantidad - item.getCantidad());
               } else {
                    // No se realizan cambios y devuelvo el una respuesta con la cantidad actual del item
                    return ResponseEntity.badRequest().body(String.valueOf(item.getCantidad()));
               }      
          } else { 
               // Sumo a los lotes del local la diferencia entre la nueva cantidad que se restó
               localModel.devolverLote(item.getProductoModel(), item.getCantidad() - nuevaCantidad);
          }
          item.setCantidad(nuevaCantidad);
          itemService.insertOrUpdate(item);
          return new ResponseEntity<String>(HttpStatus.OK); 
     }

     @PostMapping("eliminar/{idChango}")
     public String eliminarChango(@PathVariable("idChango") long idChango) {
          // Traer items del chango, devolverlos a sus lotes y eliminarlos
          localModel.setInstance(localService.findByIdLocal(1));
          List<ItemModel> items = itemService.findByChango(idChango);
          for(ItemModel item: items) {
               localModel.devolverLote(item.getProductoModel(), item.getCantidad());
               itemService.remove(item.getIdItem());
          }
          // Eliminar chango
          changoService.remove(idChango);
          return ViewRouteHelper.HOME_ROOT;
     }
     /*
      * CHANGO ABIERTO: Vista para permitir retomar un chango creado pero aun sin
      * pedido aprobado ni facturado
      */

     /* CHANGOS: Vista de la lista de changos abiertos y cerrados */

     /* CHANGO CERRADO: Vista de un chango cerrado, no permite modificar */

     // TODO Buscar donde se auto eliminarán aquellos changos guardados sin items

}