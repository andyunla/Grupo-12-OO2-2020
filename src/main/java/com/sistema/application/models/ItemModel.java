package com.sistema.application.models;

public class ItemModel {

	//Atributos
	private long idItem;
	private int cantidad;
	private long idProducto;
	
	
	//Constructores
	public ItemModel() {}

	public ItemModel(long idItem, int cantidad, long idProducto) {
		super();
		this.idItem = idItem;
		this.cantidad = cantidad;
		this.idProducto = idProducto;
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

	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	
	//toString
	@Override
	public String toString() {
		return "ItemModel [idItem=" + idItem + ", cantidad=" + cantidad + ", idProducto=" + idProducto + "]";
	};
	
}
