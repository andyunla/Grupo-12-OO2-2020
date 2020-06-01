package com.sistema.application.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sistema.application.converters.LocalConverter;
import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.ProductoDisponibleDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.entities.Local;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.IProductoService;
import com.sistema.application.services.implementations.FacturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
     @Qualifier("changoService")
     private IChangoService changoService;

     @Autowired
     @Qualifier("localConverter")
     private LocalConverter localConverter;

     @Autowired
     @Qualifier("userConverter")
     private UserConverter userConverter;

     @Autowired
     @Qualifier("productoConverter")
     private ProductoConverter productoConverter;

     @Autowired
     @Qualifier("localModel")
     private LocalModel localModel;

     @Autowired
     @Qualifier("changoSesion")
     private ChangoModel changoSesion;

     @Autowired
     @Qualifier("facturaService")
     private FacturaService facturaService;

     @GetMapping("")
     public ModelAndView chango() {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.CHANGO);
          setUserAndLocal(mAV);
          // Selecciono de todos los productos solo los que tienen por lo menos 1 en stock
          // en el local
          if( changoSesion.hasInstance() ) {
               mAV.setViewName("redirect:/chango/" + changoSesion.getIdChango());
               return mAV;
          }
          List<ProductoDisponibleDto> productosConStock = new ArrayList<ProductoDisponibleDto>();
          for (ProductoModel p : productoService.getAllModel()) {
               int stock = localModel.calcularStockLocal(p);
               if (stock > 0) {
                    productosConStock.add(productoConverter.modelToDTO(p, stock, false));
               }
          }
          ChangoModel nuevoChango = localModel.crearChango();
          changoSesion.setInstance(nuevoChango);
          mAV.addObject("productos", productosConStock);
          mAV.addObject("chango", nuevoChango);
          mAV.addObject("items", new ArrayList<ItemModel>());
          return mAV;
     } 

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
          List<ProductoDisponibleDto> productosConStock = new ArrayList<ProductoDisponibleDto>();
          // Paso como productos disponibles aquellos que tienen stock o que no tienen stock
          // pero están como item del chango
          for (ProductoModel p : productoService.getAllModel()) {
               int stock = localModel.calcularStockLocal(p);
               ItemModel item = itemService.findByChangoAndProducto(idChango, p.getIdProducto());
               // Verifica si hay stock o si el producto está como item
               if (stock > 0 || item != null) {
                    // Si el producto está como item sumo su cantidad al stock que se visualizará
                    if(item != null) {
                         stock += item.getCantidad();
                    }
                    productosConStock.add(productoConverter.modelToDTO(p, stock, item != null));
               }
          }
          mAV.addObject("productos", productosConStock);
          mAV.addObject("chango", changoSesion);
          mAV.addObject("items", itemService.findByChango(idChango) );
          return mAV;
     }
 
     // Agrega un nuevo item al chango, su cantidad será 1
     @PostMapping("nuevo-item/{idChango}/{idProducto}")
     public ModelAndView agregarItem(@PathVariable("idChango") long idChango,
               @PathVariable("idProducto") long idProducto) {
          // localModel.setInstance(localService.findByIdLocal(1));
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.ITEM);
          // Verifico que el producto elegido no esté ya en un item del chango
          if (itemService.findByChangoAndProducto(idChango, idProducto) == null) {
               ItemModel newItem = new ItemModel(1, productoService.findByIdProducto(idProducto),
                         changoService.findByIdChango(idChango));
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
          // localModel.setInstance(localService.findByIdLocal(1));
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
          // localModel.setInstance(localService.findByIdLocal(1));
          // Verifico si estoy agregando cantidad al item
          if (item.getCantidad() < nuevaCantidad) {
               // Verifico si hay stock en el local
               if (localModel.validarStock(item.getProductoModel(), nuevaCantidad - item.getCantidad())) {
                    // Resto a los lotes del local la diferencia entre la nueva cantidad que se sumó
                    localModel.restarLote(item.getProductoModel(), nuevaCantidad - item.getCantidad());
               } else {
                    // No se realizan cambios y devuelvo el una respuesta con la cantidad actual del
                    // item
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
          // localModel.setInstance(localService.findByIdLocal(1));
          List<ItemModel> items = itemService.findByChango(idChango);
          for (ItemModel item : items) {
               localModel.devolverLote(item.getProductoModel(), item.getCantidad());
               itemService.remove(item.getIdItem());
          }
          // Eliminar chango
          changoService.remove(idChango);
          changoSesion.clear();
          return "redirect:/" + ViewRouteHelper.HOME_ROOT;
     }

     // Setea el usario actual en el ModelAndView y setea su local en la instancia de localModel
     protected ModelAndView setUserAndLocal(ModelAndView mAV) {
          User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          UserDto userDto = userConverter.entityToDto(userRepository.findByUsername(user.getUsername()));
          boolean isGerente = user.getAuthorities().contains(new SimpleGrantedAuthority(UtilHelper.ROLE_GERENTE));
          userDto.setTipoGerente(isGerente);
          mAV.addObject("currentUser", userDto); 
          LocalModel localActual = this.getLocalGivenUser(userDto.getUsername());
          localModel.setInstance(localActual);
          return mAV;  
     }


     private LocalModel getLocalGivenUser(String username) {
          com.sistema.application.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
          Local local = user.getEmpleado().getLocal();
          return localConverter.entityToModel(local);
     }
}