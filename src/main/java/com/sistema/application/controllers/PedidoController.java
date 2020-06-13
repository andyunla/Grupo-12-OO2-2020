package com.sistema.application.controllers;


import java.util.List;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.DetalleNotificacionDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IClienteService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	@Qualifier("clienteService")
	private IClienteService clienteService;
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
	public ModelAndView pedidoStock() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PEDIDO_STOCK_VIEW );
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelAndView.addObject("currentUser", userDto);
		List<PedidoStockModel> pedidos =  pedidoStockService.getAllModel();
		modelAndView.addObject("pedidos", pedidos);
		modelAndView.addObject("clientes", clienteService.getAllModel());
        modelAndView.addObject("cliente", new ClienteModel());
		return modelAndView;
	}

	@PostMapping("solicitar/{userSolicitante}/{userOferente}/{aceptado}/{idProducto}/{cantidad}")
	public ResponseEntity<DetalleNotificacionDto> solicitar(@PathVariable("userSolicitante") String userSolicitante, 
											@PathVariable("userOferente") String userOferente, @PathVariable("aceptado") boolean aceptado,
											@PathVariable("idProducto") long idProducto, @PathVariable("cantidad") int cantidad) {
		
		PedidoStockModel pedido = pedidoStockService.crearPedido(userSolicitante, userOferente, aceptado, idProducto, cantidad);
		if (pedido != null) { // Para verificar si se creó el pedido			
			loteService.consumirStock(pedido.getEmpleadoOferente().getLocal().getIdLocal(), pedido.getProducto().getIdProducto(), cantidad);
			// Enviar de datos al cliente(js)
			DetalleNotificacionDto detalleDto = new DetalleNotificacionDto();
			detalleDto.setIdPedidoStock(pedido.getIdPedidoStock());
			return new ResponseEntity<DetalleNotificacionDto>(detalleDto, HttpStatus.CREATED);
		}
		// Cualquier problema
		return new ResponseEntity<DetalleNotificacionDto>(HttpStatus.BAD_REQUEST);
	}
}
