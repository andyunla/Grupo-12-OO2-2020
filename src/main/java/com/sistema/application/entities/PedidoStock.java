package com.sistema.application.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="pedido_stock")
public class PedidoStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido_stock")
	private long idPedidoStock;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_producto", nullable=false)
	private Producto producto;

	@Column(name="cantidad", nullable=false)
	private int cantidad;

	@Column(name="aceptado", nullable=false)
	private boolean aceptado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitante_id", nullable=false)
	private Empleado empleadoSolicitante;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="oferente_id", nullable=true)
	private Empleado empleadoOferente;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pedidoStock")
	private Set<Chango> listaChangos;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="pedido")
	private Set<DetalleNotificacion> listaDetallesNotificaciones;
	
	@Column(name="facturado", nullable=false)
	private boolean facturado;

	public PedidoStock() {}

	public PedidoStock(Producto producto, int cantidad, boolean aceptado, Empleado empleadoSolicitante, Empleado empleadoOferente, boolean facturado) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.aceptado = aceptado;
		this.empleadoSolicitante = empleadoSolicitante;
		this.empleadoOferente = empleadoOferente;
		this.facturado = facturado;
	}

	//Constructor usado por el converter
	public PedidoStock(long idPedidoStock, Producto producto, int cantidad, boolean aceptado, Empleado empleadoSolicitante, Empleado empleadoOferente, boolean facturado) {
		this.idPedidoStock = idPedidoStock;
		this.producto = producto;
		this.cantidad = cantidad;
		this.aceptado = aceptado;
		this.empleadoSolicitante = empleadoSolicitante;
		this.empleadoOferente = empleadoOferente;
		this.facturado = facturado;
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

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public Empleado getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}

	public void setEmpleadoSolicitante(Empleado empleadoSolicitante) {
		this.empleadoSolicitante = empleadoSolicitante;
	}

	public Empleado getEmpleadoOferente() {
		return empleadoOferente;
	}

	public void setEmpleadoOferente(Empleado empleadoOferente) {
		this.empleadoOferente = empleadoOferente;
	}

	public Set<Chango> getListaChangos() {
		return listaChangos;
	}

	public void setListaChangos(Set<Chango> listaChangos) {
		this.listaChangos = listaChangos;
	}

	public Set<DetalleNotificacion> getListaDetallesNotificaciones() {
		return listaDetallesNotificaciones;
	}

	public void setListaDetallesNotificaciones(Set<DetalleNotificacion> listaDetallesNotificaciones) {
		this.listaDetallesNotificaciones = listaDetallesNotificaciones;
	}
	

	public boolean isFacturado() {
		return facturado;
	}

	public void setFacturado(boolean facturado) {
		this.facturado = facturado;
	}

	@Override
	public String toString() {
		return "PedidoStock [idPedidoStock=" + idPedidoStock + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", empleadoSolicitante=" + empleadoSolicitante + ", empleadoOferente=" + empleadoOferente+ ", aceptado=" + aceptado + "]";
	}
}
