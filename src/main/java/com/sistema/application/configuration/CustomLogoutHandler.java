package com.sistema.application.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomLogoutHandler extends SecurityContextLogoutHandler {

     @Autowired
     @Qualifier("changoService")
     private IChangoService changoService;

     @Autowired
     @Qualifier("itemService")
     private IItemService itemService;

     @Autowired
     @Qualifier("loteService")
     private ILoteService loteService;

     @Override
     public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
          // Si hay un chango abierto lo elimina junto con sus items
          ChangoModel chango = (ChangoModel) request.getSession().getAttribute("chango");
          if (chango != null && !changoService.estaFacturado(chango)) {
               changoService.removeWithItems(chango.getIdChango());
          }
          request.getSession().invalidate(); // Cierra la sesión
     }
}