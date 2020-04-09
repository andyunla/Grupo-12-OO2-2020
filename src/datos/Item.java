package datos;

public class Item {
	private int idItem;
	private int cantidad;
	private Producto producto;
	
	public Item(int idItem, int cantidad, Producto producto) {
		this.idItem = idItem;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	//Getters y Setters
	public int getIdItem() {
		return idItem;
	}
	
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}


	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "\nItem: " + producto.getNombre()+"\nCantidad: " + cantidad+"\n" ;
	}
}
