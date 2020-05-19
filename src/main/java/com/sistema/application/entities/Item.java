package com.sistema.application.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="item")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_item")
	private long idItem;

	@Column(name="cantidad", nullable=false)
	private int cantidad;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_producto", nullable=false)
	private Producto producto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_chango", nullable=false)
	private Chango chango;
	
	public Item() {}

	public Item(int cantidad, Producto producto, Chango chango) {
		this.cantidad = cantidad;
		this.producto = producto;
		this.chango = chango;
	}
	//constructor para converter LEAN
	public Item(long idItem, int cantidad, Producto producto, Chango chango) {
		this.idItem = idItem;
		this.cantidad = cantidad;
		this.producto = producto;
		this.chango = chango;
	}

	//Getters y Setters
	public long getIdItem() {
		return idItem;
	}
	
	protected void setIdItem(long idItem) {
		this.idItem = idItem;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Chango getChango() {
		return chango;
	}
	
	public void setChango(Chango chango) {
		this.chango = chango;
	}

	@Override
	public String toString() {
		return "\nItem: " + producto.getNombre()+"\nCantidad: " + cantidad+"\n" ;
	}
}
