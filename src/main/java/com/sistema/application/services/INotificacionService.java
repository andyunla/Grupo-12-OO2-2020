package com.sistema.application.services;

import java.util.List;

import com.sistema.application.dto.NotificacionDto;
import com.sistema.application.entities.Notificacion;


public interface INotificacionService {
	public NotificacionDto findById(long id);
    
    public List<Notificacion> getAll();
	
    public List<NotificacionDto> getAllDto();
    
	public NotificacionDto insertOrUpdate(NotificacionDto localModel);
	
	public boolean remove(long id);
	
	public List<NotificacionDto> findByUserFrom(String usernameFrom);
	
	public List<NotificacionDto> findByUserTo(String usernameTo);
	
	public List<NotificacionDto> findByIdLocal(long idLocal);
}
