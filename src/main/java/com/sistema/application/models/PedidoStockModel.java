package com.sistema.application.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class PedidoStockModel {
	private long idPedidoStock;
	private ProductoModel producto;
	private int cantidad;
	private EmpleadoModel solicitante;
	private boolean aceptado;
	private EmpleadoModel oferente;
	private Set<ChangoModel> listaChangos;

	public PedidoStockModel() {}

	public PedidoStockModel(ProductoModel producto, int cantidad, EmpleadoModel solicitante, boolean aceptado, EmpleadoModel oferente) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.solicitante = solicitante;
		this.aceptado = aceptado;
		this.oferente = oferente;
	}

	//Getters y Setters
	public long getIdPedidoStock() {
		return idPedidoStock;
	}

	protected void setIdPedidoStock(long idPedidoStock) {
		this.idPedidoStock = idPedidoStock;
	}

	public ProductoModel getProducto() {
		return producto;
	}

	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public EmpleadoModel getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(EmpleadoModel solicitante) {
		this.solicitante = solicitante;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public EmpleadoModel getOferente() {
		return oferente;
	}

	public void setOferente(EmpleadoModel oferente) {
		this.oferente = oferente;
	}

	public Set<ChangoModel> getListaChangos() {
		return listaChangos;
	}

	public void setListaChangos(Set<ChangoModel> listaChangos) {
		this.listaChangos = listaChangos;
	}

	@Override
	public String toString() {
		return "PedidoStock [idPedidoStock=" + idPedidoStock + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", solicitante=" + solicitante + ", aceptado=" + aceptado + ", oferente=" + oferente + "]";
	}
}
