package com.sistema.application.dto;

public class PeticionDto {
	private long id;
	private String type; // Si es para hacerle un pedido a un usuario o responderle su pedido
	private boolean status;
	private String text;
	private String from; // El username del usuario actual
	private String to; // El username del destinatario

	public PeticionDto() {
		super();
	}

	public PeticionDto(long id, String type, boolean status, String text, String from, String to) {
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
}
