package com.sistema.application.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Chango;
import com.sistema.application.entities.Local;

@Repository("changoRepository")
public interface IChangoRepository extends JpaRepository<Chango, Serializable> {
	public abstract Chango findByIdChango(long idChango);

	public abstract List<Chango> findByLocalOrderByIdChangoDesc(Local local); 
	
	public abstract List<Chango> findByLocal(Local local);
}
