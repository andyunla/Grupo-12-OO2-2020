package com.sistema.application.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Notificacion;

@Repository("notificacionRepository")
public interface INotificacionReporitory extends JpaRepository<Notificacion, Serializable> {
	
	public abstract Notificacion findById(long id);
	
	@Query("FROM Notificacion WHERE user_from = (:idUserFrom)")
	public abstract List<Notificacion> findByUserFrom(long idUserFrom);

	@Query("FROM Notificacion WHERE user_to = (:idUserTo)")
	public abstract List<Notificacion> findByUserTo(long idUserTo);

	@Query("FROM Notificacion WHERE id_local = (:idLocal)")
	public abstract List<Notificacion> findByLocal(long idLocal);
}
  
