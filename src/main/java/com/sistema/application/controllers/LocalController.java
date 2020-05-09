package com.sistema.application.controllers;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.LocalModel;
import com.sistema.application.services.ILocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("local")
public class LocalController {
     
     @Autowired
     @Qualifier("localService")
     private ILocalService localService;

     @GetMapping("")
	public String locales(Model modelo) {
          modelo.addAttribute("locales", localService.getAll());
          modelo.addAttribute("local", new LocalModel());
          return ViewRouteHelper.LOCAL_ABM;
     }
     
     @PostMapping("agregar")
	public String agregar(@ModelAttribute("local") LocalModel nuevoLocal) {
          System.out.print("\n\n" + nuevoLocal + "\n\n");   // DEBUG: Cambiar lógica apropiada
          return "redirect:/" + ViewRouteHelper.LOCAL_ROOT;
     }
     
     @PostMapping("modificar")
	public String modificar(@ModelAttribute("local") LocalModel localModificado) {
          // LocalModel resultado = localService.insertOrUpdate(localModificado);
          System.out.print("\n\n" + resultado + "\n\n");   // DEBUG: Cambiar lógica apropiada
          return "redirect:/" + ViewRouteHelper.LOCAL_ROOT;
     }
     
     @PostMapping("eliminar/{idLocal}")
	public String eliminar(@PathVariable("idLocal") long idLocal, RedirectAttributes redirectAttributes) {
          redirectAttributes.addFlashAttribute("localEliminado", true);
          System.out.print("\n\n" + idLocal + "\n\n"); // DEBUG: Cambiar lógica apropiada
		return "redirect:/" + ViewRouteHelper.LOCAL_ROOT;
	}	
}