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
	// Parciales
	public final static String LISTA_LOTES = "partial/listaLotes";
	public final static String LISTA_LOCALES_CERCANOS = "partial/listaLocalesCercanos";
	public final static String ITEM = "partial/item";

	// CASOS DE USO
	public final static String DISTANCIA_ROOT = "cu/distancia";
	public final static String CHANGO_ROOT = "cu/chango";
	public final static String RANKIG_ROOT = "cu/ranking";

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
}
