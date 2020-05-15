package com.sistema.application.models;

import java.time.LocalDate;

public class LoteModel {

	//Atributos
	private long idLote;
	private int cantidadInicial;
	private int cantidadActual;
	private LocalDate fechaIngreso;
	private ProductoModel producto;
	private boolean activo;
	private LocalModel local;
	
	//Constructores
	public LoteModel() {}

	public LoteModel(long idLote, int cantidadInicial, int cantidadActual, LocalDate fechaIngreso,
			ProductoModel producto,LocalModel local ) {
		super();
		this.idLote = idLote;
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
		this.activo = true;
		this.local = local;
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

	public ProductoModel getProductoModel() {
		return producto;
	}
	public void setProductoModel(ProductoModel producto) {
		this.producto = producto;
	}

	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}

	//toString
	@Override
	public String toString() {
		return "LoteModel [idLote=" + idLote + ", cantidadInicial=" + cantidadInicial + ", cantidadActual="
				+ cantidadActual + ", fechaIngreso=" + fechaIngreso + ", producto=" + producto + ", activo="
				+ activo + "]";
	};
	
}
