package com.sistema.application.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.funciones.Funciones;
import com.sistema.application.services.IChangoService;
import com.sistema.application.services.IFacturaService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.ILoteService;
import com.sistema.application.services.IPedidoStockService;
import com.sistema.application.services.IProductoService;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Component("localModel")
public class LocalModel {

	// Atributos
	private long idLocal;
	@NotNull
	private String nombreLocal;
	@Min(-90)  @Max(90)
	private double latitud;
	@Min(-180) @Max(180)
	private double longitud;
	@NotNull
	private String direccion;
	@NotNull
	private int telefono;
	@NotNull
	private EmpleadoModel gerente;
	private Set<LoteModel> listaLotes;
	private Set<EmpleadoModel> listaEmpleados;
	private Set<ChangoModel> listaChangos;
	private Set<FacturaModel> listaFacturas;
	// Services
	@Autowired
	@Qualifier("loteService")
	private ILoteService loteService;
	@Autowired
	@Qualifier("changoService")
	IChangoService changoService;
	@Autowired
	@Qualifier("facturaService")
	IFacturaService facturaService;
	@Autowired
	@Qualifier("pedidoStockService")
	IPedidoStockService pedidoStockService;
	@Autowired
	@Qualifier("productoService")
	IProductoService productoService;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;

	//Converters
	@Autowired
	@Qualifier("productoConverter")
	ProductoConverter productoConverter;

	// Constructores
	public LocalModel() {
	}

	// Setea los atributos del local cuando está siendo usado como una instancia de componente
	public void setInstance(LocalModel local){
		this.idLocal = local.idLocal;
		this.nombreLocal = local.nombreLocal;
		this.latitud = local.latitud;
		this.longitud = local.longitud;
		this.direccion = local.direccion;
		this.telefono = local.telefono;
		this.gerente = local.gerente;
	}

