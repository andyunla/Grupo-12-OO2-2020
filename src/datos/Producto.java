package datos;

import java.util.Set;

public class Producto {
	private long idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private int talle;
	private Set<Item> listaItems;
	private Set<Lote> listaLotes;
	private Set<PedidoStock> listaPedidoStock;

	public Producto() {}

	public Producto(String nombre, String descripcion, double precio, int talle) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.talle = talle;
	}

	//Getters y Setters
	public long getIdProducto() {
		return idProducto;
	}

	protected void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getTalle() {
		return talle;
	}

	public void setTalle(int talle) {
		this.talle = talle;
	}

	public Set<Item> getListaItems() {
		return listaItems;
	}

	public void setListaItems(Set<Item> listaItems) {
		this.listaItems = listaItems;
	}

	public Set<Lote> getListaLotes() {
		return listaLotes;
	}

	public void setListaLotes(Set<Lote> listaLotes) {
		this.listaLotes = listaLotes;
	}
	
	public Set<PedidoStock> getListaPedidoStock() {
		return listaPedidoStock;
	}

	public void setListaPedidoStock(Set<PedidoStock> listaPedidoStock) {
		this.listaPedidoStock = listaPedidoStock;
	}

	public boolean equals(Producto producto) {
		return this.idProducto == producto.getIdProducto();
	}
	
	@Override
	public String toString() {
		return "\n\nID: "+idProducto+"\nNombre: " + nombre + "\nDescripcion: " + descripcion
				+ "\nPrecio: " + precio + "\nTalle: " + talle ;
	}
}
