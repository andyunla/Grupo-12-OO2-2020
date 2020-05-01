package com.sistema.application.entities;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="lote")
public class Lote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idLote")
	private long idLote;

	@Column(name="cantidadInicial", nullable=false)
	private int cantidadInicial;

	@Column(name="cantidadActual", nullable=false)
	private int cantidadActual;

	@Column(name="fechaIngreso", nullable=false)
	private LocalDate fechaIngreso;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idProducto", nullable=false)
	private Producto producto;

	@Column(name="activo", nullable=false)
	private boolean activo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idLocal", nullable=false)
	private Local local;

	public Lote() {}

	public Lote(int cantidadInicial, int cantidadActual, LocalDate fechaIngreso, Producto producto) {
		this.cantidadInicial = cantidadInicial;
		this.cantidadActual = cantidadActual;
		this.fechaIngreso = fechaIngreso;
		this.producto = producto;
		this.activo = true;
	}

	//Getters y Setters
	public long getIdLote() {
		return idLote;
	}

	protected void setIdLote(long idLote) {
		this.idLote = idLote;
	}

	public int getCantidadInicial() {
		return cantidadInicial;
	}
	
	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Lote [idLote=" + idLote + ", cantidadInicial=" + cantidadInicial + ", cantidadActual=" + cantidadActual
				+ ", fechaIngreso=" + fechaIngreso + ", producto=" + producto + ", activo=" + activo + "]";
	}
}