	public LocalModel(long idLocal, String nombreLocal, double latitud, double longitud, String direccion,
			int telefono, EmpleadoModel gerente, Set<LoteModel> listaLotes, Set<EmpleadoModel> listaEmpleados,
			Set<ChangoModel> listaChangos, Set<FacturaModel> listaFacturas) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = gerente;
		this.listaLotes = listaLotes;
		this.listaEmpleados = listaEmpleados;
		this.listaChangos = listaChangos;
		this.listaFacturas = listaFacturas;
	}

	// constructor para los converter
	public LocalModel(long idLocal, String nombreLocal, double latitud, double longitud, String direccion, int telefono,
			EmpleadoModel gerente) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = gerente;
	}

	public LocalModel(long idLocal, String nombreLocal, double latitud, double longitud, String direccion,
			int telefono) {
		super();
		this.idLocal = idLocal;
		this.nombreLocal = nombreLocal;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.telefono = telefono;
		this.gerente = null;
	}

	// Getters y Setters
	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
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

	public EmpleadoModel getGerente() {
		return gerente;
	}

	public void setGerente(EmpleadoModel gerente) {
		this.gerente = gerente;
	}

	public Set<LoteModel> getListaLotes() {
		return listaLotes;
	}

	public void setListaLotes(Set<LoteModel> listaLotes) {
		this.listaLotes = listaLotes;
	}

	public Set<EmpleadoModel> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(Set<EmpleadoModel> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public Set<ChangoModel> getListaChangos() {
		return listaChangos;
	}

	public void setListaChangos(Set<ChangoModel> listaChangos) {
		this.listaChangos = listaChangos;
	}

	public Set<FacturaModel> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(Set<FacturaModel> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	// toString
	@Override
	public String toString() {
		return "LocalModel [idLocal=" + idLocal + ", nombreLocal=" + nombreLocal + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", direccion=" + direccion + ", telefono=" + telefono + ", gerente="
				+ gerente + ", listaLotes=" + listaLotes + ", listaChangos=" + listaChangos + ", listaFacturas="
				+ listaFacturas + "]";
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 6) CALCULO DE DIISTANCIA ENTRE
	////////////////////////////////////////////////////////////////////////////////////////////////////// LOCALES//////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public List<LocalModel> localesCercanos(ProductoModel producto, int cantidad) {
		List<LocalModel> lista = new ArrayList<LocalModel>();
		List<LocalModel> listaLocales = stockDisponible(producto, cantidad);
		double[] distancia =  new double[listaLocales.size()];		// vector para guardar el valor de la distancia
		long[] id = new long[listaLocales.size()];	// vector para guardar el valor ID del Local
		for (int i = 0; i < listaLocales.size(); i++) {
			id[i] = listaLocales.get(i).getIdLocal();
			distancia[i] = calcularDistancia(listaLocales.get(i));
		}
		Funciones.orden(id, distancia);// este método ordena los dos vectores de mayor a menor usando el valor de la
										// distancia
		for (long l : id) {
			lista.add(localService.findByIdLocal(l));//agrego a la lista los productos con ID en orden
		}		
		return lista; 
	}

	// Devuelve la lista de locales con una cantidad minima de stock disponible de un producto
	public List<LocalModel> stockDisponible(ProductoModel producto, int cantidad){
		List<LocalModel> lista = new ArrayList<LocalModel>();
		for (LocalModel lo : localService.getAllModel()) {	// de todos los locales
						
			//  si no es este local y me puede dar stock le agrego a la lista
			if (lo.getIdLocal() != this.idLocal && validarStock(lo, producto, cantidad)){ 
				lista.add(lo);
			}
		}

		return lista;
	}

	public double calcularDistancia(LocalModel local) {
		double radioTierra = 6371; // en kilómetros
		double dLat = Math.toRadians(local.latitud - this.latitud);
		double dLng = Math.toRadians(local.longitud - this.longitud);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(this.latitud))
				* Math.cos(Math.toRadians(local.latitud));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 5) ALTA, Y CONSUMO DE
	////////////////////////////////////////////////////////////////////////////////////////////////////// STOCK/////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
			
	public LoteModel crearLote (int cantidadInicial ,ProductoModel producto ) {
		return loteService.insertOrUpdate(new LoteModel( cantidadInicial, cantidadInicial, LocalDate.now(), producto, this ));
	}

	public boolean restarLote(ProductoModel producto, int cantidad) {

		//traigo la lista de lotes del producto en el local
		Set<LoteModel> lista = loteService.findByLoteProductoActivo(producto.getIdProducto(), this.idLocal);
		Iterator<LoteModel> itr = lista.iterator();
		LoteModel lo = null;// creo un LoteModel objeto vacio
		while (cantidad > 0 && itr.hasNext()) {// mientras haya cantidad que restar
			lo = itr.next();
			if (lo.getCantidadActual() - cantidad <= 0) {// si la cantidad actual queda en 0 doy de baja el lote
				cantidad = cantidad - lo.getCantidadActual(); // actualizo la cantidad a restar
				lo.setCantidadActual(0);
				//lo.setActivo(false); //esta validación la agregué dentor del set cantidadActual
				loteService.insertOrUpdate(lo);// actualizo el lote en la base de datos
				}
			else if (lo.getCantidadActual() - cantidad >=1) {
				lo.setCantidadActual(lo.getCantidadActual()- cantidad);
				loteService.insertOrUpdate(lo);// actualizo el lote en la base de datos
				cantidad =0;// seteo en cero para salir del bucle, ya no hay mas que restar
			}			

		}		
		return true;
	}

	public boolean sumarLote(ProductoModel producto, int cantidad) {
		
		//traigo la lista de lotes del producto en el local
		Set<LoteModel> lista = loteService.findByLoteProductoBaja(producto.getIdProducto(), this.idLocal);
		Iterator<LoteModel> itr = lista.iterator();
		LoteModel lo = null;// creo un LoteModel objeto vacio
		while (cantidad > 0 && itr.hasNext()) {// mientras haya cantidad que restar
			lo = itr.next();
			if (lo.getCantidadActual() + cantidad >= lo.getCantidadInicial()) {// si supero la cantidad del lote con la
																				// suma, paso sumar en el siguiente lote
				cantidad = cantidad - lo.getCantidadInicial(); // actualizo la cantidad a restar
				lo.setCantidadActual(lo.getCantidadInicial());
				//lo.setActivo(true);//esta validación la agregué dentor del set cantidadActual
				loteService.insertOrUpdate(lo);// actualizo el lote en la base de datos
				}
			else if (lo.getCantidadActual() + cantidad < lo.getCantidadInicial()) {
				lo.setCantidadActual(lo.getCantidadActual()+ cantidad);
				loteService.insertOrUpdate(lo);// actualizo el lote en la base de datos
				cantidad =0;// seteo en cero para salir del bucle, ya no hay mas que sumar
			}			
	
		}		
		return true;
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 7) VALIDAR STOCK Y POSIBILIDADES DE LOCALES A SOLICITAR
	////////////////////////////////////////////////////////////////////////////////////////////////////// STOCK///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public int calcularStockLocal(ProductoModel producto) {		
		int cantidadStock = 0;		
		Set<LoteModel> lista = loteService.findByLoteProductoActivo( producto.getIdProducto(), this.idLocal );
		for(LoteModel lo : lista) {
			cantidadStock = cantidadStock + lo.getCantidadActual();			
		}		
		return cantidadStock;
	}

	public boolean validarStock(ProductoModel producto, int cantidad) {
		return calcularStockLocal(producto) >= cantidad;
	}

	/* MÉTODO QUE USAN PARA CALCULAR EL STOCK DE UN LOCAL QUE NO ES ESTA INSTANCIA (SE LO RECIBE POR PARAMETRO) */
	public int calcularStockLocal(LocalModel local, ProductoModel producto) {		
		int cantidadStock = 0;		
		Set<LoteModel> lista = loteService.findByLoteProductoActivo( producto.getIdProducto(), local.idLocal );
		for(LoteModel lo : lista) {
			cantidadStock = cantidadStock + lo.getCantidadActual();			
		}		
		return cantidadStock;
	}

	/* MÉTODO QUE USAN PARA VALIDAR EL STOCK DE UN LOCAL QUE NO ES ESTA INSTANCIA (SE LO RECIBE POR PARAMETRO) */
	public boolean validarStock(LocalModel local, ProductoModel producto, int cantidad) {
		return calcularStockLocal(local, producto)>= cantidad;		
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 8) GENERACION DE SOLICITUD DE USO DE STOCK DE OTRO
	////////////////////////////////////////////////////////////////////////////////////////////////////// LOCAL////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public boolean crearPedidoStock(ProductoModel producto, int cantidad, EmpleadoModel solicitante){	
		pedidoStockService.insertOrUpdate(new PedidoStockModel(producto, cantidad, solicitante));		
		return true;
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 9) ACEPTAR O RECHAZAR SOLICITUD DE
	////////////////////////////////////////////////////////////////////////////////////////////////////// STOCK////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public boolean modificarPedidoStock(long idPedidoStock, boolean aceptado, EmpleadoModel oferente) throws Exception{		
		PedidoStockModel pedidoStockModel = pedidoStockService.findByIdPedidoStock(idPedidoStock); //traiugo el Pedido de la base de datos
		pedidoStockModel.setEmpleadoOferente(oferente); //seteo el oferente
		pedidoStockModel.setAceptado(aceptado); //seteo el estado del pedido
		
		if (pedidoStockModel.isAceptado()) {// si es un pedidoStock aceptado
			pedidoStockService.insertOrUpdate(pedidoStockModel); // lo actualizo en la base de datos
			pedidoStockModel.getEmpleadoOferente().getLocal().restarLote(pedidoStockModel.getProducto(), pedidoStockModel.getCantidad());			
		}
		else { 
			pedidoStockService.remove(idPedidoStock);// si no lo elimino
		}
		return true;
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 12) GENERAR
	////////////////////////////////////////////////////////////////////////////////////////////////////// FACTURA/////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
		
	public boolean crearFactura(ClienteModel cliente, ChangoModel chango,LocalDate fecha, double costeTotal, EmpleadoModel empleado) {		
		facturaService.insertOrUpdate(new FacturaModel(cliente, chango, fecha, costeTotal, empleado, this)); //creo la factura
		if(chango.getPedidoStock() == null) restarChango(chango); // Si no hay pedidoStock, resto todos los productos del chango a este local
		return true;
	}

