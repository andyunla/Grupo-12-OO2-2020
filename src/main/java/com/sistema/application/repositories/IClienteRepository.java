package com.sistema.application.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.Cliente;

@Repository("clienteRepository")
public interface IClienteRepository extends JpaRepository<Cliente, Serializable> {
	//public abstract Cliente findById(long id);
	public abstract Cliente findByNroCliente(long nroCliente);
	public abstract List<Cliente> findByNombreAndApellido(String nombre, String apellido);
	public abstract Cliente findByEmail(String email);
}
