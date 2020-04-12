package datos;

import java.time.*;

public class Factura {
	private int idFactura;
	private Cliente cliente;
	private Chango chango;
	private LocalDate fechaFactura;
	private double costeTotal;
	private Empleado empleado;
	private Local local;
	
	public Factura(int idFactura, Cliente cliente, Chango chango, LocalDate fechaFactura, double costeTotal,
			Empleado empleado) {

		this.idFactura = idFactura;
		this.cliente = cliente;
		this.chango = chango;
		this.fechaFactura = fechaFactura;
		this.costeTotal = costeTotal;
		this.empleado = empleado;
	}

	//Getters y Setters
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Chango getChango() {
		return chango;
	}

	public void setChango(Chango chango) {
		this.chango = chango;
	}

	public LocalDate getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(LocalDate fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public double getCosteTotal() {
		return costeTotal;
	}

	public void setCosteTotal(double costeTotal) {
		this.costeTotal = costeTotal;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}
	
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", cliente=" + cliente + ", chango=" + chango + ", fechaFactura="
				+ fechaFactura + ", costeTotal=" + costeTotal + ", empleado=" + empleado + "]";
	}
}
