package com.sistema.application.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Notificacion;

@Repository("notificacionRepository")
public interface INotificacionReporitory extends JpaRepository<Notificacion, Serializable> {
	
	public abstract Notificacion findById(long id);
	/**
	 * Sólo tomamos en cuenta los mensajes que no fueron leídos(estado = false)
	 */
	@Query("SELECT n FROM Notificacion n JOIN FETCH n.detalleNotificacion WHERE n.userFrom.id = (:idUserFrom) AND n.estado = false")
	public abstract List<Notificacion> findByUserFrom(@Param("idUserFrom") long idUserFrom);

	@Query("SELECT n FROM Notificacion n WHERE n.userTo.id = (:idUserTo) AND n.estado = false")
	public abstract List<Notificacion> findByUserTo(@Param("idUserTo") long idUserTo);

	@Query("SELECT n FROM Notificacion n WHERE n.localTo.id = (:idLocal) AND n.estado = false")
	public abstract List<Notificacion> findByLocal(@Param("idLocal") long idLocal);
}
  
