package com.sistema.application.controllers;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.services.IEmpleadoService;
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

     @Autowired
     @Qualifier("empleadoService")
     private IEmpleadoService empleadoService;

     @GetMapping("")
	public String locales(Model modelo) {
          modelo.addAttribute("locales", localService.getAll());
          modelo.addAttribute("local", new LocalModel());
          return ViewRouteHelper.LOCAL_ABM;
     }
     
     @PostMapping("agregar")
	public String agregar(@ModelAttribute("local") LocalModel nuevoLocal) {
          localService.insertOrUpdate(nuevoLocal);
          return "redirect:/" + ViewRouteHelper.LOCAL_ROOT;
     }
     
     @PostMapping("modificar")
	public String modificar(@ModelAttribute("local") LocalModel localModificado) {
          System.out.println(localModificado);
          // Modifico el empleado gerente anterior si es que se eligió un nuevo gerente
          if(localModificado.getGerente() != null) {
               // Obtengo el local modificado de la bd
               LocalModel localOriginal = localService.findByIdLocal(localModificado.getIdLocal());
               // Obtengo el legajo del empleado que era gerente
               EmpleadoModel gerenteOriginal = localOriginal.getGerente();
               // Si el local no tiene un gerente asociado 'legajoGerente' vale 0 
               // y ocacionará una excepción si se lo busca por el método 'empleadoService.findByLegajo'
               if(gerenteOriginal != null) {
                    // Obtengo el empleado que era gerente y lo cambio a empleado común
                    EmpleadoModel gerenteAnterior = empleadoService.findByLegajo(gerenteOriginal.getLegajo());
                    gerenteAnterior.setTipoEmpleado(false);
                    empleadoService.insertOrUpdate(gerenteAnterior);
               }
               // Actualizo el local modificado y al empleado que será gerente
               EmpleadoModel nuevoGerente = empleadoService.findByLegajo(localModificado.getGerente().getLegajo());
               nuevoGerente.setTipoEmpleado(true);
               empleadoService.insertOrUpdate(nuevoGerente);
          }
          localService.insertOrUpdate(localModificado);
          return "redirect:/" + ViewRouteHelper.LOCAL_ROOT;
     }
     
     @PostMapping("eliminar/{idLocal}")
	public String eliminar(@PathVariable("idLocal") long idLocal, RedirectAttributes redirectAttributes) {
          boolean eliminado = localService.remove(idLocal);
		redirectAttributes.addFlashAttribute("localEliminado", eliminado);
		return "redirect:/" + ViewRouteHelper.LOCAL_ROOT;
	}
}
