package com.sistema.application.dto;

public class NotificacionDto {
	private long id;
	private String type; // Si es para hacerle un pedido a un usuario o responderle su pedido
	private boolean status;
	private String text;
	private String from; // El username del usuario actual
	private String to; // El username del destinatario
	private long toLocal; // El ID del local en caso de ser una solicitud
	private DetallePedidoDto detallePedido;
	
	public NotificacionDto() {
		super();
	}
	
	// Solicitudes
	public NotificacionDto(long id, String type, String from, long toLocal, DetallePedidoDto detallePedido) {
		super();
		this.id = id;
		this.type = type;
		this.from = from;
		this.toLocal = toLocal;
		this.detallePedido = detallePedido;
	}
	
	// Respuestas
	public NotificacionDto(long id, String type, boolean status, String text, String from, String to) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public long getToLocal() {
		return toLocal;
	}

	public void setToLocal(long toLocal) {
		this.toLocal = toLocal;
	}

	public DetallePedidoDto getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(DetallePedidoDto detallePedido) {
		this.detallePedido = detallePedido;
	}

	@Override
	public String toString() {
		return "NotificacionDto [id=" + id + ", type=" + type + ", status=" + status + ", text=" + text + ", from="
				+ from + ", to=" + to + ", toLocal=" + toLocal + ", detallePedido=" + detallePedido + "]";
	}
}
