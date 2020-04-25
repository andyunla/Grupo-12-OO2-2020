package com.sistema.application.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ProductoModel {
	private long idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private int talle;
	private Set<ItemModel> listaItems;
	private Set<LoteModel> listaLotes;
	private Set<PedidoStockModel> listaPedidoStock;

	public ProductoModel() {}

	public ProductoModel(String nombre, String descripcion, double precio, int talle) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.talle = talle;
	}

	//Getters y Setters
	public long getIdProducto() {
		return idProducto;
	}

	protected void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getTalle() {
		return talle;
	}

	public void setTalle(int talle) {
		this.talle = talle;
	}

	public Set<ItemModel> getListaItems() {
		return listaItems;
	}

	public void setListaItems(Set<ItemModel> listaItems) {
		this.listaItems = listaItems;
	}

	public Set<LoteModel> getListaLotes() {
		return listaLotes;
	}

	public void setListaLotes(Set<LoteModel> listaLotes) {
		this.listaLotes = listaLotes;
	}
	
	public Set<PedidoStockModel> getListaPedidoStock() {
		return listaPedidoStock;
	}

	public void setListaPedidoStock(Set<PedidoStockModel> listaPedidoStock) {
		this.listaPedidoStock = listaPedidoStock;
	}

	public boolean equals(ProductoModel producto) {
		return this.idProducto == producto.getIdProducto();
	}
	
	@Override
	public String toString() {
		return "\n\nID: "+idProducto+"\nNombre: " + nombre + "\nDescripcion: " + descripcion
				+ "\nPrecio: " + precio + "\nTalle: " + talle ;
	}
}
