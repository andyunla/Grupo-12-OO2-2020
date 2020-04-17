package com.sistema.application.datos;

public class Item {
	private long idItem;
	private int cantidad;
	private Producto producto;
	private Chango chango;
	
	public Item() {}

	public Item(int cantidad, Producto producto, Chango chango) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.chango = chango;
	}

	//Getters y Setters
	public long getIdItem() {
		return idItem;
	}
	
	protected void setIdItem(long idItem) {
		this.idItem = idItem;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Chango getChango() {
		return chango;
	}
	
	public void setChango(Chango chango) {
		this.chango = chango;
	}

	@Override
	public String toString() {
		return "\nItem: " + producto.getNombre()+"\nCantidad: " + cantidad+"\n" ;
	}
}
