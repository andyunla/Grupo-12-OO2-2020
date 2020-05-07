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
	private int idLocal; // El id del local al cual pertenece
	private int gerenteIdLocal; // El id del local el cual es el gerente; si es null no administra ningún local
	
	public EmpleadoModel() {}
	
	/*
	 * Para crear un empleado normal
	 * La propiedad gerenteIdLocal se establece a 0
	 */
	public EmpleadoModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, int idLocal) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.idLocal = idLocal;
		this.gerenteIdLocal = 0;
	}

	/*
	 * Para crear un gerente de un local
	 * La propiedad gerenteIdLocal indica el id del local que administra
	 */
	public EmpleadoModel(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
						 int legajo, LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, int idLocal, int gerenteIdLocal) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.idLocal = idLocal;
		this.gerenteIdLocal = gerenteIdLocal;
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

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public int getGerenteIdLocal() {
		return gerenteIdLocal;
	}

	public void setGerenteIdLocal(int gerenteIdLocal) {
		this.gerenteIdLocal = gerenteIdLocal;
	}
	
	@Override
	public String toString() {
		return "EmpleadoModel [legajo=" + legajo + ", horaDesde=" + horaDesde + ", horaHasta=" + horaHasta
				+ ", sueldoBasico=" + sueldoBasico + ", idLocal=" + idLocal + ", gerenteIdLocal=" + gerenteIdLocal
				+ ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + "]";
	}
}
