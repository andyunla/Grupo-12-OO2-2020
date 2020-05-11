package com.sistema.application.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.ILocalService;

@Controller
@RequestMapping("empleado")
public class EmpleadoController {
	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	@GetMapping("")
	public String empleados(Model modelo) {
		modelo.addAttribute("empleados", empleadoService.getAll());
		modelo.addAttribute("locales", localService.getAll());
		modelo.addAttribute("empleado", new EmpleadoModel());
		return ViewRouteHelper.EMPLEADO_ABM;
	}
	
	@PostMapping("agregar")
	public String agregar(@ModelAttribute("empleado") EmpleadoModel nuevoEmpleado) {
		empleadoService.insertOrUpdate(nuevoEmpleado);
		return "redirect:/" + ViewRouteHelper.EMPLEADO_ROOT;
	}
	
	@PostMapping("modificar")
	public String modificar(@ModelAttribute("empleado") EmpleadoModel empleadoModificado) {
		empleadoService.insertOrUpdate(empleadoModificado);
		return "redirect:/" + ViewRouteHelper.EMPLEADO_ROOT;
	}
	
	@PostMapping("eliminar/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		boolean eliminado = empleadoService.remove(id);
		redirectAttributes.addFlashAttribute("empleadoEliminado", eliminado);
		return "redirect:/" + ViewRouteHelper.EMPLEADO_ROOT;
	}
}
