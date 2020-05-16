package com.sistema.application.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="empleado")
public class Empleado extends Persona implements Serializable {

	@GeneratedValue
	@Column(name="legajo")
	private long legajo;

	@Column(name="horario_desde")
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private LocalTime horaDesde;

	@Column(name="horario_hasta")
	@DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
	private LocalTime horaHasta;

	@Column(name="sueldo_basico")
	private double sueldoBasico;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_local", nullable=false)
	private Local local;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="empleado")
	private Set<Factura> listaFacturas;
	
	// Lista en las que el empleado aparece como solicitante
	@OneToMany(fetch=FetchType.LAZY, mappedBy="empleadoSolicitante")
	private Set<PedidoStock> listaEmpleadoSolicitante;
	// Lista en las que el empleado aparece como oferente
	@OneToMany(fetch=FetchType.LAZY, mappedBy="empleadoOferente")
	private Set<PedidoStock> listaEmpleadoOferente;

	@Column(name="tipo_gerente", nullable=false)
	private boolean tipoGerente; // Para determinar si es el que administra el local  -> true=gerente, false=empleado

	public Empleado() {
		super();
	}

	public Empleado(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, long legajo, 
					LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, Local local, boolean tipoGerente) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.tipoGerente = tipoGerente;
	}

	public Empleado(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, long legajo, 
					LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = null;
		this.tipoGerente = false;
	}

	public Empleado(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, long legajo, 
					LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, boolean tipoGerente) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = null;
		this.tipoGerente = tipoGerente;
	}

	//Getters y Setters
	public long getLegajo() {
		return legajo;
	}

	public void setLegajo(long legajo) {
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

	public Set<PedidoStock> getListaEmpleadoSolicitante() {
		return listaEmpleadoSolicitante;
	}

	public void setListaEmpleadoSolicitante(Set<PedidoStock> listaEmpleadoSolicitante) {
		this.listaEmpleadoSolicitante = listaEmpleadoSolicitante;
	}

	public Set<PedidoStock> getListaEmpleadoOferente() {
		return listaEmpleadoOferente;
	}

	public void setListaEmpleadoOferente(Set<PedidoStock> listaEmpleadoOferente) {
		this.listaEmpleadoOferente = listaEmpleadoOferente;
	}

	public Local getLocal() {
		return local;
	}

	public void setTipoGerente(boolean tipoGerente) {
		this.tipoGerente = tipoGerente;
	}

	public boolean isTipoGerente() {
		return tipoGerente;
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
				+ "\nSueldoBasico: " + sueldoBasico  + "\nEs gerente: " + isTipoGerente();
	}
}
