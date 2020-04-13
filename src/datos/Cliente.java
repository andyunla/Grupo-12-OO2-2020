package datos;

import java.time.LocalDate;

public class Cliente extends Persona {
	private long idCliente;
	private String email;
	private int nroCliente;
	private Factura factura;

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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	@Override
	public String toString() {
		return "\n\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni
				+ "\nFechaNacimiento: " + fechaNacimiento + "\nE-mail: " + email ;
	}	
}
