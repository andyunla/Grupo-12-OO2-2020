package com.sistema.application.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Table(name="persona")
public class Persona {
	@Id
	@GeneratedValue
	@Column(name="idPersona")
	protected long idPersona;

	@Column(name="nombre", nullable=false, length=20)
	protected String nombre;

	@Column(name="apellido", nullable=false, length=30)
	protected String apellido;

	@Column(name="dni", nullable=false)
	protected int dni;

	@Column(name="fechaNacimiento", nullable=false)
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
