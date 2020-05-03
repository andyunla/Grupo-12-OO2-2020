package com.sistema.application.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ClienteModel;

@Controller
@RequestMapping("clientes")
public class ClientesController {
	//Lista de clientes que simula los datos en la base de datos
		private List <ClienteModel> clientes = new ArrayList <ClienteModel>( Arrays.asList(
				new ClienteModel(1234, "Pepe", "Gonzales", LocalDate.of(2000, 1, 10), "pepe@mail.com", 1),
				new ClienteModel(1235, "Juan", "Gomez", LocalDate.of(2001, 2, 20), "juan@mail.com", 2))
				);
		private int ultimoNroCliente = 2;
		
		@GetMapping("")
		public String clientes(Model modelo) {
			modelo.addAttribute("clientes", clientes);
			modelo.addAttribute("cliente", new ClienteModel());
			return ViewRouteHelper.CLIENTES;
		}
		
		@PostMapping("agregar")
		public String agregar(@ModelAttribute("cliente") ClienteModel nuevoCliente) {
			ultimoNroCliente++;
			nuevoCliente.setNroCliente(ultimoNroCliente);
			clientes.add(nuevoCliente);
	        return "redirect:/clientes";
		}
		
		@PostMapping("modificar")
		public String modificar(@ModelAttribute("cliente") ClienteModel clienteModificado) {
			System.out.println("hola");
			int i=0;
			boolean encontrado = false;
			while(i < clientes.size() && !encontrado) {
				encontrado = clientes.get(i).getNroCliente() == clienteModificado.getNroCliente();
				i++;
			}
			if(encontrado) {
				clientes.set(i-1, clienteModificado);
			}	// En caso de no encontrarlo implementar otra cosa
	        return "redirect:/clientes";
		}
		
		@PostMapping("eliminar/{nroCliente}")
		public String eliminar(@PathVariable("nroCliente") int nroCliente) {
			int i=0;
			boolean encontrado = false;
			while(i < clientes.size() && !encontrado) {
				encontrado = clientes.get(i).getNroCliente() == nroCliente;
				i++;
			}
			if(encontrado) {
				clientes.remove(i-1);
			}	// En caso de no poder implementar otra cosa
	        return "redirect:/clientes";
		}
}
