package datos;

public class Producto {
	private long idProducto;
	private String nombre;
	private String descripcion;
	private double precio;
	private int talle;
	private Item item;
	private Lote lote;
	private PedidoStock pedidoStock;

	public Producto(long idProducto, String nombre, String descripcion, double precio, int talle) {
		this.idProducto = idProducto;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
	public PedidoStock getPedidoStock() {
		return pedidoStock;
	}

	public void setLote(PedidoStock pedidoStock) {
		this.pedidoStock = pedidoStock;
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
