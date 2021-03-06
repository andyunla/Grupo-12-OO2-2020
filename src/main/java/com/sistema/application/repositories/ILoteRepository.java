package com.sistema.application.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Local;
import com.sistema.application.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable> {
	
	public abstract Lote findByIdLote(long idLote);
	
	@Query("FROM Lote  WHERE id_local = (:idLocal) AND id_producto=(:idProducto) AND activo = true ORDER BY fecha_ingreso ASC")
	public abstract List<Lote> findByLoteProductoActivo(long idProducto, long idLocal);

	// Devuelve una lista de lotes cuya cantidad actual no sea la inicial, es decir no nuevos
	@Query("FROM Lote  WHERE id_local = (:idLocal) AND id_producto=(:idProducto) AND cantidad_inicial != cantidad_actual ORDER BY fecha_ingreso DESC")
	public abstract List<Lote> findByLoteProductoNoNuevo(long idProducto, long idLocal);

	public abstract List<Lote> findByLocalOrderByFechaIngresoDesc(Local local);

	@Query("FROM Lote WHERE id_local = (:idLocal) AND " +
	"id_producto = CASE(:idProducto) WHEN 0 THEN id_producto ELSE (:idProducto) END AND " + 
	"activo = CASE(:soloActivos) WHEN 0 THEN activo ELSE (true) END " + 
	"ORDER BY fecha_ingreso DESC")
	public abstract List<Lote> findByALocalProductoAndActivo(long idLocal, long idProducto, boolean soloActivos);
}
  
