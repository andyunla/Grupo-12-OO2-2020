package com.sistema.application.models;

import java.util.Set;

import com.sistema.application.models.ItemModel;
import com.sistema.application.models.PedidoStockModel;

public class ChangoModel {
	
	//Atributos
	private long idChango;
	private Set<ItemModel> listaItems;
	private PedidoStockModel pedidostock;
	
	//Constructores
	public ChangoModel() {};
	
	public ChangoModel(long idChango, Set<ItemModel> listaItems, PedidoStockModel pedidostock) {
		super();
		this.idChango = idChango;
		this.listaItems = listaItems;
		this.pedidostock = pedidostock;
	}

	
	//Getter y Setters
	public long getIdChango() {
		return idChango;
	}
	public void setIdChango(long idChango) {
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

	//toString
	@Override
	public String toString() {
		return "ChangoModel [idChango=" + idChango + ", listaItems=" + listaItems + ", pedidostock=" + pedidostock
				+ "]";
	}
	
}
