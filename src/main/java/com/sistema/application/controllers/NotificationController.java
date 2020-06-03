package com.sistema.application.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.sistema.application.dto.DetallePedidoDto;
import com.sistema.application.dto.NotificacionDto;

/**
 * Clase que controla las notificaciones entre usuarios.
 */
@Controller
public class NotificationController {
	@MessageMapping("/peticion")
	@SendTo("/topic/respuesta")
	public NotificacionDto procesar(NotificacionDto notificacion) throws Exception {
		Thread.sleep(1000); // simulated delay
		NotificacionDto newNotificacion = null;
		if(notificacion.getType().equals("solicitud")) {
			newNotificacion = buildSolicitud(notificacion);
		} else {
			newNotificacion = buildRespuesta(notificacion);
		}
		return newNotificacion;
	}

	// Solicitudes
	public NotificacionDto buildSolicitud(NotificacionDto notificacion) {
		long id = notificacion.getId();
		String type = notificacion.getType();
		String from = notificacion.getFrom();
		long toLocal = notificacion.getToLocal();
		DetallePedidoDto detallePedido = notificacion.getDetallePedido();
		return new NotificacionDto(id, type, from, toLocal, detallePedido);
	}
	
	// Respuestas
	public NotificacionDto buildRespuesta(NotificacionDto notificacion) {
		long id = notificacion.getId();
		String type = notificacion.getType();
		boolean status = notificacion.isStatus();
		String text = notificacion.getText();
		String from = notificacion.getFrom();
		String to = notificacion.getTo();
		return new NotificacionDto(id, type, status, text, from, to);
	}
}
