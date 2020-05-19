package com.sistema.application.models;

import java.util.Iterator;
import java.util.Set;

public class ChangoModel {
	
	//Atributos
	private long idChango;
	private PedidoStockModel pedidoStock;
	private LocalModel local;
	private Set<ItemModel> listaItems;
	
	
	//Constructores
	public ChangoModel() {}

	public ChangoModel(long idChango, PedidoStockModel pedidoStock, LocalModel local) {
		super();
		this.idChango = idChango;
		this.pedidoStock = pedidoStock;
		this.local = local;
	}
	//Constructor para crerar un chango en un local
	public ChangoModel(LocalModel local) {
		super();		
		this.pedidoStock = null;
		this.local = local;
	}
	
	
	//Getters y Setters
	public long getIdChango() {
		return idChango;
	}
	public void setIdChango(long idChango) {
		this.idChango = idChango;
	}

	public PedidoStockModel getPedidoStock() {
		return pedidoStock;
	}
	public void setPedidoStock(PedidoStockModel pedidoStock) {
		this.pedidoStock = pedidoStock;
	}

	public LocalModel getLocal() {
		return local;
	}
	public void setLocal(LocalModel local) {
		this.local = local;
	}

	
	public Set<ItemModel> getListaItems() {
		return listaItems;
	}

	public void setListaItems(Set<ItemModel> listaItems) {
		this.listaItems = listaItems;
	}

	//toString
	@Override
	public String toString() {
		return "ChangoModel [idChango=" + idChango + ", pedidoStock=" + pedidoStock + ", local=" + local + "]";
	}

	// Métodos
	public ItemModel traerItem(ProductoModel producto) {
		ItemModel obj = null;
		Iterator<ItemModel> itr = listaItems.iterator();
		while (obj == null && itr.hasNext()) {
			ItemModel item = itr.next();
			if (item.getProductoModel().equals(producto))
				obj = itr.next();
		}
		return obj;
	}
}
