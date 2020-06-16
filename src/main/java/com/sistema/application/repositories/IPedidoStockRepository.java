package com.sistema.application.repositories;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.PedidoStock;

import java.util.List;
import java.util.Set;

@Repository("pedidoStockRepository")
public interface IPedidoStockRepository extends  JpaRepository<PedidoStock, Serializable>{
	
	public abstract PedidoStock findByIdPedidoStock(long idPedidoStock);
	
	@Query("FROM PedidoStock ps WHERE ps.empleadoSolicitante.idPersona = (:idEmpleadoSolicitante)")
	public abstract Set<PedidoStock> findByEmpleadoSolicitante(@Param("idEmpleadoSolicitante") long idEmpleadoSolicitante);
	
	@Query("FROM PedidoStock ps WHERE empleadoSolicitante.idPersona = (:idEmpleadoSolicitante) AND ps.facturado = false")
	public abstract List<PedidoStock> findByEmpleadoSolicitanteNoFacturado(@Param("idEmpleadoSolicitante") long idEmpleadoSolicitante);
	
	@Query("FROM PedidoStock ps WHERE ps.empleadoSolicitante.idPersona = (:idEmpleadoSolicitante) AND ps.facturado = true")
	public abstract List<PedidoStock> findByEmpleadoSolicitanteFacturado(@Param("idEmpleadoSolicitante") long idEmpleadoSolicitante);
}
