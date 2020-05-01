package com.sistema.application.models;

public class ProductoModel {
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	private String talle;
	
	public ProductoModel() {};
	
	public ProductoModel( String nombre, String descripcion, double precio, String talle) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.talle = talle;
	}
	
	public ProductoModel(int id, String nombre, String descripcion, double precio, String talle) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.talle = talle;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTalle() {
		return talle;
	}
	public void setTalle(String talle) {
		this.talle = talle;
	}

	@Override
	public String toString() {
		return "ProductoModel [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", talle=" + talle + "]";
	}
	
}
