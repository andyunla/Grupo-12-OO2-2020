package com.sistema.application.datos;

import java.time.*;

public class Persona {
	protected long idPersona;
	protected String nombre;
	protected String apellido;
	protected int dni;
	protected LocalDate fechaNacimiento;

	public Persona() {}

	public Persona(long idPersona) {
		super();
		this.idPersona = idPersona;
	}

	public Persona(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
	}

	//Getters y Setters
	public long getIdPersona() {
		return idPersona;
	}

	protected void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
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

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}	
}
