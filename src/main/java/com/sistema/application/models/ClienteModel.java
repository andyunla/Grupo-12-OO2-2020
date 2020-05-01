package com.sistema.application.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ClienteModel {
	private int dni;
	private String nombre;
	private String apellido;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNac;
	private String email;
	private int nroCliente;
	
	public ClienteModel() {}
	
	public ClienteModel(int dni, String nombre, String apellido, LocalDate fechaNac, String email, int nroCliente) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.email = email;
		this.nroCliente = nroCliente;
	}
	
	public ClienteModel(String nombre, String apellido, LocalDate fechaNac, String email, int nroCliente) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.email = email;
		this.nroCliente = nroCliente;
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
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
		return "ClientModel [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac
				+ ", email=" + email + ", nroCliente=" + nroCliente + "]";
	}
	
}
