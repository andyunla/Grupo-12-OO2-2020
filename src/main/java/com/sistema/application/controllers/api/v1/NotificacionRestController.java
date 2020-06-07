package com.sistema.application.controllers.api.v1;

import java.util.List;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.converters.UserConverter;
import com.sistema.application.dto.DetalleNotificacionDto;
import com.sistema.application.dto.NotificacionDto;
import com.sistema.application.dto.UserDto;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.INotificacionService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("api/v1/notificacion")
public class NotificacionRestController {
	@Autowired
    @Qualifier("empleadoConverter")
    private EmpleadoConverter empleadoConverter;
	@Autowired
    @Qualifier("userConverter")
    private UserConverter userConverter;
    @Autowired
    @Qualifier("localService")
    private ILocalService localService;
    @Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;
    @Autowired
    @Qualifier("notificacionService")
    private INotificacionService notificacionService;
    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;
    @Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;

    @GetMapping("solicitar/{usernameSolicitante}/{idLocal2}/{idProducto}/{cantidad}")
    ResponseEntity<String> solicitar(@PathVariable("legajoSolicitante") String usernameSolicitante, @PathVariable("idLocal2") long idLocal2, 
                                     @PathVariable("idProducto") long idProducto, @PathVariable("cantidad") int cantidad) {
    	ProductoModel producto = productoService.findByIdProducto(idProducto);
    	UserDto solicitante = userConverter.entityToDto(userRepository.findByUsername(usernameSolicitante));
    	String texto = "El empleado " + solicitante.getNombreCompleto() + " necesita " + cantidad + " unidades de " + producto.getNombre();
        String userTo = null;
        NotificacionDto newNotificacionDto = new NotificacionDto(UtilHelper.TIPO_NOTIFICACION_SOLICITUD, false, texto, usernameSolicitante, userTo, idLocal2, 
        														 new DetalleNotificacionDto(idProducto, cantidad));
        NotificacionDto notificacionGuardada = notificacionService.insertOrUpdate(newNotificacionDto);
        if(notificacionGuardada != null)
        	return new ResponseEntity<String>(HttpStatus.CREATED);
        // Cualquier problema
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
    
    // Comprobar si el usuario actual posee notificaciones
    @GetMapping("comprobar/{username}")
    ResponseEntity<List<NotificacionDto>> comprobar(@PathVariable("username") String username) {
        List<NotificacionDto> lista = notificacionService.findByUserTo(username);
        if(lista != null) {
        	return new ResponseEntity<List<NotificacionDto>>(lista, HttpStatus.OK);
        }
        // Cualquier problema
        return new ResponseEntity<List<NotificacionDto>>(HttpStatus.BAD_REQUEST);
    }
    
    // Crear una notificación que indique la respuesta del oferente
    @GetMapping("responder/{usernameFrom}/{usernameTo}/{fueAceptado}")
    ResponseEntity<String> responder(@PathVariable("usernameFrom") String usernameFrom, @PathVariable("usernameTo") String usernameTo,
    												@PathVariable("fueAceptado") boolean fueAceptado) {
    	String texto = "El pedido ha sido rechazado";
        NotificacionDto newNotificacionDto = new NotificacionDto(UtilHelper.TIPO_NOTIFICACION_RESPUESTA, false, texto, usernameFrom, usernameTo, null, null);
        NotificacionDto notificacionGuardada = notificacionService.insertOrUpdate(newNotificacionDto);
        if(notificacionGuardada != null)
        	return new ResponseEntity<String>(HttpStatus.CREATED);
        // Cualquier problema
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}
