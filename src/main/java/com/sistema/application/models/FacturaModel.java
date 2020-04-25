package com.sistema.application.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class FacturaModel {
	private long idFactura;
	private ClienteModel cliente;
	private ChangoModel chango;
	private LocalDate fechaFactura;
	private double costeTotal;
	private EmpleadoModel empleado;
	private LocalModel local;
	
	public FacturaModel() {}

	public FacturaModel(ClienteModel cliente, ChangoModel chango, LocalDate fechaFactura, double costeTotal, EmpleadoModel empleado, LocalModel local) {
		this.cliente = cliente;
		this.chango = chango;
		this.fechaFactura = fechaFactura;
		this.costeTotal = costeTotal;
		this.empleado = empleado;
		this.local = local;
	}

	//Getters y Setters
	public long getIdFactura() {
		return idFactura;
	}

	protected void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public ChangoModel getChango() {
		return chango;
	}

	public void setChango(ChangoModel chango) {
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

	public EmpleadoModel getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoModel empleado) {
		this.empleado = empleado;
	}

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}
	
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", cliente=" + cliente + ", chango=" + chango + ", fechaFactura="
				+ fechaFactura + ", costeTotal=" + costeTotal + ", empleado=" + empleado + ", local=" + local + "]";
	}
}
