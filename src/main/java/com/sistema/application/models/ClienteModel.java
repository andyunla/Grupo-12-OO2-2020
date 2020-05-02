package com.sistema.application.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ClienteModel extends PersonaModel {
	private String email;
	private int nroCliente;
	
	public ClienteModel() {}
	
	public ClienteModel(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, int nroCliente) {
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
	public int getNroCliente() {
		return nroCliente;
	}
	public void setNroCliente(int nroCliente) {
		this.nroCliente = nroCliente;
	}

	@Override
	public String toString() {
		return "ClienteModel [email=" + email + ", nroCliente=" + nroCliente + ", getDni()=" + getDni()
				+ ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido() + ", getFechaNacimiento()="
				+ getFechaNacimiento() + "]";
	}
}
