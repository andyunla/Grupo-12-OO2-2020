package com.sistema.application.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDto implements Serializable {
	private long idUser;
	private String nombreCompleto;
	private String username;
	private String emailUser;
	private long legajo;
	private boolean tipoGerente = false; // Por defecto es un empleado normal
	private long idLocal; // ID del local donde trabaja

	public UserDto() {}

	public UserDto(long idUser, String nombreCompleto, String username, String emailUser, long legajo,
			long idLocal) {
		super();
		this.idUser = idUser;
		this.nombreCompleto = nombreCompleto;
		this.username = username;
		this.emailUser = emailUser;
		this.legajo = legajo;
		this.idLocal = idLocal;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public long getLegajo() {
		return legajo;
	}

	public void setLegajo(long legajo) {
		this.legajo = legajo;
	}

	public boolean isTipoGerente() {
		return tipoGerente;
	}

	public void setTipoGerente(boolean tipoGerente) {
		this.tipoGerente = tipoGerente;
	}

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}
}
