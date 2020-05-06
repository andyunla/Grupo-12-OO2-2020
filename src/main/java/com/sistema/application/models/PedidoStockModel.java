package com.sistema.application.models;

import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.ProductoModel;

public class PedidoStockModel {

	//Atributos
	private long idPedidoStock;
	private ProductoModel producto;
	private int cantidad;
	private EmpleadoModel solicitante;
	private boolean aceptado;
	private EmpleadoModel oferente;
	
	
	//Constructores
	public PedidoStockModel() {};
	
	public PedidoStockModel(long idPedidoStock, ProductoModel producto, int cantidad, EmpleadoModel solicitante,
			boolean aceptado, EmpleadoModel oferente) {
		super();
		this.idPedidoStock = idPedidoStock;
		this.producto = producto;
		this.cantidad = cantidad;
		this.solicitante = solicitante;
		this.aceptado = aceptado;
		this.oferente = oferente;
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

	public EmpleadoModel getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(EmpleadoModel solicitante) {
		this.solicitante = solicitante;
	}

	public boolean isAceptado() {
		return aceptado;
	}
	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public EmpleadoModel getOferente() {
		return oferente;
	}
	public void setOferente(EmpleadoModel oferente) {
		this.oferente = oferente;
	}

	
	//toString
	@Override
	public String toString() {
		return "PedidoStockModel [idPedidoStock=" + idPedidoStock + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", solicitante=" + solicitante + ", aceptado=" + aceptado + ", oferente=" + oferente + "]";
	}
	
}
