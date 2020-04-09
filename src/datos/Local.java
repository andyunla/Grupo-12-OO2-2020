package datos;

import java.util.*;
import java.time.*;

public class Local {
	private int idLocal;
	private String nombreLocal;
	private double latitud;
	private double longitud;
	private String direccion;
	private int telefono;
	private Empleado gerente;
	private List<Lote> listaLotes;
	private List<Empleado> listaEmpleados;
	private List<Chango> listaChangos;
	private List<Factura> listaFacturas;
	
	public Local(int idLocal, String nombreLocal, double latitud, double longitud, String direccion, int telefono) {
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente=null;
		this.listaLotes = new ArrayList<Lote>();
		this.listaEmpleados = new ArrayList<Empleado>();
		this.listaChangos = new ArrayList<Chango>();
		this.listaFacturas = new ArrayList<Factura>();
	}
	
	//Getters y Setters
	public int getIdLocal() {
		return idLocal;
	}

	public Empleado getGerente() {
		return gerente;
	}

	public void setGerente(Empleado gerente) {
		this.gerente = gerente;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public List<Lote> getListaLotes() {
		return listaLotes;
	}

	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	
	public List<Chango> getListaChangos() {
		return listaChangos;
	}
	
	public List<Factura> getListaFacturas(){
		return listaFacturas;
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//2) ALTA, BAJA Y MODIFICACION DE EMPLEADOS///////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public Empleado traerEmpleado(int legajo) {
		Empleado obj = null;		
		int i = 0;
		while(obj == null && i<listaEmpleados.size() ) {			
			if(legajo == listaEmpleados.get(i).getLegajo() ) {
				obj = listaEmpleados.get(i);
			}			
			i++;
		}
		return obj;
	}

	public boolean crearEmpleado(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
								 int legajo, LocalTime horarioDesde, LocalTime horarioHasta, double sueldoBasico) throws Exception {
		
		if(traerEmpleado(legajo) != null) throw new Exception ("Error el legajo " + legajo + " ya existe");
		
		return listaEmpleados.add(new Empleado(nombre, apellido, dni, fechaNacimiento, legajo, horarioDesde, horarioHasta, sueldoBasico, this.idLocal) );
	}		
	public boolean modificarEmpleado(String nombre, String apellido, int dni, LocalDate fechaNacimiento, 
									 int legajo, LocalTime horarioDesde, LocalTime horarioHasta, double sueldoBasico) throws Exception{
		if(traerEmpleado(legajo) == null) throw new Exception("El empleado no existe");				
		traerEmpleado(legajo).setNombre(nombre);
		traerEmpleado(legajo).setApellido(apellido);
		traerEmpleado(legajo).setFechaNacimiento(fechaNacimiento);
		traerEmpleado(legajo).setHoraDesde(horarioDesde);
		traerEmpleado(legajo).setHoraHasta(horarioHasta);
		traerEmpleado(legajo).setSueldoBasico(sueldoBasico);		
		return true;
	}

	public boolean eliminarEmpleado(int legajo) throws Exception{
		if(traerEmpleado(legajo) == null) throw new Exception("El empleado no existe");
		
		return listaEmpleados.remove(traerEmpleado(legajo) );
	}
	
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//5) ALTA, Y CONSUMO DE STOCK/////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public List<Lote> traerLote(){
		return listaLotes;
	}
	
	public Lote traerLote(int id) {
		Lote obj = null;		
		int i = 0;
		while(obj == null && i<listaLotes.size() ) {			
			if(id == listaLotes.get(i).getIdLote() ) {
				obj = listaLotes.get(i);
			}			
			i++;
		}		
		return obj;
	}
	
	public List<Lote> traerLoteActivo(Producto producto) {
		List<Lote> lista= new ArrayList<Lote>();		
		for (Lote l : listaLotes) {
			if (l.getProducto().equals(producto) && l.isActivo()) lista.add(l);
		}		
		return lista;
	}

	public boolean crearLote(int cantidadInicial, LocalDate fechaIngreso, Producto producto){
		int id = 1;
		if(!this.listaLotes.isEmpty() ) id = listaLotes.get(listaLotes.size()-1).getIdLote()+1;		
		return listaLotes.add(new Lote(id, cantidadInicial, cantidadInicial, fechaIngreso, producto) );
	}

	public boolean restarLote(Producto producto, int cantidad) {
		int i=0;
		while(i<traerLoteActivo( producto).size() && cantidad>0) {
			if(traerLoteActivo( producto).get(i).getCantidadActual() - cantidad == 0) {
				traerLoteActivo( producto).get(i).setCantidadActual(0);
				traerLoteActivo( producto).get(i).setActivo(false);
				cantidad = cantidad - traerLoteActivo( producto).get(i).getCantidadActual();
				}
			if(traerLoteActivo( producto).get(i).getCantidadActual() - cantidad >= 1) {
				traerLoteActivo( producto).get(i).setCantidadActual(traerLoteActivo( producto).get(i).getCantidadActual() - cantidad);
				traerLoteActivo( producto).get(i).setActivo(true);
				cantidad = cantidad - traerLoteActivo( producto).get(i).getCantidadActual();
				}
			else if(traerLoteActivo( producto).get(i).getCantidadActual() - cantidad < 0) {
				cantidad = cantidad - traerLoteActivo( producto).get(i).getCantidadActual();
				traerLoteActivo( producto).get(i).setCantidadActual(0);
				traerLoteActivo( producto).get(i).setActivo(false);				
				restarLote(producto, cantidad);				
				}			
			i++;
		}
		return true;
	}

