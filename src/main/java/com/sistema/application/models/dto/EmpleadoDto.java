package com.sistema.application.models.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class EmpleadoDto extends PersonaDto implements Serializable {
	private long id;
	private long legajo;
	private String horaDesde;
	private String horaHasta;
	private double sueldoBasico;
	private long idLocal;
	private boolean tipoGerente;
	
	public EmpleadoDto() {}
	
	public EmpleadoDto(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento,
					   long legajo, String horaDesde, String horaHasta, double sueldoBasico, long idLocal,
					   boolean tipoGerente) {
		super(id, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.idLocal = idLocal;
		this.tipoGerente = tipoGerente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public boolean isTipoGerente() {
		return tipoGerente;
	}

	public void setTipoGerente(boolean tipoGerente) {
		this.tipoGerente = tipoGerente;
	}
}
