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
	private boolean tipoAdmin = false;   //Por defecto nadie es el admin
	private LocalDto local;

	public UserDto() {}
	public UserDto(long idUser, String nombreCompleto, String username, String emailUser, long legajo, LocalDto local) {
		super();
		this.idUser = idUser;
		this.nombreCompleto = nombreCompleto;
		this.username = username;
		this.emailUser = emailUser;
		this.legajo = legajo;
		this.local = local;
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

	public boolean isTipoAdmin() {
		return tipoAdmin;
	}

	public void setTipoAdmin(boolean tipoAdmin) {
		this.tipoAdmin = tipoAdmin;
	}

	public LocalDto getLocal() {
		return local;
	}
	public void setLocal(LocalDto local) {
		this.local = local;
	}
}
