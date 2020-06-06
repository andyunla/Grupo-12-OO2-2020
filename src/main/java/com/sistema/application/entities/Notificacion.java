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
	@Column(name="estado", nullable=false)
	private boolean estado;
	@Column(name="texto", nullable=false, length=45)
	private String texto;

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
	@JoinColumn(name = "detalle_id", referencedColumnName = "id_detalle_notificacion")
	private DetalleNotificacion detalleNotificacion;
	
	public Notificacion() {}

	public Notificacion(Long id, String tipo, boolean estado, String texto, User userFrom, User userTo, Local localTo,
			DetalleNotificacion detalleNotificacion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.texto = texto;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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
}