	public boolean sumarLote(int idLote, int cantidad) {
		if(traerLote(idLote)!=null) {
			traerLote(idLote).setCantidadActual(traerLote(idLote).getCantidadActual() + cantidad);
			traerLote(idLote).setActivo(true);
		}
		return true;
	}

	public boolean eliminarLote(int idLote) throws Exception{
		if(traerLote(idLote) == null) throw new Exception ("Error: El lote " + idLote + " no existe");
	//	if( listaFacturas.contains(listaLotes.get(idLote).getProducto()))throw new Exception ("Error: El lote " + idLote + " no existe");
		return listaLotes.remove(traerLote(idLote) );
	}
	
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//6) CALCULO DE DIISTANCIA ENTRE LOCALES/////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	
	public double calcularDistancia(Local local) {		
//		double x = Math.pow(this.latitud-local.getLatitud() , 2);
//		double y = Math.pow(this.longitud-local.getLongitud(), 2 );
//		double distancia = Math.sqrt(x+y);		
//		return distancia;
		return Math.sqrt((Math.pow(this.latitud-local.getLatitud() , 2))+(Math.pow(this.longitud-local.getLongitud(), 2 )));
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//7) VALIDAR STOCK Y POSIBILIDADES DE LOCALES A SOLICITAR STOCK///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public int calcularStockLocal(Producto producto) {		
		int cantidadStock = 0;		
		for(Lote lo: traerLoteActivo(producto)) {
			cantidadStock = cantidadStock + lo.getCantidadActual();			
		}		
		return cantidadStock;
	}

	public boolean validarStock(Producto producto, int cantidad) {
		return calcularStockLocal(producto)>= cantidad;		
	}
	
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//12) GENERAR FACTURA/////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public List<Factura> traerFactura(){
		return listaFacturas;
	}	
	
	public Factura traerFactura(int idFactura){
		Factura obj = null;		
		int i = 0;
		while(obj == null && i<listaFacturas.size() ){			
			if(idFactura == listaFacturas.get(i).getIdFactura() ) {
				obj = listaFacturas.get(i);
			}			
			i++;
		}		
		return obj;
	}	

	public boolean crearFactura(Cliente cliente, Chango chango,LocalDate fecha, double costeTotal, Empleado empleado) {		
		int id = 1;
		if(!this.listaFacturas.isEmpty() ) id = listaFacturas.get(listaFacturas.size()-1).getIdFactura()+1;
		if (chango.getPedidostock()==null)restarChango(chango);
		return listaFacturas.add(new Factura(id, cliente, chango, fecha, costeTotal, empleado) );
	}
	
	public void restarChango(Chango chango) {
		for (Item it : chango.getListaItems()) {
			restarLote(it.getProducto(), it.getCantidad());
		}
	}
	
	public List<Factura> traerFactura (LocalDate fecha1, LocalDate fecha2) {
		List<Factura> list = new ArrayList<Factura>();		
			int i= 0;
			while (i<listaFacturas.size() && listaFacturas.get(i).getFechaFactura().isBefore(fecha2) ) {
				if(listaFacturas.get(i).getFechaFactura().isEqual(fecha1) || listaFacturas.get(i).getFechaFactura().isAfter(fecha1) )list.add(listaFacturas.get(i));
				i++;
			}
		return list;
	}
	
	/************************************************************************************************************************/
	public List<Chango> traerChango(){
		return listaChangos;
	}

	public Chango traerChango(int idChango) {
		Chango obj = null;
		int i =0;
		while ( obj == null && i < listaChangos.size() ) {
			if(idChango == listaChangos.get(i).getIdChango()) obj = listaChangos.get(i);
			i++;
		}
		return obj;
	}

	public boolean crearChango() {		
		int id = 1;
		if (!this.listaChangos.isEmpty() )id = listaChangos.get(listaChangos.size()-1).getIdChango()+1;		
		PedidoStock pedido=null;
		return listaChangos.add(new Chango(id, pedido) );
	}

//	public boolean crearChango(PedidoStock pedido) {		
//		int id = 1;
//		if (!this.listaChangos.isEmpty() )id = listaChangos.get(listaChangos.size()-1).getIdChango()+1;			
//		return listaChangos.add(new Chango(id, pedido) );
//	}	
	public boolean eliminarChango(int idChango) throws Exception {		
		if (traerChango(idChango) == null) throw new Exception ("El Chango no existe.");
		return listaChangos.remove(traerChango(idChango));
	}
	
	/****************************************************************************************************/
	public int cantidadLocalProductoVendido(Producto producto) {		
		int cantidad = 0;		
		for(Factura fa: listaFacturas ) {		
			if (fa.getChango().traerItem(producto)!=null)cantidad = cantidad + fa.getChango().traerItem(producto).getCantidad();
		}		
		return cantidad;
	}

	@Override
	public String toString() {
		return "\n\nLocal " + idLocal + ": "+"\nNombre: " + nombreLocal + "\nLatitud: " + latitud + "\nLongitud: "
				+ longitud + "\nDireccion: " + direccion + "\nTelefono: " + telefono ;
	}
}
