package com.sistema.application.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;


@Component("changoSesion")
public class ChangoModel {

	// Atributos
	private long idChango;
	private PedidoStockModel pedidoStock;
	private LocalModel local;
	private Set<ItemModel> listaItems;
	
	// Constructores
	public ChangoModel() {
	}

	public ChangoModel(long idChango, PedidoStockModel pedidoStock, LocalModel local) {
		super();
		this.idChango = idChango;
		this.pedidoStock = pedidoStock;
		this.local = local;
		this.listaItems = new HashSet<ItemModel>();
	}

	// Constructor para crerar un chango en un local
	public ChangoModel(LocalModel local) {
		super();
		this.pedidoStock = null;
		this.local = local;
		this.listaItems = new HashSet<ItemModel>();
	}

	public void setInstance(ChangoModel chango){
		this.idChango = chango.idChango;
		this.pedidoStock = chango.pedidoStock;
		this.local = chango.local;
		this.listaItems = new HashSet<ItemModel>();
	}

	public void clear(){
		this.idChango =0;
		this.pedidoStock = null;
		this.local =null;
		this.listaItems = null;
	}

	public boolean hasInstance(){
		return idChango != 0;
	}

	// Getters y Setters
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

	// toString
	@Override
	public String toString() {
		return "ChangoModel [idChango=" + idChango + ", pedidoStock=" + pedidoStock + ", local=" + local + "]";
	}


}
