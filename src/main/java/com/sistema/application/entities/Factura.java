package com.sistema.application.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="factura")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="id_factura")
	private long idFactura;

	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="nro_cliente", nullable=false)
	private Cliente cliente;

	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="id_chango", nullable=false)
	private Chango chango;

	@Column(name="fechahora_factura", nullable=false)
	private LocalDateTime fechaHoraFactura;

	@Column(name="coste_total", nullable=false)
	private double costeTotal;
	
	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="empleado_legajo", nullable=false, referencedColumnName = "legajo")
	private Empleado empleado;

	@ManyToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="id_local", nullable=false)
	private Local local;
	
	public Factura() {}

	public Factura(Cliente cliente, Chango chango, LocalDateTime fechaHoraFactura, double costeTotal, Empleado empleado, Local local) {
		this.cliente = cliente;
		this.chango = chango;
		this.fechaHoraFactura = fechaHoraFactura;
		this.costeTotal = costeTotal;
		this.empleado = empleado;
		this.local = local;
	}
	// constructor de converter
	public Factura(long idFactura, Cliente cliente, Chango chango, LocalDateTime fechaHoraFactura, double costeTotal, Empleado empleado, Local local) {
		this.idFactura = idFactura;
		this.cliente = cliente;
		this.chango = chango;
		this.fechaHoraFactura = fechaHoraFactura;
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
		return "Factura [idFactura=" + idFactura + ", cliente=" + cliente + ", chango=" + chango + ", fechaHoraFactura="
				+ fechaHoraFactura + ", costeTotal=" + costeTotal + ", empleado=" + empleado + ", local=" + local + "]";
	}
}
