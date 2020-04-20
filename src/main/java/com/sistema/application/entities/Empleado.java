package com.sistema.application.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="empleado")
public class Empleado extends Persona {
	@Id
	@Column(name="idEmpleado")
	private long idEmpleado;

	@Column(name="legajo")
	private int legajo;

	@Column(name="horaDesde")
	private LocalTime horaDesde;

	@Column(name="horaHasta")
	private LocalTime horaHasta;

	@Column(name="sueldoBasico")
	private double sueldoBasico;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idLocal", nullable=false)
	private Local local;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="empleado")
	private Set<Factura> listaFacturas;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="empleado")
	private Set<PedidoStock> listaPedidoStock;
	
	public Empleado() {}

	public Empleado(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, int legajo, 
					LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, Local local) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
	}

	//Getters y Setters
	public long getIdEmpleado() {
		return idEmpleado;
	}

	protected void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public long getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaHasta) {
		this.horaHasta = horaHasta;
	}

	public double getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(double sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

	public Set<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Set<PedidoStock> getListaPedidoStock() {
		return listaPedidoStock;
	}

	public void setListaPedidoStock(Set<PedidoStock> listaPedidoStock) {
		this.listaPedidoStock = listaPedidoStock;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public boolean equals(Empleado empleado) {
		return this.dni == empleado.getDni();
	}

	@Override
	public String toString() {
		return "\n\nNombre: "+ this.nombre +"\nApellido: "+this.apellido +"\nDNI: "+ this.dni +"\nLegajo: " + legajo + "\nHoraDesde: " + horaDesde + "\nHoraHasta: " + horaHasta
				+ "\nSueldoBasico: " + sueldoBasico  ;
	}
}
