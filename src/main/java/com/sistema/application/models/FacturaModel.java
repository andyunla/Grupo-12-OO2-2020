package com.sistema.application.models;

import java.time.LocalDate;

import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ClienteModel;

public class FacturaModel {

	//Atributos
	private long idFactura;
	private ClienteModel cliente;
	private long idChango;
	private LocalDate fechaFactura;
	private double costeTotal;
	private long idEmpleado;
	
	
	//Constructores
	public FacturaModel() {}


	public FacturaModel(long idFactura, ClienteModel cliente, long idChango, LocalDate fechaFactura, double costeTotal,
			long idEmpleado) {
		super();
		this.idFactura = idFactura;
		this.cliente = cliente;
		this.idChango = idChango;
		this.fechaFactura = fechaFactura;
		this.costeTotal = costeTotal;
		this.idEmpleado = idEmpleado;
	}


	//Getters y Setters
	public long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}


	public ClienteModel getCliente() {
		return cliente;
	}
	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}


	public long getIdChango() {
		return idChango;
	}
	public void setIdChango(long idChango) {
		this.idChango = idChango;
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


	public long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	//toString
	@Override
	public String toString() {
		return "FacturaModel [idFactura=" + idFactura + ", cliente=" + cliente + ", idChango=" + idChango
				+ ", fechaFactura=" + fechaFactura + ", costeTotal=" + costeTotal + ", idEmpleado=" + idEmpleado + "]";
	};
	
}
