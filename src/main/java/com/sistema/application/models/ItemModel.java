package com.sistema.application.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ItemModel {
	private long idItem;
	private int cantidad;
	private ProductoModel producto;
	private ChangoModel chango;
	
	public ItemModel() {}

	public ItemModel(int cantidad, ProductoModel producto, ChangoModel chango) {
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

	public ProductoModel getProducto() {
		return producto;
	}
	
	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}

	public ChangoModel getChango() {
		return chango;
	}
	
	public void setChango(ChangoModel chango) {
		this.chango = chango;
	}

	@Override
	public String toString() {
		return "\nItem: " + producto.getNombre()+"\nCantidad: " + cantidad+"\n" ;
	}
}
