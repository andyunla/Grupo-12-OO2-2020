package com.sistema.application.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sistema.application.converters.LocalConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.models.ItemModel;
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
     @Qualifier("userConverter")
     private UserConverter userConverter;

     @Autowired
     @Qualifier("localConverter")
     private LocalConverter localConverter;

     @Autowired
     @Qualifier("empleadoService")
     private IEmpleadoService empleadoService;

     @Autowired
     @Qualifier("changoSesion")
     private ChangoModel changoSesion;

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
          @PathVariable("idChango") long idChango ) 
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
          changoSesion.clear();
          return mAV; 
     }

     @GetMapping("ver/{idFactura}")
     public ModelAndView traerFactura(@PathVariable ("idFactura") long idFactura) {
          //TODO: Controlar el caso en que se necesiten dos hojas
          //TODO: Controlar que solo se accedan a facturas del local
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.FACTURA);
          FacturaModel factura = facturaService.findByIdFactura(idFactura);
          UserDto userDto = userService.getCurrentUser();
          List <ItemModel> items = itemService.findByChango(factura.getChango().getIdChango());
          mAV.addObject("currentUser", userDto); 
          mAV.addObject("factura", factura);
          mAV.addObject("items", items);
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

     // AGREGAR FILTRO POR MES ?
}
