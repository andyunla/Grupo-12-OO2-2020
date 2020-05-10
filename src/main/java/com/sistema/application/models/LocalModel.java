package com.sistema.application.models;

import java.util.Set;

import com.sistema.application.models.LoteModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.FacturaModel;

public class LocalModel {

	//Atributos
	private long idLocal;
	private String nombreLocal;
	private double latitud;
	private double longitud;
	private String direccion;
	private int telefono;
	private EmpleadoModel gerente;
	private Set<LoteModel> listaLotes;
	private Set<EmpleadoModel> listaEmpleados;
	private Set<ChangoModel> listaChangos;
	private Set<FacturaModel> listaFacturas;
	
	
	//Constructores
	public LocalModel() {}

	public LocalModel(long idLocal, String nombreLocal, double latitud, double longitud, String direccion, 
					  int telefono, EmpleadoModel gerente) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = gerente;
	}

	//Getters y Setters
	public long getIdLocal() {
		return idLocal;
	}
	public void setIdLote(long idLocal) {
		this.idLocal = idLocal;
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

	public EmpleadoModel getGerente() {
		return gerente;
	}
	public void setIdGerente(EmpleadoModel gerente) {
		this.gerente = gerente;
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

	@Override
	public String toString() {
		return "LocalModel [idLote=" + idLocal + ", nombreLocal=" + nombreLocal + ", latitud=" + latitud + ", longitud="
				+ longitud + ", direccion=" + direccion + ", telefono=" + telefono + ", idGerente=" + gerente
				+ ", listaLotes=" + listaLotes + ", listaEmpleados=" + listaEmpleados + ", listaChangos=" + listaChangos
				+ ", listaFacturas=" + listaFacturas + "]";
	}
}
