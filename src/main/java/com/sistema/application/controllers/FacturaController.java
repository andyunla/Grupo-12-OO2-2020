package com.sistema.application.controllers;

import java.time.LocalDate;

import com.sistema.application.converters.LocalConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.entities.Local;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IClienteService;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.IFacturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
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
     @Qualifier("userRepository")
     private IUserRepository userRepository;

     @Autowired
     @Qualifier("localModel")
     private LocalModel localModel;

     @Autowired
     @Qualifier("localConverter")
     private LocalConverter localConverter;

     @Autowired
     @Qualifier("empleadoService")
     private IEmpleadoService empleadoService;

     @Autowired
     @Qualifier("changoSesion")
     private ChangoModel changoSesion;
     
     @PostMapping("confirmar/{idChango}")
     public ModelAndView crearFactura(@ModelAttribute ClienteModel cliente, 
          @PathVariable("idChango") long idChango ) 
     {
          ModelAndView mAV = new ModelAndView("redirect:/chango/todos");
          // Obtiene el usuario 
          User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          UserDto userDto = userConverter.entityToDto(userRepository.findByUsername(user.getUsername()));
          boolean isGerente = user.getAuthorities().contains(new SimpleGrantedAuthority(UtilHelper.ROLE_GERENTE));
          userDto.setTipoGerente(isGerente);
          mAV.addObject("currentUser", userDto); 
          // Obtiene local
          FacturaModel nuevaFactura = new FacturaModel(   
               clienteService.findByNroCliente(cliente.getNroCliente()),
               changoService.findByIdChango(idChango),
               LocalDate.now(),
               changoService.calcularTotal(idChango),
               empleadoService.findByLegajo(userDto.getLegajo()),
               this.getLocalGivenUser(userDto.getUsername())
          );
          FacturaModel facturaGuradada = facturaService.insertOrUpdate(nuevaFactura);
          changoSesion.clear();
          return mAV;
     }

      // Obtiene el local del usuario logueado
      private LocalModel getLocalGivenUser(String username) {
          com.sistema.application.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
          Local local = user.getEmpleado().getLocal();
          return localConverter.entityToModel(local);
     } 
}