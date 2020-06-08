package com.sistema.application.dto;

public class DetalleNotificacionDto {
	private long id;
	private long idProducto;
	private String nombreProducto;
	private int cantidad;
	
	public DetalleNotificacionDto() {}
	
	public DetalleNotificacionDto(long idProducto, String nombreProducto, int cantidad) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
	}
	
	public DetalleNotificacionDto(long id, long idProducto, String nombreProducto, int cantidad) {
		super();
		this.id = id;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "DetallePedidoDto [id=" + id + ", idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", cantidad="
				+ cantidad + "]";
	}
}
