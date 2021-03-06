package com.sistema.application.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="cliente")
public class Cliente extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="email", nullable=false, length=50)
	private String email;

	@GeneratedValue
	@Column(name="nro_cliente", nullable=false)
	private long nroCliente;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente")
	private Set<Factura> listaFacturas;

	public Cliente() {
		super();
	}

	public Cliente(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, long nroCliente) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.nroCliente = nroCliente;
		this.email = email;
	}
	
	//Getters y Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(long nroCliente) {
		this.nroCliente = nroCliente;
	}

	public Set<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	@Override
	public String toString() {
		return "\n\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni
				+ "\nFechaNacimiento: " + fechaNacimiento + "\nE-mail: " + email;
	}
}
