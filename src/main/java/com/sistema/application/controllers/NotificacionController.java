package com.sistema.application.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.application.dto.NotificacionDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.INotificacionService;
import com.sistema.application.services.IPedidoStockService;
import com.sistema.application.services.implementations.UserService;

@Controller
@RequestMapping("notificacion")
public class NotificacionController {
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	@Autowired
	@Qualifier("notificacionService")
	private INotificacionService notificacionService;
	@Autowired
	@Qualifier("pedidoStockService")
	private IPedidoStockService pedidoStockService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@GetMapping("")
	public String notificaciones(Model modelo) {
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelo.addAttribute("currentUser", userDto);
		// Le mandamos todas las notificaciones. Sean leídas o no
		List<NotificacionDto> listaRespuestas = notificacionService.findByUserTo(userDto.getUsername());
		for(NotificacionDto respuesta : listaRespuestas) {
			// Para determinar si mostramos el enlace al pedido en caso que no se haya facturado
			Long idPedido = respuesta.getDetalleNotificacion().getIdPedidoStock();
			boolean estaFacturado = pedidoStockService.findByIdPedidoStock(idPedido).isFacturado();
			respuesta.getDetalleNotificacion().setPedidoFacturado(estaFacturado);
		}
		List<NotificacionDto> listaSolicitudes = notificacionService.findByIdLocal(userDto.getLocal().getIdLocal());
		List<NotificacionDto> listaFinal = new ArrayList<NotificacionDto>();
		listaFinal.addAll(listaRespuestas);
		listaFinal.addAll(listaSolicitudes);
		// Para establecer como ya leídos
		for(NotificacionDto notificacion : listaFinal) {
			if(notificacion.isLeido() == false) { // Si aún no ha sido leído
				notificacion.setLeido(true);
				notificacionService.insertOrUpdate(notificacion);
			}
		}
		modelo.addAttribute("notificaciones", listaFinal);
		return ViewRouteHelper.NOTIFICACION_ROOT;
	}

	// Ejemplo: HOST/notificacion/ver?id=3
	@GetMapping("/ver")
	public ModelAndView verNotificacion(@RequestParam(name="id", required=true, defaultValue="0") String id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.NOTIFICACION_ROOT);
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		mAV.addObject("currentUser", userDto);
		List<NotificacionDto> lista = new ArrayList<NotificacionDto>();
		Long idNotificacion = 0L;
		try {
			idNotificacion = Long.parseLong(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(idNotificacion >= 1) {
			NotificacionDto notificacion = notificacionService.findById(idNotificacion);
			if(notificacion != null) {
				notificacion.setLeido(true);
				notificacionService.insertOrUpdate(notificacion);
				lista.add(notificacion);
			}
		}
		mAV.addObject("notificaciones", lista);
		return mAV;
	}

	// Obtenemos tanto las solicitudes del local
	// Como las respuestas al usuario actual
	@GetMapping("/consultar")
	public ModelAndView consultarTodo() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LISTA_NOTIFICACIONES);
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		// Le mandamos todas las notificaciones. Sean leídas o no
		List<NotificacionDto> listaRespuestas = notificacionService.findByUserTo(userDto.getUsername());
		for(NotificacionDto respuesta : listaRespuestas) {
			// Para determinar si mostramos el enlace al pedido en caso que no se haya facturado
			Long idPedido = respuesta.getDetalleNotificacion().getIdPedidoStock();
			boolean estaFacturado = pedidoStockService.findByIdPedidoStock(idPedido).isFacturado();
			respuesta.getDetalleNotificacion().setPedidoFacturado(estaFacturado);
		}
		List<NotificacionDto> listaSolicitudes = notificacionService.findByIdLocal(userDto.getLocal().getIdLocal());
		List<NotificacionDto> listaFinal = new ArrayList<NotificacionDto>();
		listaFinal.addAll(listaRespuestas);
		listaFinal.addAll(listaSolicitudes);
		// Para establecer como ya leídos
		for(NotificacionDto notificacion : listaFinal) {
			if(notificacion.isLeido() == false) { // Si aún no ha sido leído
				notificacion.setLeido(true);
				notificacionService.insertOrUpdate(notificacion);
			}
		}
		mAV.addObject("notificaciones", listaFinal);
		return mAV;
	}

	@GetMapping("/consultar/{idLocal}/{estadoNotificacion}")
	public ModelAndView consultarSolicitud(@PathVariable("idLocal") long idLocal, @PathVariable("estadoNotificacion") String estadoNotificacion) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LISTA_NOTIFICACIONES);
		// Le mandamos todas las notificaciones. Sean leídas o no
		List<NotificacionDto> listaSolicitudes = notificacionService.findByIdLocal(idLocal);
		List<NotificacionDto> listaFinal = new ArrayList<NotificacionDto>();
		// Para establecer como ya leídos
		for(NotificacionDto notificacion : listaSolicitudes) {
			if(notificacion.isLeido() == false) { // Si aún no ha sido leído
				notificacion.setLeido(true);
				notificacionService.insertOrUpdate(notificacion);
			}
			if(estadoNotificacion.equalsIgnoreCase("todos")) {
				listaFinal.add(notificacion);
			} else {
				String estado = notificacion.getEstado();
				if(estadoNotificacion.equalsIgnoreCase(estado)) {
					listaFinal.add(notificacion);
				}
			}
		}
		mAV.addObject("notificaciones", listaFinal);
		return mAV;
	}

	@GetMapping("/consultar/{usernameTo}")
	public ModelAndView consultarRespuesta(@PathVariable("usernameTo") String usernameTo) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LISTA_NOTIFICACIONES);
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		// Le mandamos todas las notificaciones. Sean leídas o no
		List<NotificacionDto> listaRespuestas = notificacionService.findByUserTo(userDto.getUsername());
		for(NotificacionDto respuesta : listaRespuestas) {
			// Para determinar si mostramos el enlace al pedido en caso que no se haya facturado
			Long idPedido = respuesta.getDetalleNotificacion().getIdPedidoStock();
			boolean estaFacturado = pedidoStockService.findByIdPedidoStock(idPedido).isFacturado();
			respuesta.getDetalleNotificacion().setPedidoFacturado(estaFacturado);
			// Para establecer como ya leídos
			if(respuesta.isLeido() == false) { // Si aún no ha sido leído
				respuesta.setLeido(true);
				notificacionService.insertOrUpdate(respuesta);
			}
		}
		mAV.addObject("notificaciones", listaRespuestas);
		return mAV;
	}
}
