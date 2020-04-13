package datos;

import java.time.*;

public class Empleado extends Persona {
	private long idEmpleado;
	private int legajo;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private double sueldoBasico;
	private long idLocal;
	private Factura factura;
	private Local local;
	
	public Empleado(String nombre, String apellido, int dni, LocalDate fechaNacimiento, int legajo, 
					LocalTime horaDesde, LocalTime horaHasta, double sueldoBasico, long idLocal) {
		super(nombre, apellido, dni, fechaNacimiento);
		this.legajo = legajo;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.sueldoBasico = sueldoBasico;
		this.idLocal = idLocal;
	}

	//Getters y Setters
	public long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public long getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaHasta) {
		this.horaHasta = horaHasta;
	}

	public double getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(double sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public boolean equals(Empleado empleado) {
		return this.dni == empleado.getDni();
	}

	@Override
	public String toString() {
		return "\n\nNombre: "+ this.nombre +"\nApellido: "+this.apellido +"\nDNI: "+ this.dni +"\nLegajo: " + legajo + "\nHoraDesde: " + horaDesde + "\nHoraHasta: " + horaHasta
				+ "\nSueldoBasico: " + sueldoBasico  ;
	}
}
