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
@Table(name="chango")
public class Chango implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_chango")
	private long idChango;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="chango")
	private Set<Item> listaItems;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="chango")
	private Set<Factura> listaFacturas;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pedido_stock", nullable=true)
	private PedidoStock pedidoStock;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_local")
	private Local local;

	public Chango() {}
	
	public Chango(PedidoStock pedidoStock, Local local) {
		super();
		this.pedidoStock = pedidoStock;
		this.local = local;
	}
	//contructor converter
	public Chango(long idChango, PedidoStock pedidoStock, Local local) {
		super();
		this.idChango = idChango;
		this.pedidoStock = pedidoStock;
		this.local = local;
	}

	//Constructor converter para chango con items
	public Chango(long idChango, PedidoStock pedidoStock, Local local, Set<Item>listaItems) {
		super();
		this.idChango = idChango;
		this.pedidoStock = pedidoStock;
		this.local = local;
		this.listaItems = listaItems;
	}

	//Getters y Setters
	public long getIdChango() {
		return idChango;
	}

	protected void setIdChango(long idChango) {
		this.idChango = idChango;
	}

	public Set<Item> getListaItems() {
		return listaItems;
	}

	public void setListaItems(Set<Item> listaItems) {
		this.listaItems = listaItems;
	}

	public PedidoStock getPedidostock() {
		return pedidoStock;
	}

	public void setPedidostock(PedidoStock pedidoStock) {
		this.pedidoStock = pedidoStock;
	}

	public Set<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}
	
	public boolean equals (Chango chango){
		return chango.getIdChango()==this.getIdChango();
	}
	
	@Override
	public String toString() {
		return "\n\nCHANGO: " + idChango + listaItems.toString();
		//return "Chango [idChango=" + idChango + ", pedidoStock=" + pedidoStock + "]";
	}
}
