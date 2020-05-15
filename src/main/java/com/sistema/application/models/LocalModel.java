package com.sistema.application.models;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sistema.application.entities.Producto;
import com.sistema.application.services.ILoteService;

public class LocalModel {

	// Atributos
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
	//Services
	@Autowired
	@Qualifier("iLoteService")
	ILoteService iLoteService;
	// Constructores
	public LocalModel() {
	}

	public LocalModel(long idLocal, String nombreLocal, double latitud, double longitud, String direccion,
			int telefono, EmpleadoModel gerente, Set<LoteModel> listaLotes, Set<EmpleadoModel> listaEmpleados,
			Set<ChangoModel> listaChangos, Set<FacturaModel> listaFacturas) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = gerente;
		this.listaLotes = listaLotes;
		this.listaEmpleados = listaEmpleados;
		this.listaChangos = listaChangos;
		this.listaFacturas = listaFacturas;
	}

	//constructor para los converter
	public LocalModel(long idLocal, String nombreLocal, double latitud, double longitud, String direccion, int telefono,
			EmpleadoModel gerente) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = gerente;
	}

	public LocalModel(long idLocal, String nombreLocal, double latitud, double longitud, String direccion, int telefono) {
		super();
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

	public EmpleadoModel getGerente() {
		return gerente;
	}

	public void setGerente(EmpleadoModel gerente) {
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

	// toString
	@Override
	public String toString() {
		return "LocalModel [idLocal=" + idLocal + ", nombreLocal=" + nombreLocal + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", gerente=" + gerente + ", listaLotes=" + listaLotes + ", listaEmpleados="
				+ listaEmpleados + ", listaChangos=" + listaChangos + ", listaFacturas=" + listaFacturas + "]";
	}

	public double calcularDistancia(LocalModel local) {
		double radioTierra = 6371; // en kilómetros
		double dLat = Math.toRadians(local.latitud - this.latitud);
		double dLng = Math.toRadians(local.longitud - this.longitud);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2)
				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(this.latitud)) 
				* Math.cos(Math.toRadians(local.latitud));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;
	}
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//5) ALTA, Y CONSUMO DE STOCK/////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
			
	public LoteModel crearLote (int cantidadInicial ,ProductoModel producto ) {
		return iLoteService.insertOrUpdate(new LoteModel( cantidadInicial, cantidadInicial, LocalDate.now(), producto, this ));
	}
	
}
