package com.sistema.application.models;

public class ItemModel {

	//Atributos
	private long idItem;
	private int cantidad;
	private ProductoModel productoModel;
	
	
	//Constructores
	public ItemModel() {}

	public ItemModel(long idItem, int cantidad, ProductoModel productoModel) {
		super();
		this.idItem = idItem;
		this.cantidad = cantidad;
		this.productoModel = productoModel;
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
		return productoModel;
	}

	public void setProductoModel(ProductoModel productoModel) {
		this.productoModel = productoModel;
	}

	
	//toString
	@Override
	public String toString() {
		return "ItemModel [idItem=" + idItem + ", cantidad=" + cantidad + ", productoModel=" + productoModel + "]";
	}
	
}
