package com.sistema.application.models;

import java.time.LocalDate;

public class LoteModel {

	//Atributos
	private long idLote;
	private int cantidadInicial;
	private int cantidadActual;
	private LocalDate fechaIngreso;
	private long idProducto;
	private boolean activo;
	
	//Constructores
	public LoteModel() {}

	public LoteModel(long idLote, int cantidadInicial, int cantidadActual, LocalDate fechaIngreso, long idProducto,
			boolean activo) {
		super();
		this.idLote = idLote;
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = fechaIngreso;
		this.idProducto = idProducto;
		this.activo = activo;
	}

	
	//Getters y Setters
	public long getIdLote() {
		return idLote;
	}
	public void setIdLote(long idLote) {
		this.idLote = idLote;
	}

	public int getCantidadInicial() {
		return cantidadInicial;
	}
	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}
	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	//toString
	@Override
	public String toString() {
		return "LoteModel [idLote=" + idLote + ", cantidadInicial=" + cantidadInicial + ", cantidadActual="
				+ cantidadActual + ", fechaIngreso=" + fechaIngreso + ", idProducto=" + idProducto + ", activo="
				+ activo + "]";
	};
	
}
