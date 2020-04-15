package datos;

import java.util.Set;

public class PedidoStock {
	private long idPedidoStock;
	private Producto producto;
	private int cantidad;
	private Empleado solicitante;
	private boolean aceptado;
	private Empleado oferente;
	private Set<Chango> listaChangos;

	public PedidoStock(Producto producto, int cantidad, Empleado solicitante, boolean aceptado, Empleado oferente) {
		this.idPedidoStock = idPedidoStock;
		this.producto = producto;
		this.cantidad = cantidad;
		this.solicitante = solicitante;
		this.aceptado = aceptado;
		this.oferente = oferente;
	}

	//Getters y Setters
	public long getIdPedidoStock() {
		return idPedidoStock;
	}

	protected void setIdPedidoStock(long idPedidoStock) {
		this.idPedidoStock = idPedidoStock;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Empleado getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Empleado solicitante) {
		this.solicitante = solicitante;
	}

	public boolean isAceptado() {
		return aceptado;
	}

	public void setAceptado(boolean aceptado) {
		this.aceptado = aceptado;
	}

	public Empleado getOferente() {
		return oferente;
	}

	public void setOferente(Empleado oferente) {
		this.oferente = oferente;
	}

	public Set<Chango> getListaChangos() {
		return listaChangos;
	}

	public void setListaChangos(Set<Chango> listaChangos) {
		this.listaChangos = listaChangos;
	}

	@Override
	public String toString() {
		return "PedidoStock [idPedidoStock=" + idPedidoStock + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", solicitante=" + solicitante + ", aceptado=" + aceptado + ", oferente=" + oferente + "]";
	}
}
