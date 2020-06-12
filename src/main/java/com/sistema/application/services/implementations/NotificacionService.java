package com.sistema.application.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sistema.application.converters.NotificacionConverter;
import com.sistema.application.dto.NotificacionDto;
import com.sistema.application.entities.Notificacion;
import com.sistema.application.entities.User;
import com.sistema.application.repositories.INotificacionReporitory;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.INotificacionService;

@Service("notificacionService")
public class NotificacionService implements INotificacionService {
    @Autowired
    @Qualifier("notificacionRepository")
    private INotificacionReporitory notificacionRepository;
    
    @Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;
    
    @Autowired
    @Qualifier("notificacionConverter")
    private NotificacionConverter notificacionConverter;
    
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    
    @Override
    public NotificacionDto findById(long id) {
        return notificacionConverter.entityToDto(notificacionRepository.findById(id) );
    }
    
    @Override
    public List<Notificacion> getAll(){
        return notificacionRepository.findAll();
    }
    
    @Override
    public List<NotificacionDto> getAllDto() {
        List <NotificacionDto> localesModelos = new ArrayList<NotificacionDto>();
        for(Notificacion entidad: notificacionRepository.findAll()){
            NotificacionDto dto = notificacionConverter.entityToDto(entidad);
            localesModelos.add(dto);
        }
        return localesModelos;
    }

    @Override
    public NotificacionDto insertOrUpdate(NotificacionDto notificacion) {
        Notificacion notificacionEntidad = notificacionConverter.dtoToEntity(notificacion);
        Notificacion notificacionGuardada = notificacionRepository.save(notificacionEntidad);
        return notificacionConverter.entityToDto(notificacionGuardada);
    }

    @Override
    public boolean remove(long id) {
        try{
            notificacionRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    // Busca las notificaciones de los usuarios que las enviaron
    @Override
    public List<NotificacionDto> findByUserFrom(String usernameFrom) {
        List<NotificacionDto> lista = new ArrayList<NotificacionDto>();
        User user = userRepository.findByUsernameAndFetchUserRolesEagerly(usernameFrom);
        for(Notificacion notificacion: notificacionRepository.findByUserFrom(user.getId())) {
            lista.add(notificacionConverter.entityToDto(notificacion));
        }
        return lista;
    }
    
    // Busca las notificaciones de los usuarios que necesitan saber si recibieron alguna notificación
    @Override
    public List<NotificacionDto> findByUserTo(String usernameTo) {
        List<NotificacionDto> lista = new ArrayList<NotificacionDto>();
        User user = userRepository.findByUsernameAndFetchUserRolesEagerly(usernameTo);
        for(Notificacion notificacion: notificacionRepository.findByUserTo(user.getId())) {
            lista.add(notificacionConverter.entityToDto(notificacion));
        }
        return lista;
    }
    
    // Busca las notificaciones dirijidos a un local en específico
    @Override
    public List<NotificacionDto> findByIdLocal(long idLocal) {
        List<NotificacionDto> lista = new ArrayList<NotificacionDto>();
        for(Notificacion notificacion: notificacionRepository.findByLocal(idLocal)) {
            lista.add(notificacionConverter.entityToDto(notificacion));
        }
        return lista;
    }
}