//	public List<Factura> traerFactura (LocalDate fecha1, LocalDate fecha2) {
//		List<Factura> list = new ArrayList<Factura>();		
//			int i= 0;
//			while (i<listaFacturas.size() && listaFacturas.get(i).getFechaFactura().isBefore(fecha2) ) {
//				if(listaFacturas.get(i).getFechaFactura().isEqual(fecha1) || listaFacturas.get(i).getFechaFactura().isAfter(fecha1) )list.add(listaFacturas.get(i));
//				i++;
//			}
//		return list;
//	}

	/****************************************************************************************************/
	public ChangoModel crearChango () {
		return changoService.insertOrUpdate(new ChangoModel(this)); //creo un chango nuevo para este Local
	}

	public boolean eliminarChango(ChangoModel chango) {// si elimino el chango debo restaurar todos los items
		sumarChango(chango);// sumo todos los items al local
		changoService.remove(chango.getIdChango());// elimino el chango de la DB
		return true; // return true
	}

	public void restarChango(ChangoModel chango) {
		for (ItemModel it : chango.getListaItems()) {// para cada item del chango
			restarLote(it.getProductoModel(), it.getCantidad()); // // resto la cantidad de productos correspondientes a
																	// el local
		}
	}

	public void sumarChango(ChangoModel chango) {
		for (ItemModel it : chango.getListaItems()) {// para cada item del chango
			sumarLote(it.getProductoModel(), it.getCantidad());// sumo la cantidad de productos correspondientes a el
																// local
		}
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 13) CIERRE DEL MES PARA DEFINIR EL SUELDO DE LOS
	////////////////////////////////////////////////////////////////////////////////////////////////////// EMPLEADOS//////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public double calcularSueldo(EmpleadoModel empleado) {
		double comisionCompleta = 0;
		for (FacturaModel fa : traerFacturaMesPasado()) {// para cada factura del mes pasado
			if (fa.getEmpleado().equals(empleado)) { // si la factura pertenece a este empleado
				if (fa.getChango().getPedidoStock() != null) { // el chango de la factura tiene un pedido stock, esta
																// factura es con stock de otro local
					// si el empleado solicito stock de otro local se calcula la comision de 3%
					if (fa.getChango().getPedidoStock().getEmpleadoSolicitante().equals(empleado))
						comisionCompleta = comisionCompleta + ((fa.getCosteTotal() * 3) / 100);
				} else {// si este empleado no pidio stock se calcula la comision del 5%
					comisionCompleta = comisionCompleta + ((fa.getCosteTotal() * 5) / 100);
				}
			} else {// si la factura no es de este empleado y si este empleado ofreció stock se el
					// calcula el 2%
				if (fa.getChango().getPedidoStock() != null
						&& fa.getChango().getPedidoStock().getEmpleadoOferente().equals(empleado))
					comisionCompleta = comisionCompleta + ((fa.getCosteTotal() * 2) / 100);
			}
		}
		return (empleado.getSueldoBasico() + comisionCompleta);
	}

	public Set<FacturaModel> traerFacturaMesPasado() {
		LocalDate fecha1 = LocalDate.now().minusMonths(1).withDayOfMonth(1);// mes pasado dia 1
		LocalDate fecha2 = LocalDate.now().minusMonths(1).withDayOfMonth(fecha1.lengthOfMonth());// último día del mes pasado
		return facturaService.findByFechaFacturaBetween(fecha1, fecha2);// retorno la lista de facturas
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 14) EMITIR REPORTE DE PRODUCTOS VENDIDOS ENTRE FECHAS POR
	////////////////////////////////////////////////////////////////////////////////////////////////////// LOCAL/////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public LinkedHashMap<ProductoModel, Integer> reporte(LocalDate fecha1, LocalDate fecha2) {
		LinkedHashMap<ProductoModel, Integer> listaReporte = new LinkedHashMap<ProductoModel, Integer>();
		// Para cada producto de la lista de productos
		for (ProductoModel pro : productoService.getAllModel()) {
			int cantidad = 0;
			// Traigo la lista de facturas del local entre fechas
			Set<FacturaModel> listaFacturas = facturaService.findByFechaFacturaBetweenAndIdLocal(fecha1, fecha2,
					this.idLocal);

			for (FacturaModel fa : listaFacturas) {
				// de cada factura obtengo el chango y traigo el item que tenga el producto que
				// estamos evaluando
				// si el producto está en la factura, se suma la cantidad correspondiente
				if (fa.getChango().traerItem(pro) != null)
					cantidad = cantidad + fa.getChango().traerItem(pro).getCantidad();
			}
			listaReporte.put(pro, cantidad);
		}
		return listaReporte;
	}

	/****************************************************************************************************/
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	// 15) RANKING DE PRODUCTOS MÁS VENDIDOS
	////////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/****************************************************************************************************/
	public LinkedHashMap<ProductoModel, Integer> ranking() {
		List<ProductoModel> listaProductos = productoService.getAllModel();
		LinkedHashMap<ProductoModel, Integer> rankingProductos = new LinkedHashMap<ProductoModel, Integer>();
		int[] cantidadList = new int[listaProductos.size()];// vector para guardar el valor de cantidadProductoVendido
		long[] idList = new long[listaProductos.size()];// vector para guardar el valor ID del producto
		for (int i = 0; i < listaProductos.size(); i++) {
			idList[i] = listaProductos.get(i).getIdProducto();
			cantidadList[i] = cantidadProductoVendido(listaProductos.get(i));
		}
		Funciones.orden(idList, cantidadList);// este método ordena los dos vectores de mayor a menor usando el valor de la
									   // cantidad
		int cont = 0;
		for (long id : idList) {
			// agrego a la lista los productos con ID en orden
			rankingProductos.put(productoService.findByIdProducto(id), cantidadList[cont]);
			cont++;
		}
		return rankingProductos;
	}

	public int cantidadProductoVendido(ProductoModel producto) {
		int cantidad = 0;
		for (FacturaModel fa : facturaService.getAllModel()) {
			// de cada factura obtengo el chango y traigo el item que tenga el producto que
			// estamos evaluando
			// si el producto está en la factura, se suma la cantidad correspondiente
			if (fa.getChango().traerItem(producto) != null)
				cantidad = cantidad + fa.getChango().traerItem(producto).getCantidad();
		}
		return cantidad;
	}
}
