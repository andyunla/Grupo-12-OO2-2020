package com.sistema.application.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

	public ItemModel traerItem(long idItem) {
		ItemModel obj = null;
		Iterator<ItemModel> itr = listaItems.iterator();
		while (obj == null && itr.hasNext()) {
			ItemModel item = itr.next();
			if (item.getIdItem() == idItem) {
				obj = itr.next();
			}
		}
		return obj;
	}

	public boolean crearItem(int cantidad, ProductoModel producto) {
		// Le saqué la creación del id porque eso lo hace la base de datos
		if (traerItem(producto) != null) {
			traerItem(producto).setCantidad(traerItem(producto).getCantidad() + cantidad);
		} else {
			listaItems.add(new ItemModel(cantidad, producto, this));
		}
		return true;
	}

	public boolean eliminarItem(int cantidad, ProductoModel producto) throws Exception {
		if (traerItem(producto) == null)
			throw new Exception("El producto " + producto.getNombre() + " no existe");
		if (traerItem(producto).getCantidad() - cantidad <= 0)
			listaItems.remove(traerItem(producto));
		else
			traerItem(producto).setCantidad(traerItem(producto).getCantidad() - cantidad);
		return true;
	}

	/*****************************************************************************************************************/
	public double calcularTotalChango() {
		double total = 0;
		Iterator<ItemModel> itr = listaItems.iterator();
		while (itr.hasNext()) {
			ItemModel itemActual = itr.next();
			total += itemActual.getCantidad() *itemActual.getProductoModel().getPrecio();
		}
		return total;
	}
}
