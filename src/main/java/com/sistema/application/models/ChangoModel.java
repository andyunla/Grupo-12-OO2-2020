package com.sistema.application.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ChangoModel {
	private long idChango;
	private Set<ItemModel> listaItems;
	private Set<FacturaModel> listaFacturas;
	private PedidoStockModel pedidostock;
	private LocalModel local;

	public ChangoModel() {}
	
	public ChangoModel(PedidoStockModel pedidostock, LocalModel local) {
		super();
		this.pedidostock = pedidostock;
		this.local = local;
	}

	//Getters y Setters
	public long getIdChango() {
		return idChango;
	}

	protected void setIdChango(long idChango) {
		this.idChango = idChango;
	}

	public Set<ItemModel> getListaItems() {
		return listaItems;
	}

	public void setListaItems(Set<ItemModel> listaItems) {
		this.listaItems = listaItems;
	}

	public PedidoStockModel getPedidostock() {
		return pedidostock;
	}

	public void setPedidostock(PedidoStockModel pedidostock) {
		this.pedidostock = pedidostock;
	}

	public Set<FacturaModel> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<FacturaModel> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}
	
	public boolean equals (ChangoModel chango){
		return chango.getIdChango()==this.getIdChango();
	}
	
	@Override
	public String toString() {
		return "\n\nCHANGO: " + idChango + listaItems.toString();
		//return "Chango [idChango=" + idChango + ", pedidostock=" + pedidostock + "]";
	}
}
