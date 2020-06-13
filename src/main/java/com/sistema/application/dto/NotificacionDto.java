package com.sistema.application.dto;

public class NotificacionDto {
	private long id;
	private String tipo; // Si es para hacerle un pedido a un usuario o responderle su pedido
	private boolean leido; // Si fue leído la solicitud/respuesta
	private String estado;
	private String from; // El username del usuario actual
	private String to; // El username del destinatario
	private Long toLocal; // El ID del local en caso de ser una solicitud
	private DetalleNotificacionDto detalleNotificacion;
	
	public NotificacionDto() {
		super();
	}
	
	public NotificacionDto(String tipo, boolean leido, String estado, String from, String to, Long toLocal,
			   			   DetalleNotificacionDto detalleNotificacion) {
		super();
		this.tipo = tipo;
		this.leido = leido;
		this.estado = estado;
		this.from = from;
		this.to = to;
		this.toLocal = toLocal;
		this.detalleNotificacion = detalleNotificacion;
	}
	
	public NotificacionDto(long id, String tipo, boolean leido, String estado, String from, String to, Long toLocal,
						   DetalleNotificacionDto detalleNotificacion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.leido = leido;
		this.estado = estado;
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
	public NotificacionDto(long id, String tipo, boolean leido, String estado, String from, String to) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.leido = leido;
		this.estado = estado;
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

	public boolean isLeido() {
		return leido;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
		return "NotificacionDto [id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", leido=" + leido + ", from="
				+ from + ", to=" + to + ", toLocal=" + toLocal + ", detalleNotificacion=" + detalleNotificacion + "]";
	}
}
