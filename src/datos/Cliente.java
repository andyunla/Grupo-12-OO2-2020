package datos;

import java.time.LocalDate;
import java.util.Set;

public class Cliente extends Persona {
	private long idCliente;
	private String email;
	private int nroCliente;
	private Set<Factura> facturas;

	public Cliente(long idPersona, String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, int nroCliente) {
		super(idPersona, nombre, apellido, dni, fechaNacimiento);
		this.nroCliente = nroCliente;
		this.email = email;
	}

	//Getters y Setters
	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(int nroCliente) {
		this.nroCliente = nroCliente;
	}

	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}

	@Override
	public String toString() {
		return "\n\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni
				+ "\nFechaNacimiento: " + fechaNacimiento + "\nE-mail: " + email;
	}	
}
