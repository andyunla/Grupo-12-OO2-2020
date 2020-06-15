package com.sistema.application.repositories;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Chango;
import com.sistema.application.entities.Factura;

@Repository("facturaRepository")
public interface IFacturaRepository  extends JpaRepository<Factura, Serializable>{
	public abstract Factura findByIdFactura(long idFactura);
	@Query("FROM Factura  WHERE id_local = :idLocal AND fechahora_factura BETWEEN :fecha1 AND :fecha2")
	public abstract List<Factura> findByFechaFacturaBetweenAndIdLocal(LocalDateTime fecha1, LocalDateTime fecha2, long idLocal);
	@Query("FROM Factura  WHERE fechahora_factura BETWEEN :fecha1 AND :fecha2")
	public abstract List<Factura> findByFechaFacturaBetween(LocalDateTime fecha1, LocalDateTime fecha2);
	//public abstract Set<Factura> findByFechaFacturaBetween(LocalDateTime fecha1, LocalDateTime fecha2);
	public abstract Factura findByChango(Chango chango);
	@Query("FROM Factura  WHERE id_local = (:idLocal) ORDER BY fechahora_factura DESC")
	public abstract List<Factura> findByIdLocal(long idLocal);
	@Query("FROM Factura  WHERE id_local = (:idLocal) AND empleado_legajo = (:legajo) ORDER BY fechahora_factura DESC")
	public abstract List<Factura> findByIdLocalAndByLegajoEmpleado(long idLocal, long legajo);
}
