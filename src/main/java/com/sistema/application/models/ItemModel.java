package com.sistema.application.models;

import com.sistema.application.models.ProductoModel;

public class ItemModel {

	//Atributos
	private long idItem;
	private int cantidad;
	private ProductoModel producto;
	
	//Constructores
	public ItemModel() {};
	
	public ItemModel(long idItem, int cantidad, ProductoModel producto) {
		super();
		this.idItem = idItem;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	
	//Getters y Setters
	public long getIdItem() {
		return idItem;
	}
	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoModel getProductoModel() {
		return producto;
	}
	public void setProductoModel(ProductoModel productoModel) {
		this.producto = producto;
	}

	
	//toString
	@Override
	public String toString() {
		return "ItemModel [idItem=" + idItem + ", cantidad=" + cantidad + ", productoModel=" + producto + "]";
	}
	
}
