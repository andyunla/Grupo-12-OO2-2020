package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.dto.DetalleNotificacionDto;
import com.sistema.application.dto.NotificacionDto;
import com.sistema.application.entities.DetalleNotificacion;
import com.sistema.application.entities.Local;
import com.sistema.application.entities.Notificacion;
import com.sistema.application.entities.PedidoStock;
import com.sistema.application.entities.Producto;
import com.sistema.application.entities.User;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.repositories.ILocalRepository;
import com.sistema.application.repositories.IPedidoStockRepository;
import com.sistema.application.repositories.IProductoRepository;
import com.sistema.application.repositories.IUserRepository;

@Component("notificacionConverter")
public class NotificacionConverter {
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	@Autowired
	@Qualifier("pedidoStockRepository")
	private IPedidoStockRepository pedidoStockRepository;
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
			idLocal = notificacion.getLocalTo().getIdLocal();
		} else { // Es una respuesta dirigida a un usuario
			usernameTo = notificacion.getUserTo().getUsername();
		}
		DetalleNotificacion detalle = notificacion.getDetalleNotificacion();
		if(detalle != null) {
			Long idProducto = null;
			String nombreProducto = null;
			int cantidad = 0;
			if(detalle.getProducto() != null) { // Si es una solicitud
				idProducto = detalle.getProducto().getIdProducto();
				nombreProducto = productoRepository.findByIdProducto(idProducto).getNombre();
				cantidad = detalle.getCantidad();
			}
			Long idPedidoStock = null;
			if (detalle.getPedido() != null) // En caso que sea una respuesta
				idPedidoStock = detalle.getPedido().getIdPedidoStock();
			detalleDto = new DetalleNotificacionDto(detalle.getId(), idProducto, nombreProducto, cantidad, idPedidoStock);
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
			localTo = localRepository.findByIdLocal(notificacionDto.getToLocal());
		} else {
			userTo = userRepository.findByUsername(notificacionDto.getTo());
		}
		DetalleNotificacionDto detalleDto = notificacionDto.getDetalleNotificacion();
		if(detalleDto != null) {
			Producto producto = null;
			int cantidad = 0;
			PedidoStock pedido = null;
			Long idProducto = detalleDto.getIdProducto();
			if(idProducto != null) { // Si es una solicitud
				producto = productoRepository.findByIdProducto(detalleDto.getIdProducto());
				cantidad = detalleDto.getCantidad();
			}
			Long idPedidoStock = detalleDto.getIdPedidoStock();
			if(idPedidoStock != null) // En caso que sea una respuesta
				pedido = pedidoStockRepository.findByIdPedidoStock(idPedidoStock);
			detalle = new DetalleNotificacion(detalleDto.getId(), producto, cantidad, pedido);
		}
		return new Notificacion(notificacionDto.getId(), notificacionDto.getTipo(), notificacionDto.isEstado(), notificacionDto.getTexto(),
								userFrom, userTo, localTo, detalle);		
	}

}
