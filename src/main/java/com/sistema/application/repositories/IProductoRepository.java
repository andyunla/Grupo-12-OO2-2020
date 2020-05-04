package com.sistema.application.repositories;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends  JpaRepository<Producto, Serializable> {
	public abstract Producto findByIdProducto(long idProducto);
	public abstract Producto findByNombre(String nombre);
}
