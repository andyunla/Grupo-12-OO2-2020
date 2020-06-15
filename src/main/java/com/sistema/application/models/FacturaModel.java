package com.sistema.application.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FacturaModel {

	//Atributos
	private long idFactura;
	private ClienteModel cliente;
	private ChangoModel chango;
	private LocalDateTime fechaHoraFactura;
	private double costeTotal;
	private EmpleadoModel empleado;
	private LocalModel local;
	
	
	//Constructores
	public FacturaModel() {}

	public FacturaModel(long idFactura, ClienteModel cliente, ChangoModel chango, LocalDateTime fechaHoraFactura,
			double costeTotal, EmpleadoModel empleado, LocalModel local) {
		super();
		this.idFactura = idFactura;
		this.cliente = cliente;
		this.chango = chango;
		this.fechaHoraFactura = fechaHoraFactura;
		this.costeTotal = costeTotal;
		this.empleado = empleado;
		this.local = local;
	}
	// conconstructor para crear factura en un local sin ID
	public FacturaModel(ClienteModel cliente, ChangoModel chango, LocalDateTime fechaHoraFactura,
			double costeTotal, EmpleadoModel empleado, LocalModel local) {
		super();		
		this.cliente = cliente;
		this.chango = chango;
		this.fechaHoraFactura = fechaHoraFactura;
		this.costeTotal = costeTotal;
		this.empleado = empleado;
		this.local = local;
	}
	
	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
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

	public ChangoModel getChango() {
		return chango;
	}
	public void setChango(ChangoModel chango) {
		this.chango = chango;
	}

	public LocalDateTime getFechaHoraFactura() {
		return fechaHoraFactura;
	}
	public void setFechaHoraFactura(LocalDateTime fechaHoraFactura) {
		this.fechaHoraFactura = fechaHoraFactura;
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

	public String getFechaCorta() {
		return fechaHoraFactura.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
	}

	public String getFechaHoraCorta() {
		return fechaHoraFactura.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm"));
	}
	
	//toString
	@Override
	public String toString() {
		return "FacturaModel [idFactura=" + idFactura + ", cliente=" + cliente + ", chango=" + chango
				+ ", fechaHoraFactura=" + fechaHoraFactura + ", costeTotal=" + costeTotal + ", empleado=" + empleado + "Local"+ local+ "]";
	};
	
}
