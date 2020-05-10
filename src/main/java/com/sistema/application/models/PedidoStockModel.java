package com.sistema.application.models;

import java.util.Set;

public class PedidoStockModel {

	//Atributos
	private long idPedidoStock;
	private ProductoModel producto;
	private int cantidad;
	private boolean aceptado;
	private long idSolicitante;
	private long idOferente;
	
	//Constructores
	public PedidoStockModel() {}
	
	public PedidoStockModel(long idPedidoStock, ProductoModel producto, int cantidad, boolean aceptado,
			long idSolicitante, long idOferente) {
		super();
		this.idPedidoStock = idPedidoStock;
		this.producto = producto;
		this.cantidad = cantidad;
		this.aceptado = aceptado;
		this.idSolicitante = idSolicitante;
		this.idOferente = idOferente;
	}

	
	//Getters y Setters
	public long getIdPedidoStock() {
		return idPedidoStock;
	}
	public void setIdPedidoStock(long idPedidoStock) {
		this.idPedidoStock = idPedidoStock;
	}

	public ProductoModel getProducto() {
		return producto;
	}
	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isAceptado() {
		return aceptado;
	}
	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public long getIdSolicitante() {
		return idSolicitante;
	}
	public void setIdSolicitante(long idSolicitante) {
		this.idSolicitante = idSolicitante;
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
		return "PedidoStockModel [idPedidoStock=" + idPedidoStock + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", aceptado=" + aceptado + ", idSolicitante=" + idSolicitante + ", idOferente=" + idOferente + "]";
	}
	
}
