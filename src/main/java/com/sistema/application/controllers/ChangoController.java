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
     @Qualifier("localConverter")
     private LocalConverter localConverter;
     
     @Autowired
     @Qualifier("userService")
     private UserService userService;

     @Autowired
     @Qualifier("userConverter")
     private UserConverter userConverter;

     @Autowired
     @Qualifier("productoConverter")
     private ProductoConverter productoConverter;

     @Autowired
     @Qualifier("localModel")
     private LocalModel localModel;

     /* Instancia de changoModel que se mantiene abierta en la pestaña 'Chango' */
     /* Siempre se mantiene el ultimo chango que se está modificando o creando */
     /* Se cambia la instancia cuando se decide facturarlo, cancelarlo o guardarlo */
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
          setUserAndLocal(mAV);
          // Verifico si ya hay un chango abierto, si es así se redirecciona a la modificación
          if( changoSesion.hasInstance() ) {
               mAV.setViewName("redirect:/chango/" + changoSesion.getIdChango());
               return mAV;
          }   
          ChangoModel nuevoChango = localModel.crearChango();
          changoSesion.setInstance(nuevoChango);
          mAV.addObject("chango", nuevoChango);
          //mAV.addObject("items", new ArrayList<ItemModel>());
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
               int stock = loteService.calcularStock( producto, localModel );
               ItemModel item = itemService.findByChangoAndProducto(idChango, producto.getIdProducto());
               // Verifica si hay stock o si el producto está como item
               if (stock > 0 || item != null) {
                    // Si el producto está como item sumo su cantidad al stock que se visualizará
                    int stockTotal = stock  + ((item != null) ? item.getCantidad() : 0);
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
          

     /* MODIFICACIÓN DE UN CHANGO */
     @GetMapping("{idChango}")
     public ModelAndView editarChango(@PathVariable("idChango") long idChango) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHANGO);
          setUserAndLocal(mAV);
          ChangoModel chango = changoService.findByIdChango(idChango);
          // Verifico si el chango fue eliminado o no existe
          if(chango == null) {
               mAV.setViewName("error/404");
               return mAV;
          }
          // Verifico si el chango fue facturado o es un chango de otro local
          if(facturaService.findByChango(chango) != null || !chango.getLocal().equals(localModel) ){
               mAV.setViewName("error/403");
               return mAV;
          }
          changoSesion.setInstance( chango );
          mAV.addObject("chango", changoSesion);
          mAV.addObject("items", itemService.findByChango(idChango) );
          mAV.addObject("clientes", clienteService.getAllModel());
          mAV.addObject("cliente", new ClienteModel()); 
          return mAV;
     }
 
     /* VISTA DE LOS ITEMS DE UN CHANGO */
     @GetMapping("ver/{idChango}")
     public ModelAndView verChango(@PathVariable("idChango") long idChango) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHANGO_FACTURADO);
          setUserAndLocal(mAV);
          ChangoModel chango = changoService.findByIdChango(idChango);
          // Verifica si el chango que se intenta acceder existe
          if(chango == null) {
               mAV.setViewName("error/404");
               return mAV;
          }
          // Verifica que se intenta ver sea del local del cual está logueado
          if(! chango.getLocal().equals(localModel) ){
               mAV.setViewName("error/403");
               return mAV;
          }
          List<ItemModel> items = itemService.findByChango(idChango);
          chango.setListaItems(new HashSet<ItemModel>(items));
          mAV.addObject("chango", chango);
          mAV.addObject("items", items);
          return mAV;
     }

     // Agrega un nuevo item al chango, su cantidad inicial será 1
     @PostMapping("nuevo-item/{idChango}/{idProducto}")
     public ModelAndView agregarItem(@PathVariable("idChango") long idChango,
               @PathVariable("idProducto") long idProducto) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEM);
          // Verifico que el producto elegido no esté ya en un item del chango
          if (itemService.findByChangoAndProducto(idChango, idProducto) == null) {
               ItemModel newItem = new ItemModel(1, productoService.findByIdProducto(idProducto),
                         changoService.findByIdChango(idChango));
               ItemModel item = itemService.insertOrUpdate(newItem);
               mAV.addObject("item", item);
               mAV.setStatus(HttpStatus.CREATED);
               // Resta la cantidad agregada del/de los lote/s
               localModel.restarLote(item.getProductoModel(), 1);
          } else {
               mAV.setStatus(HttpStatus.NOT_ACCEPTABLE);
          }
          return mAV;
     }

     // Elimina un item de un chango abierto
     @PostMapping("eliminar-item/{idItem}")
     public ResponseEntity<String> eliminarItem(@PathVariable("idItem") long idItem) {
          System.out.println("\n\nELIMINADO");
          ItemModel item = itemService.findByIdItem(idItem);
          ProductoModel producto = item.getProductoModel();
          if (itemService.remove(idItem)) {
               // Devuelve la cantidad del item eliminado a el/los lote/s
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
          // Verifico si estoy agregando cantidad al item
          if (item.getCantidad() < nuevaCantidad) {
               // Verifico si hay stock en el local
               if (localModel.validarStock(item.getProductoModel(), nuevaCantidad - item.getCantidad())) {
                    // Resto a los lotes del local la cantidad que se sumó
                    localModel.restarLote(item.getProductoModel(), nuevaCantidad - item.getCantidad());
               } else {
                    // No se realizan cambios y devuelvo el una respuesta con la cantidad actual del item
                    return ResponseEntity.badRequest().body(String.valueOf(item.getCantidad()));
               }
          } else {
               // Sumo a los lotes la cantidad que se restó
               localModel.devolverLote(item.getProductoModel(), item.getCantidad() - nuevaCantidad);
          }
          item.setCantidad(nuevaCantidad);
          itemService.insertOrUpdate(item);
          return new ResponseEntity<String>(HttpStatus.OK);
     }

     /* Elimina un chango y sus items */
     @PostMapping("cancelar/{idChango}")
     public ModelAndView cancelarChango(@PathVariable("idChango") long idChango, 
               RedirectAttributes redirectAttributes) {
          ModelAndView mAV = new ModelAndView("redirect:/chango/todos");
          // Traer items del chango, devolverlos a sus lotes y eliminarlos
          List<ItemModel> items = itemService.findByChango(idChango);
          for (ItemModel item : items) {
               localModel.devolverLote(item.getProductoModel(), item.getCantidad());
               itemService.remove(item.getIdItem());
          }
          if( changoService.remove(idChango) ) {  // Elimina al chango
               redirectAttributes.addFlashAttribute("changoEliminado", true);
               changoSesion.clear();    //Saca al chango de la sesion
          }
          return mAV;
     }

     /* SACA AL CHANGO DE LA SESIÓN */
     @PostMapping("guardar")
     public String guardarChango() {
          changoSesion.clear();
          return "redirect:/chango/todos";
     }  

     /* VISTA DE TODOS LOS CHANGOS CREADOS, DIFERENCIANDOLOS POR FACTURADOS O NO */
     @GetMapping("todos")
     public ModelAndView traerTodos(Model modelo) {
          // Devuelve una vista con todos los changos del local actual
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHANGOS);
          // Agrega el objeto changoEliminado en caso de ser redireccionado tras cancelar uno
          if(modelo.containsAttribute("changoEliminado")){
               mAV.addObject("changoEliminado", modelo.getAttribute("changoEliminado"));
          }
          setUserAndLocal(mAV);
          List<ChangoDetalleDto> changos = new ArrayList<ChangoDetalleDto>();
          for(ChangoModel chango: changoService.findByLocal(localModel)) {
               // Llena los detalles que se pasaran a la vista de la lista de los changos
               chango.setListaItems( new HashSet<>( itemService.findByChango(chango.getIdChango())));
               int cantidadDeItems = chango.getListaItems().size();
               double total = chango.calcularTotalChango();
               boolean facturado = ( facturaService.findByChango(chango) != null );
               changos.add( new ChangoDetalleDto(chango.getIdChango(), cantidadDeItems, total, facturado));
          }
          mAV.addObject("changos", changos);
          return mAV; 
     }

     // Establece el usario actual en el ModelAndView y a su local en la instancia de localModel
     protected ModelAndView setUserAndLocal(ModelAndView mAV) {
          // Obtenemos el usuario de la sesión
          UserDto userDto = userService.getCurrentUser();
          mAV.addObject("currentUser", userDto); 
          LocalModel localActual = this.getLocalGivenUser(userDto.getUsername());
          localModel.setInstance(localActual);
          return mAV;  
     }

     /* Obtiene el local del usuario logueado*/
     private LocalModel getLocalGivenUser(String username) {
          com.sistema.application.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
          Local local = user.getEmpleado().getLocal();
          return localConverter.entityToModel(local);
     }  
}
