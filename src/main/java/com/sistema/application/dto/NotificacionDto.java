package com.sistema.application.dto;

public class NotificacionDto {
	private long id;
	private String tipo; // Si es para hacerle un pedido a un usuario o responderle su pedido
	private boolean estado;
	private String text;
	private String from; // El username del usuario actual
	private String to; // El username del destinatario
	private Long toLocal; // El ID del local en caso de ser una solicitud
	private DetalleNotificacionDto detalleNotificacion;
	
	public NotificacionDto() {
		super();
	}
	
	public NotificacionDto(long id, String tipo, boolean estado, String text, String from, String to, Long toLocal,
						   DetalleNotificacionDto detalleNotificacion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.text = text;
		this.from = from;
		this.to = to;
		this.toLocal = toLocal;
		this.detalleNotificacion = detalleNotificacion;
	}

	// Solicitudes
	public NotificacionDto(long id, String tipo, String from, Long toLocal, DetalleNotificacionDto detalleNotificacion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.from = from;
		this.toLocal = toLocal;
		this.detalleNotificacion = detalleNotificacion;
	}
	
	// Respuestas
	public NotificacionDto(long id, String tipo, boolean estado, String text, String from, String to) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.text = text;
		this.from = from;
		this.to = to;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Long getToLocal() {
		return toLocal;
	}

	public void setToLocal(Long toLocal) {
		this.toLocal = toLocal;
	}

	public DetalleNotificacionDto getDetalleNotificacion() {
		return detalleNotificacion;
	}

	public void setDetalleNotificacion(DetalleNotificacionDto detalleNotificacion) {
		this.detalleNotificacion = detalleNotificacion;
	}

	@Override
	public String toString() {
		return "NotificacionDto [id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", text=" + text + ", from="
				+ from + ", to=" + to + ", toLocal=" + toLocal + ", detalleNotificacion=" + detalleNotificacion + "]";
	}
}
