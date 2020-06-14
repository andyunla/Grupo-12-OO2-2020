package com.sistema.application.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.sistema.application.converters.LocalConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IClienteService;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.IFacturaService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.implementations.UserService;
import com.sistema.application.helpers.ViewRouteHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("factura")
public class FacturaController {

     @Autowired
     @Qualifier("facturaService")
     private IFacturaService facturaService;

     @Autowired
     @Qualifier("clienteService")
     private IClienteService clienteService;

     @Autowired
     @Qualifier("changoService")
     private IChangoService changoService;

     @Autowired
     @Qualifier("localConverter")
     private LocalConverter localConverter;

     @Autowired
     @Qualifier("empleadoService")
     private IEmpleadoService empleadoService;

     @Autowired
     @Qualifier("userService")
     private UserService userService;

     @Autowired
     @Qualifier("localService")
     private ILocalService localService;

     @Autowired
     @Qualifier("itemService")
     private IItemService itemService;
     
     @PostMapping("confirmar/{idChango}")
     public ModelAndView crearFactura(@ModelAttribute ClienteModel cliente, 
          @PathVariable("idChango") long idChango, HttpSession session ) 
     {
          ModelAndView mAV = new ModelAndView();
          UserDto userDto = userService.getCurrentUser();
          mAV.addObject("currentUser", userDto); 
          FacturaModel nuevaFactura = new FacturaModel(   
               clienteService.findByNroCliente(cliente.getNroCliente()),
               changoService.findByIdChango(idChango),
               LocalDate.now(),
               changoService.calcularTotal(idChango),
               empleadoService.findByLegajo(userDto.getLegajo()),
               localService.findByIdLocal(userDto.getLocal().getIdLocal())
               );
          FacturaModel facturaGuradada = facturaService.insertOrUpdate(nuevaFactura);
          mAV.setViewName("redirect:/factura/ver/" + facturaGuradada.getIdFactura());
          session.removeAttribute("chango");
          return mAV; 
     }

     @PostMapping("confirmarPedido/{idPedidoStock}")
     public String crearFactura( @ModelAttribute ClienteModel cliente,
                                 @PathVariable("idPedidoStock") long idPedidoStock ) {
          FacturaModel factura = facturaService.facturaPedido(idPedidoStock, cliente.getNroCliente());
          return "redirect:/factura/ver/" + factura.getIdFactura();
     }

     @GetMapping("ver/{idFactura}")
     public ModelAndView traerFactura(@PathVariable ("idFactura") long idFactura) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.FACTURA);
          UserDto userDto = userService.getCurrentUser();
          FacturaModel factura = facturaService.findByIdFactura(idFactura);
          // Verifico que la factura exista
          if(factura == null) {
               mAV.setViewName("error/404");
               return mAV;
          }
          // Verifica que la factura sea del local del empleado logueado
          if( factura.getLocal().getIdLocal() != userDto.getLocal().getIdLocal()) {
               mAV.setViewName("error/403");
               return mAV;
          }
          List <ItemModel> items = itemService.findByChango(factura.getChango().getIdChango());
          // Creo una lista de listas de items. Cada lista tendrá hasta max 13 items, seran 13 items por hoja
          List <ArrayList<ItemModel>> listaDeListas = new ArrayList<ArrayList<ItemModel>>();
          // Verifica si la factura es de un pedido, de serlo lo manda como un item
          PedidoStockModel pedido = factura.getChango().getPedidoStock();
          if( pedido != null ) {
               ItemModel itemPedido = new ItemModel(pedido.getCantidad(), pedido.getProducto());
               listaDeListas.add(new ArrayList<ItemModel>(Arrays.asList(itemPedido)));
          } else {
               int cursorListas = -1;
               for(int i=0; i<items.size(); i++) {     
                    if(i == 0 || i % 13 == 0) {
                         listaDeListas.add(new ArrayList<ItemModel>());
                         cursorListas++;
                    }
                    listaDeListas.get( cursorListas ).add( items.get(i) );
               }
          }
          mAV.addObject("todosLosItems", listaDeListas);
          mAV.addObject("currentUser", userDto); 
          mAV.addObject("factura", factura);
          return mAV;
     }

     @GetMapping("todas")
     public ModelAndView traerFactura() {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.FACTURAS);
          UserDto userDto = userService.getCurrentUser();
          List <FacturaModel> facturas = facturaService.findByIdLocal(userDto.getLocal().getIdLocal());
          List <EmpleadoModel> empleados = empleadoService.findByIdLocal(userDto.getLocal().getIdLocal());
          mAV.addObject("currentUser", userDto); 
          mAV.addObject("facturas", facturas);
          mAV.addObject("empleados", empleados);
          return mAV;
     }

     @GetMapping("empleado/{legajo}")
     public ModelAndView traerFacturaPorEmpleado(@PathVariable("legajo") long legajo) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.LISTA_FACTURAS);
          UserDto userDto = userService.getCurrentUser();
          List <FacturaModel> facturas = new ArrayList<FacturaModel>();
          // Si el legajo es 0 no se filtrará por empleado
          if(legajo == 0 ) {
               facturas = facturaService.findByIdLocal(userDto.getLocal().getIdLocal());
          } else {
               facturas = facturaService.findByIdLocalAndByLegajoEmpleado(userDto.getLocal().getIdLocal(), legajo);
          }
          mAV.addObject("facturas", facturas);
          return mAV;
     }
}
