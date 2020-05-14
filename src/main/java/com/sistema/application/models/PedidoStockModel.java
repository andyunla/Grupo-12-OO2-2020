package com.sistema.application.models;

import java.util.Set;

public class PedidoStockModel {

	//Atributos
	private long idPedidoStock;
	private ProductoModel producto;
	private int cantidad;
	private boolean aceptado;
	private EmpleadoModel empleado;
	
	//Constructores
	public PedidoStockModel() {}
	
	public PedidoStockModel(long idPedidoStock, ProductoModel producto, int cantidad, boolean aceptado, EmpleadoModel empleado) {
		super();
		this.idPedidoStock = idPedidoStock;
		this.producto = producto;
		this.cantidad = cantidad;
		this.aceptado = aceptado;
		this.empleado = empleado;
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

	public EmpleadoModel getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoModel empleado) {
		this.empleado = empleado;
	}	
	
	//toString
	@Override
	public String toString() {
		return "PedidoStockModel [idPedidoStock=" + idPedidoStock + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", aceptado=" + aceptado + ", empleado=" + empleado + "]";
	}
}
