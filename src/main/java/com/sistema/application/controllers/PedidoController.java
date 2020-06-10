package com.sistema.application.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.DetalleNotificacionDto;
import com.sistema.application.dto.UserDto;
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
import com.sistema.application.services.implementations.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@Autowired
    @Qualifier("userService")
    private UserService userService;

	@GetMapping("")
	public String pedidos(Model modelo) {
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelo.addAttribute("currentUser", userDto);
		Set<PedidoStockModel> pedidos = pedidoStockService.getAllModel();
		modelo.addAttribute("pedidos", pedidos);
		return ViewRouteHelper.PEDIDO_VIEW;
	}

	@PostMapping("solicitar/{userSolicitante}/{userOferente}/{aceptado}/{idProducto}/{cantidad}")
	public ResponseEntity<DetalleNotificacionDto> solicitar(@PathVariable("userSolicitante") String userSolicitante, 
											@PathVariable("userOferente") String userOferente, @PathVariable("aceptado") boolean aceptado,
											@PathVariable("idProducto") long idProducto, @PathVariable("cantidad") int cantidad) {
		ProductoModel producto = productoService.findByIdProducto(idProducto);
		com.sistema.application.entities.User solicitante = userRepository.findByUsernameAndFetchUserRolesEagerly(userSolicitante);
		com.sistema.application.entities.User oferente = userRepository.findByUsernameAndFetchUserRolesEagerly(userOferente);
		EmpleadoModel solicitanteModel = empleadoService.findByLegajo(solicitante.getEmpleado().getLegajo());
		EmpleadoModel oferenteModel = empleadoService.findByLegajo(oferente.getEmpleado().getLegajo());
		PedidoStockModel pedido = pedidoStockService.insertOrUpdate(new PedidoStockModel(producto, cantidad, aceptado, solicitanteModel, oferenteModel));
		if (pedido != null) { // Para verificar si se creó el pedido
			LocalModel local = oferenteModel.getLocal();
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
			// Enviar de datos al cliente(js)
			DetalleNotificacionDto detalleDto = new DetalleNotificacionDto();
			detalleDto.setIdPedidoStock(pedido.getIdPedidoStock());
			detalleDto.setIdChango(chango.getIdChango());
			return new ResponseEntity<DetalleNotificacionDto>(detalleDto, HttpStatus.CREATED);
		}
		// Cualquier problema
		return new ResponseEntity<DetalleNotificacionDto>(HttpStatus.BAD_REQUEST);
	}
}
