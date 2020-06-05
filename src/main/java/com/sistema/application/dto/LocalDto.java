package com.sistema.application.dto;

import java.io.Serializable;
import java.util.Set;

public class LocalDto implements Serializable {
	private long idLocal;
	private String nombreLocal;
	private double latitud;
	private double longitud;
	private String direccion;
	private int telefono;
	private Long gerenteLegajo;
	private Set<EmpleadoDto> listaEmpleados;
	
	public LocalDto() {}

	public LocalDto(long idLocal, String nombreLocal, double latitud, double longitud, String direccion, int telefono,
			Long gerenteLegajo) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerenteLegajo = gerenteLegajo;
	}

	public LocalDto(long idLocal, String nombreLocal, double latitud, double longitud, String direccion, int telefono,
			Long gerenteLegajo, Set<EmpleadoDto> listaEmpleados) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerenteLegajo = gerenteLegajo;
		this.listaEmpleados = listaEmpleados;
	}

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
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

	public Long getGerenteLegajo() {
		return gerenteLegajo;
	}

	public void setGerenteLegajo(Long gerenteLegajo) {
		this.gerenteLegajo = gerenteLegajo;
	}

	public Set<EmpleadoDto> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(Set<EmpleadoDto> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
}
