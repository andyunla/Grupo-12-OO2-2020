package com.sistema.application.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="notificacion")
public class Notificacion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="id_notificacion")
	private Long id;

	@Column(name="tipo", nullable=false, length=20)
	private String tipo; // Si es para hacerle un pedido a un usuario o responderle su pedido
	@Column(name="leido", nullable=true)
	private boolean leido;
	@Column(name="estado", nullable=true, length=20)
	private String estado;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_from", nullable=false)
	private User userFrom; // El usuario actual
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_to", nullable=true)
	private User userTo; // El destinatario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="local_to", nullable=true)
	private Local localTo; // Indica el local a donde va dirijido, en caso de ser una solicitud
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="detalle_id", referencedColumnName="id_detalle_notificacion", nullable=true)
	private DetalleNotificacion detalleNotificacion;
	
	public Notificacion() {}

	public Notificacion(Long id, String tipo, boolean leido, String estado, User userFrom, User userTo, Local localTo,
						DetalleNotificacion detalleNotificacion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.leido = leido;
		this.estado = estado;
		this.userFrom = userFrom;
		this.userTo = userTo;
		this.localTo = localTo;
		this.detalleNotificacion = detalleNotificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public Local getLocalTo() {
		return localTo;
	}

	public void setLocalTo(Local localTo) {
		this.localTo = localTo;
	}

	public DetalleNotificacion getDetalleNotificacion() {
		return detalleNotificacion;
	}

	public void setDetalleNotificacion(DetalleNotificacion detalleNotificacion) {
		this.detalleNotificacion = detalleNotificacion;
	}

	@Override
	public String toString() {
		return "Notificacion [id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", leido=" + leido + ", userFrom="
				+ userFrom + ", userTo=" + userTo + ", localTo=" + localTo + ", detalleNotificacion="
				+ detalleNotificacion + "]";
	}
}
