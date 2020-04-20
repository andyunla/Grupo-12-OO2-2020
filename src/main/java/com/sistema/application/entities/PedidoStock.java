package com.sistema.application.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="pedidostock")
public class PedidoStock {
	@Id
	@GeneratedValue
	@Column(name="idPedidoStock")
	private long idPedidoStock;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idProducto", nullable=false)
	private Producto producto;

	@Column(name="cantidad", nullable=false)
	private int cantidad;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitante_idEmpleado", nullable=false)
	private Empleado solicitante;
	
	@Column(name="aceptado", nullable=false)
	private boolean aceptado;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="oferente_idEmpleado", nullable=false)
	private Empleado oferente;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="pedidostock")
	private Set<Chango> listaChangos;

	public PedidoStock() {}

	public PedidoStock(Producto producto, int cantidad, Empleado solicitante, boolean aceptado, Empleado oferente) {
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Empleado getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Empleado solicitante) {
		this.solicitante = solicitante;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public Empleado getOferente() {
		return oferente;
	}

	public void setOferente(Empleado oferente) {
		this.oferente = oferente;
	}

	public Set<Chango> getListaChangos() {
		return listaChangos;
	}

	public void setListaChangos(Set<Chango> listaChangos) {
		this.listaChangos = listaChangos;
	}

	@Override
	public String toString() {
		return "PedidoStock [idPedidoStock=" + idPedidoStock + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", solicitante=" + solicitante + ", aceptado=" + aceptado + ", oferente=" + oferente + "]";
	}
}
