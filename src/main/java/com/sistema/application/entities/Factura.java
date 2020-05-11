package com.sistema.application.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="factura")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_factura")
	private long idFactura;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nro_cliente", nullable=false)
	private Cliente cliente;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_chango", nullable=false)
	private Chango chango;

	@Column(name="fecha_factura", nullable=false)
	private LocalDate fechaFactura;

	@Column(name="coste_total", nullable=false)
	private double costeTotal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empleado_legajo", nullable=false)
	private Empleado empleado;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_local", nullable=false)
	private Local local;
	
	public Factura() {}

	public Factura(Cliente cliente, Chango chango, LocalDate fechaFactura, double costeTotal, Empleado empleado, Local local) {
		this.cliente = cliente;
		this.chango = chango;
		this.fechaFactura = fechaFactura;
		this.costeTotal = costeTotal;
		this.empleado = empleado;
		this.local = local;
	}
	// constructor de converter
	public Factura(long idFactura, Cliente cliente, Chango chango, LocalDate fechaFactura, double costeTotal, Empleado empleado, Local local) {
		this.idFactura = idFactura;
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
				+ fechaFactura + ", costeTotal=" + costeTotal + ", empleado=" + empleado + ", local=" + local + "]";
	}
}
