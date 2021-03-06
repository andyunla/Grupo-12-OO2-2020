package com.sistema.application.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="locales")
public class Local implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_local")
	private long idLocal;

	@Column(name="nombre_local", nullable=false, length=10)
	private String nombreLocal;

	@Column(name="latitud", nullable=false)
	private double latitud;

	@Column(name="longitud", nullable=false)
	private double longitud;

	@Column(name="direccion", nullable=false, length=50)
	private String direccion;

	@Column(name="telefono", nullable=false)
	private int telefono;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gerente_legajo", referencedColumnName = "legajo")
	private Empleado gerente;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="local")
	private Set<Lote> listaLotes;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="local")
	private Set<Empleado> listaEmpleados;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="local")
	private Set<Chango> listaChangos;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="local")
	private Set<Factura> listaFacturas;
	
	public Local() {}

	public Local(String nombreLocal, double latitud, double longitud, String direccion, int telefono) {
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = null;
	}

	// Constructor usado por el converter, ver sobre el gerente...
	public Local(long idLocal, String nombreLocal, double latitud, double longitud, String direccion, 
			int telefono, Empleado gerente) {
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		setGerente(gerente);
	}

	public Local(long idLocal, String nombreLocal, double latitud, double longitud, String direccion, int telefono) {
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = null;
	}
	
	//Getters y Setters
	public long getIdLocal() {
		return idLocal;
	}

	protected void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public Empleado getGerente() {
		return gerente;
	}

	public void setGerente(Empleado gerente) {
		this.gerente = gerente;
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

	public Set<Lote> getListaLotes() {
		return listaLotes;
	}

	public void setListaLotes(Set<Lote> listaLotes) {
		this.listaLotes = listaLotes;
	}

	public Set<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	
	public void setListaEmpleados(Set<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public Set<Chango> getListaChangos() {
		return listaChangos;
	}

	public void setListaChangos(Set<Chango> listaChangos) {
		this.listaChangos = listaChangos;
	}
	
	public Set<Factura> getListaFacturas(){
		return listaFacturas;
	}

	public void setListaFacturas(Set<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	@Override
	public String toString() {
		return "\n\nLocal " + idLocal + ": "+"\nNombre: " + nombreLocal + "\nLatitud: " + latitud + "\nLongitud: "
				+ longitud + "\nDireccion: " + direccion + "\nTelefono: " + telefono + "\nGerente: " +
				gerente ;
	}
}
