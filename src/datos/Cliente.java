package datos;

import java.time.LocalDate;

public class Cliente extends Persona {
	private String email;
	private Factura factura;

	public Cliente(String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.email = email;
	}

	//Getters y Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
