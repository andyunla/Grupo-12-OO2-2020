package com.sistema.application.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.JoinColumn;

@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = -6833167247955613395L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name="id_user")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empleado_legajo", referencedColumnName = "legajo")
	private Empleado empleado;

	@Column(unique = true)
	private String email;
	@Column(name="username", unique=true, nullable=false, length=45)
	private String username;
	@Column(name="password", nullable=false, length=60)
	private String password;
	@Column(name="enabled")
	private boolean enabled;

	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}
	
	public User(Long id, Empleado empleado, String email, String username, String password, boolean enabled) {
		super();
		this.id = id;
		this.empleado = empleado;
		this.email = email;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(Long id, Empleado empleado, String email, String username, String password, boolean enabled, Set<UserRole> userRoles) {
		super();
		this.id = id;
		this.empleado = empleado;
		this.email = email;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRoles = userRoles;
	}
	
	// Constructor usado por el Converter
	public User(Long id, Empleado empleado, String email, String username) {
		super();
		this.id = id;
		this.empleado = empleado;
		this.email = email;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", empleado=" + empleado + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", enabled=" + enabled + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", userRoles=" + userRoles + "]";
	}	
}
