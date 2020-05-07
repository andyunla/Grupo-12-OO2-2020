package com.sistema.application.models;

public class PedidoStockModel {

	//Atributos
	private long idPedidoStock;
	private long idProducto;
	private int cantidad;
	private long idSolicitante;
	private boolean aceptado;
	private long idOferente;
	
	
	//Constructores
	public PedidoStockModel() {}

	public PedidoStockModel(long idPedidoStock, long idProducto, int cantidad, long idSolicitante, boolean aceptado,
			long idOferente) {
		super();
		this.idPedidoStock = idPedidoStock;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.idSolicitante = idSolicitante;
		this.aceptado = aceptado;
		this.idOferente = idOferente;
	}

	
	//Getters y Setters
	public long getIdPedidoStock() {
		return idPedidoStock;
	}
	public void setIdPedidoStock(long idPedidoStock) {
		this.idPedidoStock = idPedidoStock;
	}

	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public long getIdSolicitante() {
		return idSolicitante;
	}
	public void setIdSolicitante(long idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public boolean isAceptado() {
		return aceptado;
	}
	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public long getIdOferente() {
		return idOferente;
	}
	public void setIdOferente(long idOferente) {
		this.idOferente = idOferente;
	}

	
	//toString
	@Override
	public String toString() {
		return "PedidoStockModel [idPedidoStock=" + idPedidoStock + ", idProducto=" + idProducto + ", cantidad="
				+ cantidad + ", idSolicitante=" + idSolicitante + ", aceptado=" + aceptado + ", idOferente="
				+ idOferente + "]";
	};
	
}
