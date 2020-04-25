package com.sistema.application.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ClienteModel extends PersonaModel {
	private long idCliente;
	private String email;
	private int nroCliente;
	private Set<FacturaModel> listaFacturas;

	public ClienteModel() {}

	public ClienteModel(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, int nroCliente) {
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

	public Set<FacturaModel> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<FacturaModel> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	@Override
	public String toString() {
		return "\n\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni
				+ "\nFechaNacimiento: " + fechaNacimiento + "\nE-mail: " + email;
	}
}
