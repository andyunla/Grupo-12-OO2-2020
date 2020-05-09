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
	private boolean gerente; // Para determinar si administra el local; true=gerente
	
	public EmpleadoModel() {}
	
	public EmpleadoModel(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, LocalModel local, boolean gerente) {
		super(id, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.gerente = gerente;
	}

	/*
	 * Para crear un empleado normal
	 * La propiedad gerente se establece a false
	 */
	public EmpleadoModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, LocalModel local) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.gerente = false;
	}

	/*
	 * Para crear un gerente de un local
	 * La propiedad gerente indica si el empleado administra el local
	 */
	public EmpleadoModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, LocalModel local, boolean gerente) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.gerente = gerente;
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

	public boolean isGerente() {
		return gerente;
	}

	public void setGerente(boolean gerente) {
		this.gerente = gerente;
	}
	
	@Override
	public String toString() {
		return "EmpleadoModel [legajo=" + legajo + ", horaDesde=" + horaDesde + ", horaHasta=" + horaHasta
				+ ", sueldoBasico=" + sueldoBasico + ", local=" + local + ", es gerente=" + isGerente()
				+ ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + "]";
	}
}
