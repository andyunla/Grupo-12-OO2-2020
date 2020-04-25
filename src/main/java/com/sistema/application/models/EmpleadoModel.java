package com.sistema.application.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class EmpleadoModel extends PersonaModel {
	private long idEmpleado;
	private int legajo;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private double sueldoBasico;
	private LocalModel local;
	private Set<FacturaModel> listaFacturas;
	private Set<PedidoStockModel> listaPedidoStock;
	
	public EmpleadoModel() {}

	public EmpleadoModel(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, int legajo, 
						 LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, LocalModel local) {
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

	public Set<FacturaModel> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<FacturaModel> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Set<PedidoStockModel> getListaPedidoStock() {
		return listaPedidoStock;
	}

	public void setListaPedidoStock(Set<PedidoStockModel> listaPedidoStock) {
		this.listaPedidoStock = listaPedidoStock;
	}

	public LocalModel getLocal() {
		return local;
	}

	public void setLocal(LocalModel local) {
		this.local = local;
	}

	public boolean equals(EmpleadoModel empleado) {
		return this.dni == empleado.getDni();
	}

	@Override
	public String toString() {
		return "\n\nNombre: "+ this.nombre +"\nApellido: "+this.apellido +"\nDNI: "+ this.dni +"\nLegajo: " + legajo + "\nHoraDesde: " + horaDesde + "\nHoraHasta: " + horaHasta
				+ "\nSueldoBasico: " + sueldoBasico  ;
	}
}
