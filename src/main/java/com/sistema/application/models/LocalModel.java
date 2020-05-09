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
	
	// Constructores
	public LocalModel() {
	}

	public LocalModel(long idLocal, String nombreLocal, double latitud, double longitud, String direccion,
			 int telefono, EmpleadoModel gerente) {
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = gerente;
	}

	// Getters y Setters
	public long getIdLocal() {
		return this.idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public String getNombreLocal() {
		return this.nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public EmpleadoModel getGerente() {
		return this.gerente;
	}

	public void setGerente(EmpleadoModel gerente) {
		this.gerente = gerente;
	}

	@Override
	public String toString() {
		return "{" +
			" idLocal='" + getIdLocal() + "'" +
			", nombreLocal='" + getNombreLocal() + "'" +
			", latitud='" + getLatitud() + "'" +
			", longitud='" + getLongitud() + "'" +
			", direccion='" + getDireccion() + "'" +
			", telefono='" + getTelefono() + "'" +
			", gerente='" + getGerente() + "'" +
			"}";
	}

}
