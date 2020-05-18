package com.sistema.application.controllers;

import java.util.List;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.LoteModel;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.ILoteService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("lote")
public class LoteController {

     @Autowired
     @Qualifier("loteService")
     private ILoteService loteService;

     @Autowired
     @Qualifier("localService")
     private ILocalService localService;

     @Autowired
     @Qualifier("productoService")
     private IProductoService productoService;

     @GetMapping("")
     public String lotes(Model modelo) {
          modelo.addAttribute("lotes", loteService.getAllModels());
          modelo.addAttribute("lote", new LoteModel());
          modelo.addAttribute("locales", localService.getAll());
          modelo.addAttribute("productos", productoService.getAll());
          return ViewRouteHelper.LOTE_ABM;
     }

     @PostMapping("agregar")
     public String agregar(@ModelAttribute("lote") LoteModel nuevoLote) {
          nuevoLote.setCantidadActual(nuevoLote.getCantidadInicial());
          loteService.insertOrUpdate(nuevoLote);
          return "redirect:" + ViewRouteHelper.LOTE_ROOT;
     }

     @GetMapping("traer/{idLocal}/{idProducto}/{soloActivos}")
     public ModelAndView traer(@PathVariable("idLocal") long idLocal, @PathVariable("idProducto") long idProducto,
               @PathVariable("soloActivos") boolean soloActivos) {
          ModelAndView mAV = new ModelAndView(ViewRouteHelper.LISTA_LOTES);
          List<LoteModel> lotes = loteService.findByLocalProductoYActivo(idLocal, idProducto, soloActivos);
          mAV.addObject("lotes", lotes);
          return mAV;
     }
       
     @PostMapping("eliminar/{idLote}")
     public ResponseEntity<String> eliminar(@PathVariable("idLote") long idLote) {
          boolean eliminado = loteService.remove(idLote);
          if (eliminado) {
               return new ResponseEntity<String>(HttpStatus.OK);
          } else {
               return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
          }
     }   
}    