package com.sistema.application.repositories;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable> {
	
	public abstract Lote findByIdLote(long idLote);
	
	@Query("FROM Lote  WHERE id_local = (:idLocal) and id_producto=(:idProducto) and activo = true")
	public abstract Set<Lote> findByLoteProductoActivo(long idProducto, long idLocal);
	
	@Query("FROM Lote  WHERE id_local = (:idLocal) and id_producto=(:idProducto) and activo = false order by fecha_ingreso desc")
	public abstract Set<Lote> findByLoteProductoBaja(long idProducto, long idLocal);

	@Query("FROM Lote WHERE id_local = CASE(:idLocal) WHEN 0 THEN id_local ELSE (:idLocal) END AND id_producto = CASE(:idProducto) WHEN 0 THEN id_producto ELSE (:idProducto) END AND activo = CASE(:soloActivos) WHEN 0 THEN activo ELSE (true) END")
	public abstract List<Lote> findByLocalProductoYActivo(long idLocal, long idProducto, boolean soloActivos);
}
  