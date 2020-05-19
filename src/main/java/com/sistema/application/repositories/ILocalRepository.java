package com.sistema.application.repositories;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Local;

@Repository("localRepository")
public interface ILocalRepository extends JpaRepository<Local, Serializable>{
	public abstract Local findByIdLocal(long idLocal);
	
	@Query("FROM Local  WHERE nombre_local=(:nombreLocal)")
	public abstract Local findByNombreLocal(String nombreLocal);
	
	@Query("FROM Local  WHERE direccion=(:direccion)")
	public abstract Local findByDireccion(String direccion);
}
