package com.sistema.application.dto;

public class DetalleNotificacionDto {
	private long id;
	private Long idProducto;
	private String nombreProducto;
	private int cantidad;
	private Long idPedidoStock;
	
	public DetalleNotificacionDto() {}
	
	public DetalleNotificacionDto(Long idProducto, String nombreProducto, int cantidad, Long idPedidoStock) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.idPedidoStock = idPedidoStock;
	}
	
	public DetalleNotificacionDto(long id, Long idProducto, String nombreProducto, int cantidad, Long idPedidoStock) {
		super();
		this.id = id;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.idPedidoStock = idPedidoStock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
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

	public Long getIdPedidoStock() {
		return idPedidoStock;
	}

	public void setIdPedidoStock(Long idPedidoStock) {
		this.idPedidoStock = idPedidoStock;
	}

	@Override
	public String toString() {
		return "DetallePedidoDto [id=" + id + ", idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", cantidad="
				+ cantidad + ", idPedidoStock=" + idPedidoStock + "]";
	}
}
