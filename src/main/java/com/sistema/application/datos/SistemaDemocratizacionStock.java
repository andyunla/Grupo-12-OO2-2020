package datos;

import java.time.LocalDate;
import java.util.*;

public class SistemaDemocratizacionStock {
	private List<Producto> listaProductos;
	private List<PedidoStock> listaPedidosStock;
	private List<Local> listaLocales;
	private List<Cliente> listaClientes;
	
	public SistemaDemocratizacionStock() {
		this.listaProductos = new ArrayList<Producto>();
		this.listaPedidosStock = new ArrayList<PedidoStock>();
		this.listaLocales = new ArrayList<Local>();
		this.listaClientes = new ArrayList<Cliente>();
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public List<PedidoStock> getListaPedidosStock() {
		return listaPedidosStock;
	}

	public List<Local> getListaLocales() {
		return listaLocales;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//1) ALTA, BAJA Y MODIFICACION DE LOCALES/////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public List<Local> traerLocal(){
			return listaLocales;
		}
	public Local traerLocal(long idLocal){
		int x = 0;
		Local local = null;
		while(local == null && x < listaLocales.size()) {
			if(idLocal == listaLocales.get(x).getIdLocal()) local = listaLocales.get(x);
			x++;
		}
		return local;
	}

	public Local traerLocal(double latitud, double longitud){
		int x = 0;
		Local local = null;
		while(local == null && x < listaLocales.size()) {
			if(listaLocales.get(x).getLongitud()==(longitud) && listaLocales.get(x).getLatitud()==(latitud)) local = listaLocales.get(x);
			x++;
		}
		return local;
	}
	
	
	public boolean crearLocal(String nombreLocal, double latitud, double longitud, String direccion, int telefono) throws Exception{
		if(traerLocal(latitud, longitud)!=null) throw new Exception ("El local ya existe");
		
		long id = 1;
		if (!this.listaLocales.isEmpty() ) id = listaLocales.get(listaLocales.size()-1).getIdLocal()+1;
		
		return listaLocales.add(new Local(nombreLocal, latitud, longitud, direccion, telefono));
	}

	
	public boolean modificarLocal(int idLocal, String nombreLocal, double latitud, double longitud, String direccion, int telefono) throws Exception{
		Local local = traerLocal(idLocal);
		if(local==null) throw new Exception ("El local " + idLocal + " no existe");
		local.setNombreLocal(nombreLocal);
		local.setLatitud(latitud);
		local.setLongitud(longitud);
		local.setDireccion(direccion);
		local.setTelefono(telefono);
		return true;
	}

	
	public boolean eliminarLocal(int idLocal) throws Exception{
		if(traerLocal(idLocal)==null) throw new Exception ("El local " + idLocal + " no existe");
		return listaLocales.remove(traerLocal(idLocal));
	}
	
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//3) ALTA, BAJA Y MODIFICACION DE CLIENTES////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	
	public Cliente traerCliente(int dni){
		Cliente cliente = null;		
		int i = 0;
		while(cliente == null && i<listaClientes.size() ){
			if(dni == listaClientes.get(i).getDni()) cliente = listaClientes.get(i);
			i++;
		}		
		return cliente;
	}

	public boolean crearCliente(String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email, int nroCliente) throws Exception{
		if(traerCliente(dni) != null) throw new Exception ("Error el cliente con dni " + dni + " ya existe");		
		long idPersona = 1; // DEBUG
		return listaClientes.add(new Cliente(idPersona, nombre, apellido, dni, fechaNacimiento, email, nroCliente));
	}

	public boolean modificarCliente(String nombre, String apellido, int dni, LocalDate fechaNacimiento, String email) throws Exception{
		if(traerCliente(dni) == null) throw new Exception("\nEl cliente no existe");
		
		traerCliente(dni).setNombre(nombre);
		traerCliente(dni).setApellido(apellido);
		traerCliente(dni).setFechaNacimiento(fechaNacimiento);
		traerCliente(dni).setEmail(email);
		
		return true;
	}

	public boolean eliminarCliente(int dni) throws Exception{
		if(traerCliente(dni) == null) throw new Exception("\nEl cliente no existe");
		return listaClientes.remove(traerCliente(dni));
	}
	
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//3) ALTA, BAJA Y MODIFICACION DE PRODUCTOS///////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
		
	public Producto traerProducto(int idProducto) {
		int i=0;
		Producto obj = null;
		while(obj == null && i < listaProductos.size() ) {
			if(idProducto == listaProductos.get(i).getIdProducto() ) obj = listaProductos.get(i);					
			i++;
		}
		return obj;
	}

	public Producto traerProducto(String nombre) {
		int i=0;
		Producto obj = null;
		while(obj == null && i < listaProductos.size() ) {
			if(listaProductos.get(i).getNombre().equals(nombre) ) obj = listaProductos.get(i);					
			i++;
		}
		return obj;
	}
	
	
	public boolean crearProducto(String nombre, String descripcion, double precio, int talle) throws Exception {
		if (traerProducto(nombre) != null) throw new Exception ("El producto "+nombre+" ya existe.");
		
		long id = 1;
		if (!this.listaProductos.isEmpty() ) id = listaProductos.get(listaProductos.size()-1).getIdProducto()+1;
		
		return listaProductos.add(new Producto(nombre, descripcion,  precio, talle));		 
	}
	
	public boolean modificarProducto(int idProducto, String nombre, String descripcion, double precio, int talle) throws Exception {
		if (traerProducto(idProducto) == null) throw new Exception ("El producto"+nombre+" no existe.");
		
		traerProducto(idProducto).setPrecio(precio);
		traerProducto(idProducto).setNombre(nombre);
		traerProducto(idProducto).setDescripcion(descripcion);
		traerProducto(idProducto).setTalle(talle);
		
		return true;
	}
	public boolean productoVendido(int idProducto) {
		boolean vendido= false;
			int i=0;
			while(i < traerFacturaGlobal().size() && vendido == false) {
				if(traerFacturaGlobal().get(i).getChango().traerItem(traerProducto(idProducto)) !=null) vendido=true;
				i++;
			}			
		return vendido;	
	}	
	public boolean eliminarProducto(int idProducto) throws Exception {
		if(traerProducto(idProducto) == null) throw new Exception ("El producto con ID: "+idProducto+" no existe.");
		if (productoVendido(idProducto))throw new Exception ("El producto con ID: "+idProducto+" fu� vendido.");
		return listaProductos.remove(traerProducto(idProducto));
	}
	public List<Factura> traerFacturaGlobal () {
		List<Factura> list = new ArrayList<Factura>();
		for (Local lo : listaLocales) {
			for (Factura fa : lo.getListaFacturas()) {
				list.add(fa);
			}
		}
		return list;
	}
	
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//8) GENERACION DE SOLICITUD DE USO DE STOCK DE OTRO LOCAL////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public boolean crearPedidoStock(Producto producto, int cantidad, Empleado solicitante){	
		long id = 1;
		if(!this.listaPedidosStock.isEmpty() ) id = listaPedidosStock.get(listaPedidosStock.size()-1).getIdPedidoStock() + 1;	
		return listaPedidosStock.add(new PedidoStock(producto, cantidad, solicitante, false, null) );
	}
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//9) ACEPTAR O RECHAZAR SOLICITUD DE STOCK////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public boolean modificarPedidoStock(int idPedidoStock, boolean aceptado, Empleado oferente) throws Exception{		
		if(traerPedidoStock(idPedidoStock) == null) throw new Exception ("El pedido no existe"); 		
		traerPedidoStock(idPedidoStock).setAceptado(aceptado);
		traerPedidoStock(idPedidoStock).setOferente(oferente);
		
