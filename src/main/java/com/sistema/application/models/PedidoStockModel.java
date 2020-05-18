package com.sistema.application.models;

import java.util.Set;

public class PedidoStockModel {

	//Atributos
	private long idPedidoStock;
	private ProductoModel producto;
	private int cantidad;
	private boolean aceptado;	
	private EmpleadoModel empleadoSolicitante;
	private EmpleadoModel empleadoOferente;
	
	//Constructores
	public PedidoStockModel() {}
	
	
	public PedidoStockModel(long idPedidoStock, ProductoModel producto, int cantidad, boolean aceptado,
			 EmpleadoModel empleadoSolicitante,EmpleadoModel empleadoOferente) {
		super();
		this.idPedidoStock = idPedidoStock;
		this.producto = producto;
		this.cantidad = cantidad;
		this.aceptado = aceptado;		
		this.empleadoSolicitante = empleadoSolicitante;
		this.empleadoOferente = empleadoOferente;
	}

		//para pedir stock de otro local, sin oferente con el estado en false
	public PedidoStockModel(ProductoModel producto, int cantidad, EmpleadoModel empleadoSolicitante) {
		super();		
		this.producto = producto;
		this.cantidad = cantidad;
		this.aceptado = false;
		this.empleadoSolicitante = empleadoSolicitante;
		this.empleadoOferente = null;
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
	
		
	
	public EmpleadoModel getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}


	public void setEmpleadoSolicitante(EmpleadoModel empleadoSolicitante) {
		this.empleadoSolicitante = empleadoSolicitante;
	}


	public EmpleadoModel getEmpleadoOferente() {
		return empleadoOferente;
	}


	public void setEmpleadoOferente(EmpleadoModel empleadoOferente) {
		this.empleadoOferente = empleadoOferente;
	}


	//toString
	@Override
	public String toString() {
		return "PedidoStockModel [idPedidoStock=" + idPedidoStock + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", aceptado=" + aceptado + ", empleadoSolicitante=" + empleadoSolicitante + ", empleadoOferente= "+ empleadoOferente+ "]";
	}
}
