package com.sistema.application.dto;

public class ProductoRankingDto implements Comparable<ProductoRankingDto> {
	private long idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private int talle;
	private int cantidad;
	
	public ProductoRankingDto() {}

	public ProductoRankingDto(long idProducto, String nombre, String descripcion, double precio, int talle,
			int cantidad) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.talle = talle;
		this.cantidad = cantidad;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int compareTo(ProductoRankingDto o) {
		// TODO Auto-generated method stub
		Integer i = new Integer(cantidad);
		return i.compareTo(o.getCantidad());
	}

	
	
}
