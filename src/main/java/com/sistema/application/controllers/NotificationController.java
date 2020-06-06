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
		return notificacion;
	}
}
