package com.sistema.application.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.sistema.application.converters.LocalConverter;
import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.ChangoDetalleDto;
import com.sistema.application.dto.ProductoDisponibleDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.entities.Local;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.ILoteService;
import com.sistema.application.services.IProductoService;
import com.sistema.application.services.implementations.FacturaService;
import com.sistema.application.services.IClienteService;

import com.sistema.application.services.implementations.UserService;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("chango")
public class ChangoController {

     @Autowired
     @Qualifier("userRepository")
     private IUserRepository userRepository;

     @Autowired
     @Qualifier("productoService")
     private IProductoService productoService;

     @Autowired
     @Qualifier("itemService")
     private IItemService itemService;

     @Autowired
     @Qualifier("clienteService")
     private IClienteService clienteService;

     @Autowired
     @Qualifier("changoService")
     private IChangoService changoService;

     @Autowired
     @Qualifier("loteService")
     private ILoteService loteService;

     @Autowired
     @Qualifier("userService")
     private UserService userService;

     @Autowired
     @Qualifier("localService")
     private ILocalService localService;

     @Autowired
     @Qualifier("userConverter")
     private UserConverter userConverter;

     @Autowired
     @Qualifier("productoConverter")
     private ProductoConverter productoConverter;

     /* CAMBIAR!!!! */
     @Autowired
     @Qualifier("changoSesion")
     private ChangoModel changoSesion;

     @Autowired
     @Qualifier("facturaService")
     private FacturaService facturaService;

