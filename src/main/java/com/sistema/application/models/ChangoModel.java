package com.sistema.application.models;

import java.util.Set;

import com.sistema.application.models.ItemModel;

public class ChangoModel {
	
	//Atributos
	private long idChango;
	private Set<ItemModel> listaItems;
	private long idPedidostock;
	
	//Constructores
	public ChangoModel() {}

	public ChangoModel(long idChango, Set<ItemModel> listaItems, long idPedidostock) {
		super();
		this.idChango = idChango;
		this.listaItems = listaItems;
		this.idPedidostock = idPedidostock;
	}

	
	//Getters y Setters
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

	public long getIdPedidostock() {
		return idPedidostock;
	}
	public void setIdPedidostock(long idPedidostock) {
		this.idPedidostock = idPedidostock;
	};
	
	
	
	
	
}
