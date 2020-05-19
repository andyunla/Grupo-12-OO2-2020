package com.sistema.application.repositories;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Factura;

@Repository("facturaRepository")
public interface IFacturaRepository  extends JpaRepository<Factura, Serializable>{
	public abstract Factura findByIdFactura(long idFactura);
	@Query("FROM Factura  WHERE id_local = :idLocal AND fecha_factura BETWEEN :fecha1 AND :fecha2")
	public abstract Set<Factura> findFacturasEntreFechasLocal(LocalDate fecha1, LocalDate fecha2, long idLocal);
	@Query("FROM Factura  WHERE  fecha_factura BETWEEN :fecha1 AND :fecha2")
	public abstract Set<Factura> findFacturasEntreFechasLocal(LocalDate fecha1, LocalDate fecha2);
}
