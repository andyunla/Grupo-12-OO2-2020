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

@Entity
@Table(name="empleado")
public class Empleado extends Persona implements Serializable {

	@Column(name="legajo")
	private long legajo;

	@Column(name="horario_desde")
	private LocalTime horaDesde;

	@Column(name="horario_hasta")
	private LocalTime horaHasta;

	@Column(name="sueldo_basico")
	private double sueldoBasico;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_local", nullable=false)
	private Local local;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="empleado")
	private Set<Factura> listaFacturas;
	
	@Column(name="tipo_empleado", nullable=false)
	private boolean tipoEmpleado; // Para determinar si es el que administra el local  -> true=gerente, false=empleado

	public Empleado() {
		super();
	}

	public Empleado(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, long legajo, 
					LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, Local local, boolean tipoEmpleado) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = local;
		this.tipoEmpleado = tipoEmpleado;
	}

	public Empleado(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, long legajo, 
					LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.local = null;
		this.tipoEmpleado = false;
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

	public Local getLocal() {
		return local;
	}

	public void setTipoEmpleado(boolean tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public boolean isTipoEmpleado() {
		return tipoEmpleado;
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
				+ "\nSueldoBasico: " + sueldoBasico  + "\nEs gerente: " + isTipoEmpleado();
	}
}
