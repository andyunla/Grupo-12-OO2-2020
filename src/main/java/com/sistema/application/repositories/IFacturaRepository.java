package com.sistema.application.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Chango;
import com.sistema.application.entities.Factura;

@Repository("facturaRepository")
public interface IFacturaRepository extends JpaRepository<Factura, Serializable> {
	public abstract Factura findByIdFactura(long idFactura);

	@Query("FROM Factura  WHERE id_local = :idLocal AND fechahora_factura BETWEEN :fecha1 AND :fecha2")
	public abstract List<Factura> findByFechaFacturaBetweenAndIdLocal(LocalDate fecha1, LocalDate fecha2,
			long idLocal);

	@Query("FROM Factura  WHERE fechahora_factura BETWEEN :fecha1 AND :fecha2")
	public abstract List<Factura> findByFechaFacturaBetween(LocalDate fecha1, LocalDate fecha2);

	// public abstract Set<Factura> findByFechaFacturaBetween(LocalDate fecha1,
	// LocalDate fecha2);
	public abstract Factura findByChango(Chango chango);

	@Query("FROM Factura  WHERE id_local = (:idLocal) ORDER BY fechahora_factura DESC")
	public abstract List<Factura> findByIdLocal(long idLocal);
	
	@Query("FROM Factura WHERE id_local = (:idLocal)" + 
	" AND empleado_legajo = CASE(:legajo) WHEN 0 THEN empleado_legajo ELSE (:legajo) END" + 
	" AND fechahora_factura BETWEEN :fecha1 AND :fecha2 ORDER BY fechahora_factura DESC") 
	public abstract List<Factura> findByLocalAndEmpleadoAndFechas(long idLocal, long legajo, LocalDateTime fecha1, LocalDateTime fecha2);
}