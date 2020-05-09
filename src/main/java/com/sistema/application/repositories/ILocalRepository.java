package com.sistema.application.repositories;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Local;

@Repository("localRepository")
public interface ILocalRepository extends JpaRepository<Local, Serializable>{
	public abstract Local findByIdLocal(long idLocal);
	public abstract Local findByNombreLocal(String nombreLocal);
	public abstract Local findByDireccion(String direccion);
}
