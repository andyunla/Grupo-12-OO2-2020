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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="detalle_notificacion")
public class DetalleNotificacion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="id_detalle_notificacion")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producto_id", nullable=false)
	private Producto producto;

	@Column(name="cantidad", nullable=false)
	private int cantidad;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pedido_id", nullable=true)
	private PedidoStock pedido;

	public DetalleNotificacion() {}

	public DetalleNotificacion(Long id, Producto producto, int cantidad, PedidoStock pedido) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public PedidoStock getPedido() {
		return pedido;
	}

	public void setPedido(PedidoStock pedido) {
		this.pedido = pedido;
	}
}
