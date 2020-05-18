package com.sistema.application.models;

import java.util.Set;

import com.sistema.application.models.ItemModel;
import com.sistema.application.entities.Item;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.models.LocalModel;

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

}
