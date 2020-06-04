package com.sistema.application.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.LoteModel;
import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.ILoteService;
import com.sistema.application.services.IPedidoStockService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	@Autowired
	@Qualifier("changoService")
	private IChangoService changoService;
	@Autowired
	@Qualifier("empleadoService")
	private IEmpleadoService empleadoService;
	@Autowired
	@Qualifier("pedidoStockService")
	private IPedidoStockService pedidoStockService;
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;

	@GetMapping("")
	public String pedidos(Model modelo) {
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelo.addAttribute("currentUser", userDto);
		Set<PedidoStockModel> pedidos = pedidoStockService.getAllModel();
		modelo.addAttribute("pedidos", pedidos);
		return ViewRouteHelper.PEDIDO_VIEW;
	}

	/**
	 * Método que solicita un pedido nuevo, donde un empleado realiza el pedido de
	 * un pruducto a otro local. El empleado oferente se selecciona al azar.
	 * 
	 * @param legajo     de tipo long. También podremos obtener el local donde
	 *                   trabaja
	 * @param idLocal2   de tipo long. Representa el local que posee los productos
	 *                   solicitados.
	 * @param idProducto de tipo long.
	 * @param cantidad   de tipo int.
	 * @return boolean
	 */
	@PostMapping("solicitar/{legajo}/{idLocal2}/{idProducto}/{cantidad}")
	public ResponseEntity<String> solicitar(@PathVariable("legajo") long legajo, @PathVariable("idLocal2") long idLocal2, 
											@PathVariable("idProducto") long idProducto, @PathVariable("cantidad") int cantidad) {
		ProductoModel producto = productoService.findByIdProducto(idProducto);
		boolean aceptado = false;
		EmpleadoModel solicitante = empleadoService.findByLegajo(legajo);
		// Tratamos de buscar aleatoriamente a cualquier empleado del otro local
		List<EmpleadoModel> listaEmpleados = empleadoService.findByIdLocal(idLocal2);
		Random rand = new Random();
		int randIndex = rand.nextInt(listaEmpleados.size());
		EmpleadoModel oferente = listaEmpleados.get(randIndex);
		PedidoStockModel pedido = pedidoStockService.insertOrUpdate(new PedidoStockModel(producto, cantidad, aceptado, solicitante, oferente));
		if (pedido != null) { // Para verificar si se creó el pedido
			LocalModel local = oferente.getLocal();
			ChangoModel chango = new ChangoModel(local);
			chango.setPedidoStock(pedido);
			
			// Empezamos a descontar el stock requerido
			boolean continuar = true;
			int i = 0;
			int acumStock = 0;
			List<LoteModel> listaLotes = loteService.findByLoteProductoNoNuevo(producto.getIdProducto(), local.getIdLocal());
			while (continuar && i < listaLotes.size()) {
				LoteModel lote = listaLotes.get(i);
				int cantActual = lote.getCantidadActual();
				if (acumStock + cantActual <= cantidad) { // Para no descontar de más
					lote.setCantidadActual(0); // Adquirimos todos los productos
					lote.setActivo(false);
				} else {
					int diff = cantidad - acumStock;
					int restante = cantActual - diff;
					lote.setCantidadActual(restante); // Establecemos la cantidad que sobra
					continuar = false;
				}
				loteService.insertOrUpdate(lote);
				acumStock = acumStock + cantActual;
				i++;
			}
			local.setListaLotes(new HashSet<LoteModel>(listaLotes)); // List -> Set
			// Persistiendo los datos
			chango = changoService.insertOrUpdate(chango);
			local = localService.insertOrUpdate(local);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		// Cualquier problema
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
