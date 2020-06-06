package com.sistema.application.dto;

public class DetalleNotificacionDto {
	private long id;
	private long idProducto;
	private int cantidad;
	
	public DetalleNotificacionDto() {}
	
	public DetalleNotificacionDto(long id, long idProducto, int cantidad) {
		super();
		this.id = id;
		this.idProducto = idProducto;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "DetallePedidoDto [id=" + id + ", idProducto=" + idProducto + ", cantidad="
				+ cantidad + "]";
	}
}
