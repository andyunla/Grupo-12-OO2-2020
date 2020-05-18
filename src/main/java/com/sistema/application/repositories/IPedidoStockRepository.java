package com.sistema.application.repositories;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.PedidoStock;
import com.sistema.application.models.EmpleadoModel;
import java.util.Set;

@Repository("pedidoStockRepository")
public interface IPedidoStockRepository extends  JpaRepository<PedidoStock, Serializable>{
	public abstract PedidoStock findByIdPedidoStock(long idPedidoStock);
	@Query("FROM PedidoStock  WHERE id_solicitante_legajo=(:legajoEmpleadoSolicitante)")
	public abstract Set<PedidoStock> findByEmpleadoSolicitante(long legajoEmpleadoSolicitante);
	
}
