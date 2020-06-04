package com.sistema.application.models;

import java.time.LocalDate;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class ClienteModel extends PersonaModel {
	private String email;
	@NumberFormat(style = Style.NUMBER)
	private long nroCliente;
	
	public ClienteModel() {}
	
	public ClienteModel(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, long nroCliente) {
		super(id, nombre, apellido, dni, fechaNacimiento);
		this.email = email;
		this.nroCliente = nroCliente;
	}

	public ClienteModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, int nroCliente) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.email = email;
		this.nroCliente = nroCliente;
	}
	
	public ClienteModel(String nombre, String apellido, LocalDate fechaNacimiento, String email, int nroCliente) {
		super(nombre, apellido, fechaNacimiento);
		this.email = email;
		this.nroCliente = nroCliente;
	}
	
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

	@Override
	public String toString() {
		return "ClienteModel:{" +
			" id=" + this.getId() +
			" nombre=" + this.getNombre() +
			" apellido=" + this.getApellido() + 
			" dni=" + this.getDni() + 
			" fecha=" + this.getFechaNacimiento() + 
			" email='" + email + "'" +
			", nroCliente='" + nroCliente + "'" +
			"}";
	}

}
