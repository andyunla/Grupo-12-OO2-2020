package com.sistema.application.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class EmpleadoModel extends PersonaModel {
	@NumberFormat(style = Style.NUMBER)
	private int legajo;
	@DateTimeFormat(pattern = "HH-mm-ss")
	private LocalTime horaDesde;
	@DateTimeFormat(pattern = "HH-mm-ss")
	private LocalTime horaHasta;
	private double sueldoBasico;
	private LocalModel local; // El local donde trabaja
	private boolean tipoEmpleado; // Para determinar si administra el local; true=gerente
	
	public EmpleadoModel() {}
	
	public EmpleadoModel(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, LocalModel local, boolean tipoEmpleado) {
		super(id, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.tipoEmpleado = tipoEmpleado;
	}

	/*
	 * Para crear un empleado normal
	 * La propiedad tipoEmpleado se establece a false
	 */
	public EmpleadoModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, LocalModel local) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.tipoEmpleado = false;
	}

	/*
	 * Para crear un gerente de un local
	 * La propiedad tipoEmpleado indica si el empleado administra el local
	 */
	public EmpleadoModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, LocalModel local, boolean tipoEmpleado) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.tipoEmpleado = tipoEmpleado;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaHasta) {
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

	public boolean isTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(boolean tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	
	@Override
	public String toString() {
		return "EmpleadoModel [legajo=" + legajo + ", horaDesde=" + horaDesde + ", horaHasta=" + horaHasta
				+ ", sueldoBasico=" + sueldoBasico + ", local=" + local + ", es gerente=" + isTipoEmpleado()
				+ ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + "]";
	}
}
