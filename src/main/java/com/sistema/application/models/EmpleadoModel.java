package com.sistema.application.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class EmpleadoModel extends PersonaModel {
	@NumberFormat(style = Style.NUMBER)
	private long legajo;
	private String horaDesde;
	private String horaHasta;
	private double sueldoBasico;
	private LocalModel local; // El local donde trabaja
	private boolean tipoGerente; // Para determinar si administra el local; true=gerente
	
	public EmpleadoModel() {}

	public EmpleadoModel(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 long legajo, String horaDesde, String horaHasta, double sueldoBasico, LocalModel local, boolean tipoGerente) {
		super(id, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.tipoGerente = tipoGerente;
	}

	// Utilizado por el converter pero sin establecer el local
	public EmpleadoModel(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 long legajo, String horaDesde, String horaHasta, double sueldoBasico, boolean tipoGerente) {
		super(id, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = null;
		this.tipoGerente = tipoGerente;
	}

	/*
	 * Para crear un empleado normal
	 * La propiedad tipoGerente se establece a false
	 */
	public EmpleadoModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 long legajo, String horaDesde, String horaHasta, double sueldoBasico, LocalModel local) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.tipoGerente = false;
	}

	/*
	 * Para crear un gerente de un local
	 * La propiedad tipoGerente indica si el empleado administra el local
	 */
	public EmpleadoModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 long legajo, String horaDesde, String horaHasta, double sueldoBasico, LocalModel local, boolean tipoGerente) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.tipoGerente = tipoGerente;
	}

	public long getLegajo() {
		return legajo;
	}

	public void setLegajo(long legajo) {
		this.legajo = legajo;
	}

	public String getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}

	public String getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}

	public double getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(double sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}

	public boolean isTipoGerente() {
		return tipoGerente;
	}

	public void setTipoGerente(boolean tipoGerente) {
		this.tipoGerente = tipoGerente;
	}
	
	@Override
	public String toString() {
		return "EmpleadoModel [id=" + getId() + "legajo=" + legajo + ", horaDesde=" + horaDesde + ", horaHasta=" + horaHasta
				+ ", sueldoBasico=" + sueldoBasico + ", es gerente=" + isTipoGerente()
				+ ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + "]";
	}
}
