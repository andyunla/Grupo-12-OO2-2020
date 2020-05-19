package com.sistema.application.repositories;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Chango;

@Repository("changoRepository")
public interface IChangoRepository extends JpaRepository<Chango, Serializable> {
	public abstract Chango findByIdChango(long idChango);
}
