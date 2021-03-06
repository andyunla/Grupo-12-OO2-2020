package com.sistema.application.helpers;

public class ViewRouteHelper {
	/**** Views *********************************************/
	// HOME
	public final static String INDEX = "index";
	// ABMs
	public final static String CLIENTE_ABM = "abm/cliente";
	public final static String EMPLEADO_ABM = "abm/empleado";
	public final static String PRODUCTO_ABM = "abm/producto";
	public final static String LOCAL_ABM = "abm/local";
	public final static String ITEM_ABM = "abm/item";
	public static final String LOTE_ABM = "abm/lote";
	public final static String CHANGO = "abm/chango";
	public final static String CHANGOS = "abm/changos";
	public final static String CHANGO_FACTURADO = "abm/changoFacturado";
	public final static String FACTURA = "abm/factura";
	public final static String FACTURAS = "abm/facturas";

	// Parciales   
	public final static String LISTA_LOTES = "partial/listaLotes";
	public final static String LISTA_LOCALES_CERCANOS = "partial/listaLocalesCercanos";
	public final static String RANKING_REPORTE_PRODUCTOS = "partial/listaProductosRanking";
	public final static String PRODUCTOS_DISPONIBLES = "partial/productosDisponibles";
	public final static String LISTA_FACTURAS = "partial/listaFacturas";
	public final static String LISTA_SUELDOS = "partial/listaSueldos";
  	public final static String ITEMS = "partial/items";
	public final static String LISTA_NOTIFICACIONES = "partial/notificaciones";
  
	// CASOS DE USO
	public final static String DISTANCIA_ROOT = "cu/distancia";
	public final static String RANKING_ROOT = "cu/ranking";
	public final static String REPORTE_ROOT = "cu/reporte";
	public final static String PEDIDO_STOCK_VIEW = "cu/pedidoStock";
	public final static String SUELDO_ROOT = "cu/sueldo";
	public final static String RECIBO = "cu/recibo";

	// OTROS
	public final static String NOTIFICACION_ROOT = "notificacion/notificacion";

	// USER
	public final static String USER_LOGIN = "user/login";
	public final static String USER_LOGOUT = "user/logout";

	/**** Redirects *****************************************/
	public final static String HOME_ROOT = "index";
	public final static String CLIENTE_ROOT = "cliente";
	public final static String EMPLEADO_ROOT = "empleado";
	public final static String PRODUCTO_ROOT = "producto";
	public final static String LOCAL_ROOT = "local";
	public final static String ITEM_ROOT = "item";
	public static final String LOTE_ROOT = "lote";
	public static final String PEDIDO_ROOT = "pedido";
	public final static String CHANGOS_ROOT = "changos";
}
