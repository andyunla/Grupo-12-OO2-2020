package com.sistema.application.models.dto;

public class LocalDistanciaDto {
	private long idLocal;
	private String nombreLocal;
	private double distancia;
	private String direccion;
	private int telefono;
	
	public LocalDistanciaDto() {}

	public LocalDistanciaDto(long idLocal, String nombreLocal, double distancia, String direccion,
			int telefono) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.distancia = distancia;
		this.direccion = direccion;
		this.telefono = telefono;
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

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
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
}
