package com.sistema.application.dto;

public class DetallePedidoDto {
	private long legajoSolicitante;
	private long idProducto;
	private int cantidad;
	
	public DetallePedidoDto() {}
	
	public DetallePedidoDto(long legajoSolicitante, long idProducto, int cantidad) {
		super();
		this.legajoSolicitante = legajoSolicitante;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public long getLegajoSolicitante() {
		return legajoSolicitante;
	}

	public void setLegajoSolicitante(long legajoSolicitante) {
		this.legajoSolicitante = legajoSolicitante;
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
}
