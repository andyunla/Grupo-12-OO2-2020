package com.sistema.application.repositories;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.PedidoStock;

import java.util.List;
import java.util.Set;

@Repository("pedidoStockRepository")
public interface IPedidoStockRepository extends  JpaRepository<PedidoStock, Serializable>{
	
	public abstract PedidoStock findByIdPedidoStock(long idPedidoStock);
	
	@Query("FROM PedidoStock WHERE solicitante_id=(:idEmpleadoSolicitante)")
	public abstract Set<PedidoStock> findByEmpleadoSolicitante(long idEmpleadoSolicitante);
	
	@Query("FROM PedidoStock WHERE solicitante_id=(:idEmpleadoSolicitante AND facturado = false)")
	public abstract List<PedidoStock> findByEmpleadoSolicitanteNoFacturado(long idEmpleadoSolicitante);
	
	@Query("FROM PedidoStock WHERE solicitante_id=(:idEmpleadoSolicitante AND facturado = true)")
	public abstract List<PedidoStock> findByEmpleadoSolicitanteFacturado(long idEmpleadoSolicitante);
	
}
