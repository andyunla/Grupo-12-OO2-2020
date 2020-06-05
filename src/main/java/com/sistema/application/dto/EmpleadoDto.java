package com.sistema.application.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import com.sistema.application.entities.Factura;

public class EmpleadoDto extends PersonaDto implements Serializable {
	private long id;
	private long legajo;
	private String horaDesde;
	private String horaHasta;
	private double sueldoBasico;
	private double sueldoFinal;
	private long idLocal;
	private boolean tipoGerente;
	private double comisionVentaCompleta;
	private double comisionVentaExterna;
	private double comisionStockCedido;
	
	
	public EmpleadoDto() {}
	
	public EmpleadoDto(long id, String nombre, String apellido, int dni, LocalDate fechaNacimiento,
					   long legajo, String horaDesde, String horaHasta, double sueldoBasico, long idLocal,
					   boolean tipoGerente) {
		super(id, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.sueldoFinal = 0;
		this.idLocal = idLocal;
		this.tipoGerente = tipoGerente;
		this.comisionVentaCompleta = 0;
		this.comisionVentaExterna = 0;
		this.comisionStockCedido = 0;
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
	

	public double getSueldoFinal() {
		return sueldoFinal;
	}

	public void setSueldoFinal(double sueldoFinal) {
		this.sueldoFinal = sueldoFinal;
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

	public double getComisionVentaCompleta() {
		return comisionVentaCompleta;
	}

	public void setComisionVentaCompleta(double comisionVentaCompleta) {
		this.comisionVentaCompleta = comisionVentaCompleta;
	}

	public double getComisionVentaExterna() {
		return comisionVentaExterna;
	}

	public void setComisionVentaExterna(double comisionVentaExterna) {
		this.comisionVentaExterna = comisionVentaExterna;
	}

	public double getComisionStockCedido() {
		return comisionStockCedido;
	}

	public void setComisionStockCedido(double comisionStockCedido) {
		this.comisionStockCedido = comisionStockCedido;
	}

	
}