		if (traerPedidoStock(idPedidoStock).isAceptado()) {
			traerLocal(oferente.getLocal().getIdLocal()).restarLote(traerPedidoStock(idPedidoStock).getProducto(),traerPedidoStock(idPedidoStock).getCantidad());
		}
		else { 
			eliminarPedidoStock( idPedidoStock);		
		}
		return true;
	}
	public List<PedidoStock> traerPedidoStock(){
		return listaPedidosStock;
	}	
	public PedidoStock traerPedidoStock(int idPedidoStock) {	
		PedidoStock obj = null;		
		int i = 0;
		while(obj == null && i<listaPedidosStock.size() ) {			
			if(idPedidoStock == listaPedidosStock.get(i).getIdPedidoStock() ) {
				obj = listaPedidosStock.get(i);
			}
			i++;
		}		
		return obj;
	}		
	public boolean eliminarPedidoStock(int idPedidoStock) throws Exception{
		if(traerPedidoStock(idPedidoStock) == null) throw new Exception ("El pedido no existe"); 	
		return listaPedidosStock.remove(traerPedidoStock(idPedidoStock));
	}
	
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//13) CIERRE DEL MES PARA DEFINIR EL SUELDO DE LOS EMPLEADOS//////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public double calcularSueldo(Empleado empleado) {
		double comisionCompleta =0;			
			for (Factura fa : traerFacturaMesPasado() ) {			
				if (fa.getEmpleado().equals(empleado)) {
					if(fa.getChango().getPedidostock() !=null ) {					
						if(fa.getChango().getPedidostock().getSolicitante().equals(empleado))  comisionCompleta = comisionCompleta + ((fa.getCosteTotal()*3)/100);
						}
					else {
						comisionCompleta = comisionCompleta + ((fa.getCosteTotal()*5)/100);
						}
				}	
				else {
					 if(fa.getChango().getPedidostock()!=null && fa.getChango().getPedidostock().getOferente().equals(empleado))comisionCompleta = comisionCompleta + ((fa.getCosteTotal()*2)/100);
				}
			}		
		return (empleado.getSueldoBasico()+ comisionCompleta);	
		}
	public List<Factura> traerFacturaMesPasado () {
		List<Factura> list = new ArrayList<Factura>();
		LocalDate fecha = LocalDate.now().minusMonths(1).withDayOfMonth(1);
		/* DEBUG
		for (Local lo : listaLocales) {
			int i= lo.getListaFacturas().size()-1;
			while (i>=0 && (lo.getListaFacturas().get(i).getFechaFactura().isAfter(fecha) ) ) {
				if(lo.getListaFacturas().get(i).getFechaFactura().getMonthValue()== fecha.getMonthValue()) list.add(lo.getListaFacturas().get(i));
				i--;
			}
		}
		*/
		return list;
	}
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//14) EMITIR REPORTE DE PRODUCTOS VENDIDOS ENTRE FECHAS POR LOCAL/////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public void reporte(Local local, LocalDate fecha1, LocalDate fecha2) {
		System.out.println(local.getNombreLocal());		
		for (Producto pro : listaProductos) {
			int cantidad = 0;
			for (Factura fa : local.traerFactura(fecha1, fecha2)) {
				if(fa.getChango().traerItem(pro)!=null) cantidad = cantidad + fa.getChango().traerItem(pro).getCantidad();
			}
			System.out.println("Producto: " +pro.getNombre()+ " Cantidad: "+cantidad);
			System.out.println();
		}
	}
	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//15) RANKING DE PRODUCTOS M�S VENDIDOS //////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public List<Producto> rankingProductosGlobales() throws Exception{
		if(listaProductos.size()<6) throw new Exception("La cantidad de productos no son suficientes para crear el ranking");		
		ArrayList<Producto> listaProductosMasVendidos = new ArrayList<Producto>();
		Producto aux = listaProductos.get(0);
		int i = 0;
		int j = 0;
		int k = 0;
		int flag = 0;
		int lowest = 0;
		int maximo = 0;
		
		for(i=1; i<listaProductos.size(); i++) {
			if(cantidadGlobalProductoVendido(listaProductos.get(i) ) > cantidadGlobalProductoVendido(listaProductos.get(maximo)) ) maximo = i;
			else if(cantidadGlobalProductoVendido(listaProductos.get(i) ) < cantidadGlobalProductoVendido(listaProductos.get(lowest)) ) lowest = i;			
		}
		listaProductosMasVendidos.add(listaProductos.get(maximo));

		for(i=0; i<4; i++) { //Numero de veces que se debe agregar un producto a la lista, para llegar a 5
			aux = listaProductos.get(lowest);
			for(j=0; j<listaProductos.size(); j++) {	//Recorridas por la lista para encontrar los mas vendidos, para que no haya repetidos
				if(cantidadGlobalProductoVendido(listaProductos.get(j) ) > cantidadGlobalProductoVendido(aux))  {
					while (k<listaProductosMasVendidos.size() && flag == 0) {
						if (listaProductosMasVendidos.get(k).equals(listaProductos.get(j)) ) flag = 1;
						k++;
					}
					if(flag==0) aux = listaProductos.get(j);
					flag = 0;
					k = 0;
				}
			}
			listaProductosMasVendidos.add(aux);
		}
		return listaProductosMasVendidos;
	}
	

	
	
	/****************************************************************************************************/
	public int calcularStockGlobal(Producto producto) {
		int cantidad = 0;		
		for(Local lo: listaLocales) {
			cantidad = cantidad + lo.calcularStockLocal(producto);
		}		
		return cantidad;
	}	
	
	/****************************************************************************************************/
	
	public int cantidadGlobalProductoVendido(Producto producto) {
		int cantidad = 0;
		
		for(Local lo: listaLocales) {	//Recorro todos los locales
			cantidad = cantidad + lo.cantidadLocalProductoVendido(producto);
		}
		
		return cantidad;
	}
	
	/****************************************************************************************************/
	
	
	
	/****************************************************************************************************/
	
	public Local calcularTiendaMasCercana(Local local) {
		
		double distancia;
		double menor = 0;
		Local localMasCercano = null;		
		for(Local lo: listaLocales) {
			
			if(lo != local) {
				distancia = local.calcularDistancia(lo);
				
				if(menor==0) {
					menor = distancia;
					localMasCercano = lo;
				}				
				if(distancia < menor) {
					menor = distancia;
					localMasCercano = lo;
				}
			}	
		}		
		return localMasCercano;
	}
	

	/************************************************************************************************************************/
	
}
