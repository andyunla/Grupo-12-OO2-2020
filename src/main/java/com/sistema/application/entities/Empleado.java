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
	
	// Lista en las que el empleado aparece como oferente
	@OneToMany(fetch=FetchType.LAZY, mappedBy="oferente")
	private Set<PedidoStock> listaPedidoOferente;

	// Lista en las que el empleado aparece como solicitante
	@OneToMany(fetch=FetchType.LAZY, mappedBy="solicitante")
	private Set<PedidoStock> listaPedidoSolicitante;
	
	@OneToOne(fetch = FetchType.LAZY, optional=true)
	@JoinColumn(name = "gerente_idLocal", nullable=true)
	private Local localOwner; // Para determinar cuál es el local que dirige; si es null es un empleado normal

	public Empleado() {}

	public Empleado(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, int legajo, 
					LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, Local local, Local localOwner) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.localOwner = localOwner;
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

	public Set<PedidoStock> getListaPedidoSolicitante() {
		return listaPedidoSolicitante;
	}

	public void setListaPedidoSolicitante(Set<PedidoStock> listaPedidoSolicitante) {
		this.listaPedidoSolicitante = listaPedidoSolicitante;
	}

	public Set<PedidoStock> getListaPedidoOferente() {
		return listaPedidoOferente;
	}

	public void setListaPedidoOferente(Set<PedidoStock> listaPedidoOferente) {
		this.listaPedidoOferente = listaPedidoOferente;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocalOwner(Local localOwner) {
		this.localOwner = localOwner;
	}

	public Local getLocalOwner() {
		return localOwner;
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
