package com.sistema.application.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductoModel {
	private long idProducto;
	@NotNull
	private String nombre;
	@NotNull
	private String descripcion;
	@Min(1)
	private double precio;
	@Min(1)
	private int talle;
	
	public ProductoModel() {};

	public ProductoModel(long idProducto, String nombre, String descripcion, double precio, int talle) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.talle = talle;
	}
	
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result * (int)idProducto + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof ProductoModel) {
			ProductoModel prod = (ProductoModel) obj;
			return (this.idProducto == prod.idProducto && this.nombre == prod.nombre);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "ProductoModel [id=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", talle=" + talle + "]";
	}
}
