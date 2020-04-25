package com.sistema.application.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="cliente")
public class Cliente extends Persona {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCliente")
	private long idCliente;

	@Column(name="email", nullable=false, length=50)
	private String email;

	@Column(name="nroCliente", nullable=false)
	private int nroCliente;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente")
	private Set<Factura> listaFacturas = new HashSet<Factura>();

	public Cliente() {}

	public Cliente(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, int nroCliente) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.nroCliente = nroCliente;
		this.email = email;
	}

	//Getters y Setters
	public long getIdCliente() {
		return idCliente;
	}

	protected void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(int nroCliente) {
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
