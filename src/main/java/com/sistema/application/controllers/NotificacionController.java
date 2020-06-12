package com.sistema.application.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.application.dto.NotificacionDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.INotificacionService;
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
	@Qualifier("userService")
	private UserService userService;
	
	@GetMapping("")
	public String notificaciones(Model modelo) {
		// Obtenemos el usuario de la sesión
		UserDto userDto = userService.getCurrentUser();
		modelo.addAttribute("currentUser", userDto);
		// Le mandamos todas las notificaciones. Sean leídas o no
		List<NotificacionDto> listaRespuestas = notificacionService.findByUserTo(userDto.getUsername());
		List<NotificacionDto> listaSolicitudes = notificacionService.findByIdLocal(userDto.getLocal().getIdLocal());
		List<NotificacionDto> listaFinal = new ArrayList<NotificacionDto>();
		listaFinal.addAll(listaRespuestas);
		listaFinal.addAll(listaSolicitudes);
		// Para establecer como ya leídos
		for(NotificacionDto notificacion : listaFinal) {
			if(notificacion.isEstado() == false) { // Si aún no ha sido leído
				notificacionService.insertOrUpdate(notificacion);
			}
		}
		modelo.addAttribute("notificaciones", listaFinal);
		return ViewRouteHelper.NOTIFICACION_ROOT;
	}
}
