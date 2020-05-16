package com.sistema.application.repositories;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Lote;
public interface ILoteRepository extends JpaRepository<Lote, Serializable> {
	public abstract Lote findByIdLote(long idLote);
	@Query("FROM Lote  WHERE id_local = (:idLocal) and id_producto=(:idProducto) and activo = true")
	public abstract Set<Lote> findByLoteProductoActivo(long idProducto, long idLocal);
	@Query("FROM Lote  WHERE id_local = (:idLocal) and id_producto=(:idProducto) and activo = false order by fecha_ingreso desc")
	public abstract Set<Lote> findByLoteProductoBaja(long idProducto, long idLocal);
}
