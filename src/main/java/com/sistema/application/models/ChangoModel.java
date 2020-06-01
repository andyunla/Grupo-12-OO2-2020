package com.sistema.application.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.services.IItemService;

@Component("changoSesion")
public class ChangoModel {

	// Atributos
	private long idChango;
	private PedidoStockModel pedidoStock;
	private LocalModel local;
	private Set<ItemModel> listaItems;
	//Service
	@Autowired
	@Qualifier("itemService")
	IItemService itemService;
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
		ItemModel itm = traerItem(producto);
		if (itm != null) {												// si existe el item
			itm.setCantidad(itm.getCantidad() + cantidad); 				// le sumo la cantidad
			itemService.insertOrUpdate(itm); 							// lo actualizo en la DB
			local.restarLote(producto, cantidad); 						// resto  el lote del local
		} else {// si no existe
			ItemModel item = new ItemModel(cantidad, producto, this); 	// Creo un item con el producto y cantidad
			listaItems.add(item); 										// lo agrego a la lista de items
			itemService.insertOrUpdate(item);							// lo inserto  en la DB
			local.restarLote(producto, cantidad); 						// resto el lote del local
		}
		return true;
	}

	public boolean eliminarItem(int cantidad, ProductoModel producto) throws Exception {
		ItemModel itm = traerItem(producto);
		if (itm == null)throw new Exception("El producto " + producto.getNombre() + " no existe"); // no sé si hace falta la excepcion
		if (itm.getCantidad() - cantidad <= 0) { 							// Si la resta da cero o menos
			listaItems.remove(itm); 										// elimino el item de la lista
			itemService.remove(itm.getIdItem());							// tambien dee la DB
			local.sumarLote(producto, itm.getCantidad()); 					// esos productos los sumo al lote
		}
		else { // Si la resta no da cero o menos
			itm.setCantidad(itm.getCantidad() - cantidad); 					// Le resto la cantidad
			itemService.insertOrUpdate(itm);								// lo actualizo en la DB
			local.sumarLote(producto, cantidad);							// esos productos los sumo al lote
		}
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
