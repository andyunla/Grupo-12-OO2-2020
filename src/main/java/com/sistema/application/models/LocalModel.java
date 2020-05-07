package com.sistema.application.models;

import java.util.Set;

import com.sistema.application.models.LoteModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.FacturaModel;

public class LocalModel {

	//Atributo
	private long idLote;
	private String nombreLocal;
	private double latitud;
	private double longitud;
	private String direccion;
	private int telefono;
	private long idGerente;
	private Set<LoteModel> listaLotes;
	private Set<EmpleadoModel> listaEmpleados;
	private Set<ChangoModel> listaChangos;
	private Set<FacturaModel> listaFacturas;
	
	
	//Constructores
	public LocalModel() {}


	public LocalModel(long idLote, String nombreLocal, double latitud, double longitud, String direccion, int telefono,
			long idGerente, Set<LoteModel> listaLotes, Set<EmpleadoModel> listaEmpleados, Set<ChangoModel> listaChangos,
			Set<FacturaModel> listaFacturas) {
		super();
		this.idLote = idLote;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.idGerente = idGerente;
		this.listaLotes = listaLotes;
		this.listaEmpleados = listaEmpleados;
		this.listaChangos = listaChangos;
		this.listaFacturas = listaFacturas;
	}


	//Getters y Setters
	public long getIdLote() {
		return idLote;
	}
	public void setIdLote(long idLote) {
		this.idLote = idLote;
	}


	public String getNombreLocal() {
		return nombreLocal;
	}
	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}


	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}


	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public long getIdGerente() {
		return idGerente;
	}
	public void setIdGerente(long idGerente) {
		this.idGerente = idGerente;
	}


	public Set<LoteModel> getListaLotes() {
		return listaLotes;
	}
	public void setListaLotes(Set<LoteModel> listaLotes) {
		this.listaLotes = listaLotes;
	}


	public Set<EmpleadoModel> getListaEmpleados() {
		return listaEmpleados;
	}
	public void setListaEmpleados(Set<EmpleadoModel> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}


	public Set<ChangoModel> getListaChangos() {
		return listaChangos;
	}
	public void setListaChangos(Set<ChangoModel> listaChangos) {
		this.listaChangos = listaChangos;
	}


	public Set<FacturaModel> getListaFacturas() {
		return listaFacturas;
	}
	public void setListaFacturas(Set<FacturaModel> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}


	//toString
	@Override
	public String toString() {
		return "LocalModel [idLote=" + idLote + ", nombreLocal=" + nombreLocal + ", latitud=" + latitud + ", longitud="
				+ longitud + ", direccion=" + direccion + ", telefono=" + telefono + ", idGerente=" + idGerente
				+ ", listaLotes=" + listaLotes + ", listaEmpleados=" + listaEmpleados + ", listaChangos=" + listaChangos
				+ ", listaFacturas=" + listaFacturas + "]";
	};

}
