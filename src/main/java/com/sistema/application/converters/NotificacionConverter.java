package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.dto.DetalleNotificacionDto;
import com.sistema.application.dto.NotificacionDto;
import com.sistema.application.entities.DetalleNotificacion;
import com.sistema.application.entities.Local;
import com.sistema.application.entities.Notificacion;
import com.sistema.application.entities.Producto;
import com.sistema.application.entities.User;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.repositories.ILocalRepository;
import com.sistema.application.repositories.IProductoRepository;
import com.sistema.application.repositories.IUserRepository;

@Component("notificacionConverter")
public class NotificacionConverter {
    @Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
    @Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
    @Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
    
	public NotificacionDto entityToDto(Notificacion notificacion) {
		DetalleNotificacionDto detalleDto = null;
		String usernameTo = null; // El username a quién va dirijido
		Long idLocal = null;
		if(notificacion.getTipo().equalsIgnoreCase(UtilHelper.TIPO_NOTIFICACION_SOLICITUD)) {
			DetalleNotificacion detalle = notificacion.getDetalleNotificacion();
			if(detalle != null) {
				detalleDto = new DetalleNotificacionDto(detalle.getId(), detalle.getProducto().getIdProducto(), detalle.getCantidad());
			}
			idLocal = notificacion.getLocalTo().getIdLocal();
		} else { // Es una respuesta dirigida a un usuario
			usernameTo = notificacion.getUserTo().getUsername();
		}
		
		return new NotificacionDto(notificacion.getId(), notificacion.getTipo(), notificacion.isEstado(), notificacion.getTexto(),
								   notificacion.getUserFrom().getUsername(), usernameTo, idLocal, detalleDto);
	}
	
	public Notificacion dtoToEntity(NotificacionDto notificacionDto) {
		DetalleNotificacion detalle = null;
		User userFrom = userRepository.findByUsername(notificacionDto.getFrom());
		User userTo = null;
		Local localTo = null;
		if(notificacionDto.getTipo().equalsIgnoreCase(UtilHelper.TIPO_NOTIFICACION_SOLICITUD)) {
			DetalleNotificacionDto detalleDto = notificacionDto.getDetalleNotificacion();
			if(detalleDto != null) {
				Producto producto = productoRepository.findByIdProducto(detalleDto.getIdProducto());
				detalle = new DetalleNotificacion(detalleDto.getId(), producto, detalleDto.getCantidad());
			}
			localTo = localRepository.findByIdLocal(notificacionDto.getToLocal());
		} else {
			userTo = userRepository.findByUsername(notificacionDto.getTo());
		}		
		return new Notificacion(notificacionDto.getId(), notificacionDto.getTipo(), notificacionDto.isEstado(), notificacionDto.getTexto(),
								userFrom, userTo, localTo, detalle);		
	}

}
