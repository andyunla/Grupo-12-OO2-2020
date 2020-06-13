package com.sistema.application.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ItemModel {

	//Atributos
	private long idItem;
	@Min(1)
	private int cantidad;
	@NotNull
	private ProductoModel productoModel;
	@NotNull
	private ChangoModel changoModel;
	
	
	//Constructores
	public ItemModel() {}

	public ItemModel(long idItem, int cantidad, ProductoModel productoModel, ChangoModel changoModel) {
		super();
		this.idItem = idItem;
		this.cantidad = cantidad;
		this.productoModel = productoModel;
		this.changoModel = changoModel;
	}

	// Constructor usado para crear items desde el model
	public ItemModel(int cantidad, ProductoModel productoModel, ChangoModel changoModel) {
		super();
		this.cantidad = cantidad;
		this.productoModel = productoModel;
		this.changoModel = changoModel;
	}

	// Constructor usado para crear items de los pedidos para la vista de la factura
	public ItemModel(int cantidad, ProductoModel productoModel) {
		super();
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
	
	public ChangoModel getChangoModel() {
		return changoModel;
	}
	public void setChangoModel(ChangoModel changoModel) {
		this.changoModel = changoModel;
	}

	
	//toString
	@Override
	public String toString() {
		return "ItemModel [idItem=" + idItem + ", cantidad=" + cantidad + ", productoModel=" + productoModel
				+ ", changoModel=" + changoModel + "]";
	}
	
}