     /* CREACION DE UN CHANGO */
     @GetMapping("")
     public ModelAndView chango() {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHANGO);
          UserDto userDto = userService.getCurrentUser();
          mAV.addObject("currentUser", userDto);          
          // Verifico si ya hay un chango abierto en esta sesion y si hay lo redirecciona a su modificación
          if (changoSesion.hasInstance()) {
               mAV.setViewName("redirect:/chango/" + changoSesion.getIdChango());
               return mAV;
          }
          ChangoModel nuevoChango = changoService.insertOrUpdate( new ChangoModel(getLocal()) );
          changoSesion.setInstance(nuevoChango);  //D
          mAV.addObject("chango", nuevoChango);
          mAV.addObject("clientes", clienteService.getAllModel());
          mAV.addObject("cliente", new ClienteModel());
          return mAV;
     }

     /* MODIFICACIÓN DE UN CHANGO */
     @GetMapping("{idChango}")
     public ModelAndView editarChango(@PathVariable("idChango") long idChango) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHANGO);
          UserDto userDto = userService.getCurrentUser();
          mAV.addObject("currentUser", userDto);  
          ChangoModel chango = changoService.findByIdChango(idChango);
          // Verifico si el chango fue eliminado o no existe
          if (chango == null) {
               mAV.setViewName("error/404");
               return mAV;
          }
          // Verifico si el chango fue facturado o es un chango de otro local
          if (facturaService.findByChango(chango) != null || !chango.getLocal().equals( getLocal() )) {
               mAV.setViewName("error/403");
               return mAV;
          }
          changoSesion.setInstance(chango);
          mAV.addObject("chango", changoSesion);
          mAV.addObject("clientes", clienteService.getAllModel());
          mAV.addObject("cliente", new ClienteModel());
          return mAV;
     }

     /* TRAER PRODUCTOS DISPONIBLES */
     @GetMapping("/productos-disponibles/{idChango}")
     public ModelAndView traerProductosDisponibles(@PathVariable("idChango") long idChango) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTOS_DISPONIBLES);
          List<ProductoDisponibleDto> productosDisponibles = new ArrayList<ProductoDisponibleDto>();
          for (ProductoModel producto : productoService.getAllModel()) {
               int stock = loteService.calcularStock( producto, getLocal() );
               ItemModel item = itemService.findByChangoAndProducto(idChango, producto.getIdProducto());
               // Verifica si hay stock o si el producto está como item
               if (stock > 0 || item != null) {
                    // Si el producto está como item sumo su cantidad al stock que se visualizará
                    int stockTotal = stock + ((item != null) ? item.getCantidad() : 0);
                    productosDisponibles.add(productoConverter.modelToDTO(producto, stockTotal, stock, item != null));
               }
          }
          mAV.addObject("productos", productosDisponibles);
          return mAV;
     }

     /* TRAER ITEMS DEL CHANGO */
     @GetMapping("/items/{idChango}")
     public ModelAndView traerItems(@PathVariable("idChango") long idChango) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEMS);
          mAV.addObject("items", itemService.findByChango(idChango));
          return mAV;
     }

     // Agrega un nuevo item al chango, su cantidad inicial será 1
     @PostMapping("nuevo-item/{idChango}/{idProducto}")
     public ModelAndView agregarItem(@PathVariable("idChango") long idChango,
               @PathVariable("idProducto") long idProducto) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEMS);
          ProductoModel producto = productoService.findByIdProducto(idProducto);
          // Verifico que el producto elegido no esté ya en un item del chango y que haya
          // stock de 1
          if (itemService.findByChangoAndProducto(idChango, idProducto) == null
                    && loteService.verificarStock(producto, getLocal(), 1)) {
               ItemModel newItem = new ItemModel(1, producto, changoService.findByIdChango(idChango));
               ItemModel item = itemService.insertOrUpdate(newItem);
               mAV.addObject("items", new ItemModel[] { item });
               // Resta la cantidad agregada de los lotes
               loteService.consumirStock(getLocal(), item.getProductoModel(), 1);
               mAV.setStatus(HttpStatus.CREATED);
          } else {
               mAV.setStatus(HttpStatus.NOT_ACCEPTABLE);
          }
          return mAV;
     }

     // Elimina un item de un chango abierto
     @PostMapping("eliminar-item/{idItem}")
     public ResponseEntity<String> eliminarItem(@PathVariable("idItem") long idItem) {
          ItemModel item = itemService.findByIdItem(idItem);
          ProductoModel producto = item.getProductoModel();
          if (itemService.remove(idItem)) {
               // Devuelve la cantidad del item eliminado a el/los lote/s
               loteService.devolverStock(getLocal(), producto, item.getCantidad());
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
          // Verifico si estoy agregando cantidad al item
          if (item.getCantidad() < nuevaCantidad) {
               // Verifico si hay stock en el local
               if (loteService.verificarStock(item.getProductoModel(), getLocal(), nuevaCantidad - item.getCantidad())) {
                    // Resto a los lotes del local la cantidad que se sumó
                    loteService.consumirStock(getLocal(), item.getProductoModel(), nuevaCantidad - item.getCantidad());
               } else {
                    return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
               }
          } else {
               // Sumo a los lotes la cantidad que se restó
               loteService.devolverStock(getLocal(), item.getProductoModel(), item.getCantidad() - nuevaCantidad);
          }
          item.setCantidad(nuevaCantidad);
          itemService.insertOrUpdate(item);
          return new ResponseEntity<String>(HttpStatus.OK);
     }

     @GetMapping("stock/{idProducto}")
     public ResponseEntity<Integer> traerStock(@PathVariable("idProducto") long idProducto) {
          int stock = loteService.calcularStock(productoService.findByIdProducto(idProducto), getLocal());
          return new ResponseEntity<>(stock, HttpStatus.OK);
     }

     /* Elimina un chango y sus items */
     @PostMapping("cancelar/{idChango}")
     public ModelAndView cancelarChango(@PathVariable("idChango") long idChango,
               RedirectAttributes redirectAttributes) {
          ModelAndView mAV = new ModelAndView("redirect:/chango/todos");
          // Traer items del chango, devolverlos a sus lotes y eliminarlos
          List<ItemModel> items = itemService.findByChango(idChango);
          for (ItemModel item : items) {
               loteService.devolverStock(getLocal(), item.getProductoModel(), item.getCantidad());
               itemService.remove(item.getIdItem());
          }
          if (changoService.remove(idChango)) { 
               redirectAttributes.addFlashAttribute("changoEliminado", true);
               changoSesion.clear(); // Saca al chango de la sesion D
          }
          return mAV;
     }

     /* VISTA DE TODOS LOS CHANGOS CREADOS, DIFERENCIANDOLOS POR FACTURADOS O NO */
     @GetMapping("todos")
     public ModelAndView traerTodos(Model modelo) {
          // Devuelve una vista con todos los changos del local actual
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHANGOS);
          UserDto userDto = userService.getCurrentUser();
          mAV.addObject("currentUser", userDto); 
          // Agrega el objeto changoEliminado en caso de ser redireccionado tras cancelar
          // uno
          if (modelo.containsAttribute("changoEliminado")) {
               mAV.addObject("changoEliminado", modelo.getAttribute("changoEliminado"));
          }
          List<ChangoDetalleDto> changos = new ArrayList<ChangoDetalleDto>();
          for (ChangoModel chango : changoService.findByLocal(getLocal())) {
               // Llena los detalles que se pasaran a la vista de la lista de los changos
               chango.setListaItems(new HashSet<>(itemService.findByChango(chango.getIdChango())));
               int cantidadDeItems = chango.getListaItems().size();
               double total = chango.calcularTotalChango();
               boolean facturado = (facturaService.findByChango(chango) != null);
               changos.add(new ChangoDetalleDto(chango.getIdChango(), cantidadDeItems, total, facturado));
          }
          mAV.addObject("changos", changos);
          return mAV;
     }

     /* VISTA DE LOS ITEMS DE UN CHANGO */
     @GetMapping("ver/{idChango}")
     public ModelAndView verChango(@PathVariable("idChango") long idChango) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHANGO_FACTURADO);
          UserDto userDto = userService.getCurrentUser();
          mAV.addObject("currentUser", userDto); 
          ChangoModel chango = changoService.findByIdChango(idChango);
          // Verifica si el chango que se intenta acceder existe
          if (chango == null) {
               mAV.setViewName("error/404");
               return mAV;
          }
          // Verifica que se intenta ver sea del local del cual está logueado
          if (!chango.getLocal().equals(getLocal())) {
               mAV.setViewName("error/403");
               return mAV;
          }
          List<ItemModel> items = itemService.findByChango(idChango);
          chango.setListaItems(new HashSet<ItemModel>(items));
          mAV.addObject("chango", chango);
          mAV.addObject("items", items);
          return mAV;
     }

     // Devuelve el local del usuario
     public LocalModel getLocal() {
          UserDto userDto = userService.getCurrentUser();
          return localService.findByIdLocal(userDto.getLocal().getIdLocal());
     }
}
