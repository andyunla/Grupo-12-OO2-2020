package test;

import java.time.LocalDate;
import java.time.LocalTime;

import datos.*;

public class Test {

	public static void main(String[] args) {
		
				// TODO Auto-generated method stub
				SistemaDemocratizacionStock sistema1 = new SistemaDemocratizacionStock();
				//ALTA DE LOCALES
				try {
					sistema1.crearLocal("Tienda1 ABC", 45.0, 54.8, "La Salada", 11111111);
					sistema1.crearLocal("Tienda2 El Gato", 21.1, 61.3, "La Quinta de Olivos", 22222222);
					sistema1.crearLocal("Tienda3 de Moda Salazar", 42.67, 37.87, "Espa�a al 1800", 33333333);
					sistema1.crearLocal("Tienda4 El callejon de la moda", 15.56, 51.28, "Boulevar de Burzaco", 44444444);
					sistema1.crearLocal("Tienda5 será borrada ", 25.56, 25.28, "S�, borrada.", 55555555);
				} catch(Exception e) {
					System.out.println(e.getMessage() );
				}
				
				System.out.println("LOCALES");
				System.out.println(sistema1.getListaLocales() );
				System.out.println();

				
				//MODIFICAR DE LOCAL
				try {
					System.out.println("\nMODIFICAR LOCAL 5: "+sistema1.modificarLocal(5, "Tienda5 ser� borrada ", 35.56, 35.28, "pronto", 66666666));	
					System.out.println("Traer local 5: "+sistema1.traerLocal(5).toString());
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}	
				//BAJA DE LOCALES
				try {
					System.out.println("\nBAJA DE LOCAL 5: "+ sistema1.eliminarLocal(5));
					System.out.println("Traer local 5: "+sistema1.traerLocal(5));				
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}

				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				
				System.out.println();
				//ALTA DE EMPLEADOS
				try { //nombre, apellido, dni, fechaNacimiento, legajo, horaDesde, horaHasta, sueldoBasico
					sistema1.traerLocal(1).crearEmpleado("Pepe", "Argento", 11111111, LocalDate.of(1982, 3, 15), 111, LocalTime.of(8, 30), LocalTime.of(16, 30), 35000.0);
					sistema1.traerLocal(1).crearEmpleado("Moni", "Argento", 22222222, LocalDate.of(1990, 1, 23), 222, LocalTime.of(8, 30), LocalTime.of(16, 30), 35000.0 );
					sistema1.traerLocal(1).crearEmpleado("Gerente1", "Gerente1", 11112222, LocalDate.of(1985, 1, 23), 101, LocalTime.of(8, 30), LocalTime.of(16, 30), 35020.0 );
					sistema1.traerLocal(1).crearEmpleado("Para", "Borrar", 11126222, LocalDate.of(1985, 1, 23), 105, LocalTime.of(8, 30), LocalTime.of(16, 30), 35020.0 );
					
					sistema1.traerLocal(2).crearEmpleado("Mauricio", "Macri", 33333333, LocalDate.of(1959, 2, 8), 333, LocalTime.of(10, 0), LocalTime.of(16, 30), 35000.0 );
					sistema1.traerLocal(2).crearEmpleado("Solaris", "De Astora", 44444444, LocalDate.of(1956, 6, 11), 444, LocalTime.of(12, 00), LocalTime.of(20, 0), 35000.0 );
					sistema1.traerLocal(2).crearEmpleado("Gerente2", "Gerente2", 11113333, LocalDate.of(1985, 1, 23), 102, LocalTime.of(8, 30), LocalTime.of(16, 30), 35020.0 );
					
					sistema1.traerLocal(3).crearEmpleado("Esteban", "Fernandez", 55555555, LocalDate.of(1978, 7, 28), 555, LocalTime.of(16, 0), LocalTime.of(0, 0), 35000.0 );
					sistema1.traerLocal(3).crearEmpleado("Guillermo", "Nicolas", 66666666, LocalDate.of(1999, 11, 30), 666, LocalTime.of(16, 0), LocalTime.of(0, 0), 35000.0 );
					sistema1.traerLocal(3).crearEmpleado("Gerente3", "Gerente3", 11114444, LocalDate.of(1985, 1, 23), 103, LocalTime.of(8, 30), LocalTime.of(16, 30), 35020.0 );
					
					sistema1.traerLocal(4).crearEmpleado("Fernando", "Ferreira", 77777777, LocalDate.of(1976, 4, 21), 777, LocalTime.of(0, 0), LocalTime.of(8, 0), 35000.0 );
					sistema1.traerLocal(4).crearEmpleado("Sofia", "Celeste", 88888888, LocalDate.of(1989, 8, 7), 888, LocalTime.of(0, 0), LocalTime.of(0, 0), 35000.0 );
					sistema1.traerLocal(4).crearEmpleado("Gerente4", "Gerente4", 11115555, LocalDate.of(1985, 1, 23), 104, LocalTime.of(8, 30), LocalTime.of(16, 30), 35020.0 );
					
				}catch(Exception e){
					System.out.println(e.getMessage() );
				}
				
				
				for(Local lo: sistema1.traerLocal() ) {
					System.out.println("\nEMPLEADOS DEL LOCAL " + lo.getIdLocal() + ": " + lo.getNombreLocal() );
					System.out.println(lo.getListaEmpleados().toString());
					System.out.println();
			
				}
				
				//MODIFICAR EMPLEADO
				try {
					System.out.println("\nMODIFICAR EMPLEADO LEGAJO 105: "+sistema1.traerLocal(1).modificarEmpleado("Ser�", "Borrado", 11126222, LocalDate.of(1985, 1, 23), 105, LocalTime.of(8, 30), LocalTime.of(16, 30), 35020.0 ));	
					System.out.println("Traer empleado legajo 105: "+sistema1.traerLocal(1).traerEmpleado(105).toString());
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
				
				//BAJA DE LOCALES
				try {
					System.out.println("\nBAJA DE EMPLEADO LEGAJO 105: "+ sistema1.traerLocal(1).eliminarEmpleado(105));
					System.out.println("Traer empleado legajo 105: "+sistema1.traerLocal(1).traerEmpleado(105).toString());
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
			
				System.out.println("\nAgregar Gerente a local 1: " );
				sistema1.traerLocal(1).setGerente(sistema1.traerLocal(1).traerEmpleado(101));
				System.out.println("Gerente local 1: "+sistema1.traerLocal(1).getGerente().getNombre()+" "+sistema1.traerLocal(1).getGerente().getApellido());
				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				//CARGA DE PRODUCTOS
				try { //idProducto, nombre, descripcion, precio, talle
					sistema1.crearProducto("Camiseta de Rata Blanca", "Camiseta cool", 100.00, 36);
					sistema1.crearProducto("Pantalon de Boca", "La mitad m�s uno", 200.00, 34);
					sistema1.crearProducto("Camiseta de River", "Los millonarios", 300.00, 34);
					sistema1.crearProducto("Zapatillas de Puma", "De las que te hacen volar cuando corres", 400.00, 42);
					sistema1.crearProducto("Ojotas comodas", "No sirven para correr", 500.00, 40);
					sistema1.crearProducto("Visera", "Sacate la gorra y ponete la visera", 600.00, 20);
					sistema1.crearProducto("SER�", "BORRADO", 100.00, 20);
					
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
								
				System.out.println("\nPRODUCTOS");
				System.out.println(sistema1.getListaProductos().toString());

				//MODIFICAR PRODUCTO
				try {
					System.out.println("\nMODIFICAR PRODUCTO ID=7: "+sistema1.modificarProducto(7, "PARA", "BORRAR", 200.2, 40));	
					System.out.println("Traer producto ID=7: "+sistema1.traerProducto(7));
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}	
				//BAJA DE PRODUCTO
				try {
					System.out.println("\nBAJA DE PRODUCTO ID=7: "+ sistema1.eliminarProducto(7));
					System.out.println("Traer producto ID=7: "+sistema1.traerProducto(7));
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				
				
				//CARGA DE CLIENTES
				try { //Nombre, apellido, dni, fechadenacimiento, email
					sistema1.crearCliente("Luis", "Sera", 99991111, LocalDate.of(2004, 6, 27), "Luis@hotmail.com");
					sistema1.crearCliente("Ashley", "Graham", 99992222, LocalDate.of(2005, 2, 14), "Ashley@hotmail.com");
					sistema1.crearCliente("Lion", "Kennedy", 99993333, LocalDate.of(2003, 8, 2), "Lion@gmail.com");
					sistema1.crearCliente("Ada", "Wong", 99994444, LocalDate.of(2001, 2, 28), "Ada@gmail.com");
					sistema1.crearCliente("Christ", "Adam", 99995555, LocalDate.of(2002, 12, 11), "Christ@gmail.com");
					sistema1.crearCliente("Alan", "Wesker", 99996666, LocalDate.of(1998, 10, 25), "Alan@hotmail.com");
					sistema1.crearCliente("SER�", "BORRADO", 99997777, LocalDate.of(1998, 10, 25), "Alan@hotmail.com");	
					
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}				
				System.out.println("\nCLIENTES");
				System.out.println(sistema1.getListaClientes().toString());

				//MODIFICAR CLIENTE
				try {
					System.out.println("\nMODIFICAR CLIENTE 99997777: "+sistema1.modificarCliente("PARA", "BORRAR", 99997777, LocalDate.of(1998, 10, 25), "Alan@hotmail.com"));	
					System.out.println("\nTraer cliente DNI=99997777: "+sistema1.traerCliente(99997777));
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}	
				//BAJA DE CLIENTE
				try {
					System.out.println("\nBAJA DE CLIENTE ID=7: "+ sistema1.eliminarCliente(99997777));
					System.out.println("Traer cliente DNI=99997777: "+sistema1.traerCliente(99997777));
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}

				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				
				
				//CARGA DE LOTES
				try { //Cantidadinicial, fechadeingreso, producto
					sistema1.traerLocal(1).crearLote(50, LocalDate.of(2019, 1, 20), sistema1.traerProducto(1));
					sistema1.traerLocal(1).crearLote(50, LocalDate.of(2019, 2, 20), sistema1.traerProducto(1));
					sistema1.traerLocal(1).crearLote(50, LocalDate.of(2019, 3, 20), sistema1.traerProducto(1));
					sistema1.traerLocal(1).crearLote(50, LocalDate.of(2019, 4, 20), sistema1.traerProducto(4));
					sistema1.traerLocal(1).crearLote(50, LocalDate.of(2019, 5, 20), sistema1.traerProducto(4));
					sistema1.traerLocal(1).crearLote(50, LocalDate.of(2019, 6, 20), sistema1.traerProducto(5));
					sistema1.traerLocal(1).crearLote(50, LocalDate.of(2019, 7, 20), sistema1.traerProducto(5));
					
					sistema1.traerLocal(2).crearLote(50, LocalDate.of(2019, 1, 20), sistema1.traerProducto(1));
					sistema1.traerLocal(2).crearLote(50, LocalDate.of(2019, 2, 20), sistema1.traerProducto(2));
					sistema1.traerLocal(2).crearLote(50, LocalDate.of(2019, 3, 20), sistema1.traerProducto(3));
					sistema1.traerLocal(2).crearLote(50, LocalDate.of(2019, 4, 20), sistema1.traerProducto(4));
					sistema1.traerLocal(2).crearLote(50, LocalDate.of(2019, 5, 20), sistema1.traerProducto(4));
					sistema1.traerLocal(2).crearLote(50, LocalDate.of(2019, 6, 20), sistema1.traerProducto(5));
					sistema1.traerLocal(2).crearLote(50, LocalDate.of(2019, 7, 20), sistema1.traerProducto(5));
					
					sistema1.traerLocal(3).crearLote(50, LocalDate.of(2019, 1, 20), sistema1.traerProducto(1));
					sistema1.traerLocal(3).crearLote(50, LocalDate.of(2019, 2, 20), sistema1.traerProducto(1));
					sistema1.traerLocal(3).crearLote(50, LocalDate.of(2019, 3, 20), sistema1.traerProducto(1));
					sistema1.traerLocal(3).crearLote(50, LocalDate.of(2019, 4, 20), sistema1.traerProducto(2));
					sistema1.traerLocal(3).crearLote(50, LocalDate.of(2019, 5, 20), sistema1.traerProducto(3));
					sistema1.traerLocal(3).crearLote(50, LocalDate.of(2019, 6, 20), sistema1.traerProducto(4));
					sistema1.traerLocal(3).crearLote(50, LocalDate.of(2019, 7, 20), sistema1.traerProducto(5));
					
					sistema1.traerLocal(4).crearLote(50, LocalDate.of(2019, 1, 20), sistema1.traerProducto(1));
					sistema1.traerLocal(4).crearLote(50, LocalDate.of(2019, 2, 20), sistema1.traerProducto(2));
					sistema1.traerLocal(4).crearLote(50, LocalDate.of(2019, 3, 20), sistema1.traerProducto(3));
					sistema1.traerLocal(4).crearLote(50, LocalDate.of(2019, 4, 20), sistema1.traerProducto(4));
					sistema1.traerLocal(4).crearLote(50, LocalDate.of(2019, 5, 20), sistema1.traerProducto(4));
					sistema1.traerLocal(4).crearLote(50, LocalDate.of(2019, 6, 20), sistema1.traerProducto(5));
					sistema1.traerLocal(4).crearLote(50, LocalDate.of(2019, 7, 20), sistema1.traerProducto(5));
					
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
				
				
				System.out.println("LOTES");
				
				for(Local lo: sistema1.traerLocal() ) {
					System.out.println("LOTES DEL LOCAL "+lo.getIdLocal()+": "+lo.getNombreLocal() );				
					for(Lote lote: lo.getListaLotes() ) {
						System.out.println("ID: " + lote.getIdLote() );
						System.out.println("Cantidad Inicial: " + lote.getCantidadInicial() );
						System.out.println("Cantidad Actual: " + lote.getCantidadActual() );
						System.out.println("Fecha de Ingreso: " + lote.getFechaIngreso() );
						System.out.println("Producto: " + lote.getProducto().getNombre() );
						System.out.println("Activo: " + lote.isActivo() );
						System.out.println();
					}
				}
				
				// RESTAR LOTE
				
				try {
					System.out.println("Traer LOTE LOCAL 1, PRODUCTO 1: "+sistema1.traerLocal(1).calcularStockLocal(sistema1.traerProducto(1)));
					System.out.println("BAJA DE LOTE LOCAL 1, PRODUCTO 1, 111 und.: "+ sistema1.traerLocal(1).restarLote(sistema1.traerProducto(1), 111));
					System.out.println("Traer LOTE LOCAL 1, PRODUCTO 1: "+sistema1.traerLocal(1).calcularStockLocal(sistema1.traerProducto(1)));
					System.out.println("BAJA DE LOTE LOCAL 1, PRODUCTO 1, 18 und.: "+ sistema1.traerLocal(1).restarLote(sistema1.traerProducto(1), 18));
					System.out.println("Traer LOTE LOCAL 1, PRODUCTO 1: "+sistema1.traerLocal(1).calcularStockLocal(sistema1.traerProducto(1)));
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
				
				
				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				
				//CARGA DE CHANGOS
				try {
					sistema1.traerLocal(1).crearChango();
					sistema1.traerLocal(1).crearChango();
					sistema1.traerLocal(1).traerChango(1).crearItem(5, sistema1.traerProducto(1) );
					sistema1.traerLocal(1).traerChango(1).crearItem(5, sistema1.traerProducto(1) );
					sistema1.traerLocal(1).traerChango(1).crearItem(5, sistema1.traerProducto(2) );
					sistema1.traerLocal(1).traerChango(1).eliminarItem(5, sistema1.traerProducto(1));
					sistema1.traerLocal(1).traerChango(2).crearItem(10, sistema1.traerProducto(2) );
					sistema1.traerLocal(1).traerChango(2).eliminarItem(70, sistema1.traerProducto(2));
					
					sistema1.traerLocal(2).crearChango();
					sistema1.traerLocal(2).crearChango();
					sistema1.traerLocal(2).crearChango();
					sistema1.traerLocal(2).traerChango(1).crearItem(5, sistema1.traerProducto(4) );
					sistema1.traerLocal(2).traerChango(2).crearItem(15, sistema1.traerProducto(1) );
					sistema1.traerLocal(2).traerChango(2).crearItem(5, sistema1.traerProducto(1) );
					sistema1.traerLocal(2).traerChango(3).crearItem(8, sistema1.traerProducto(2) );
					
					sistema1.traerLocal(3).crearChango();
					sistema1.traerLocal(3).traerChango(1).crearItem(10, sistema1.traerProducto(1) );
					sistema1.traerLocal(3).traerChango(1).crearItem(10, sistema1.traerProducto(2) );
					sistema1.traerLocal(3).crearChango();
					sistema1.traerLocal(3).traerChango(2).crearItem(10, sistema1.traerProducto(2) );
					sistema1.traerLocal(3).traerChango(2).crearItem(10, sistema1.traerProducto(2) );
					sistema1.traerLocal(3).crearChango();
					sistema1.traerLocal(3).traerChango(3).crearItem(10, sistema1.traerProducto(3) );
					sistema1.traerLocal(3).traerChango(3).crearItem(10, sistema1.traerProducto(3) );
					sistema1.traerLocal(3).crearChango();
					sistema1.traerLocal(3).traerChango(4).crearItem(10, sistema1.traerProducto(4) );
					sistema1.traerLocal(3).traerChango(4).crearItem(10, sistema1.traerProducto(4) );
					
					sistema1.traerLocal(4).crearChango();
					sistema1.traerLocal(4).crearChango();
					sistema1.traerLocal(4).traerChango(1).crearItem(20, sistema1.traerProducto(2) );
					sistema1.traerLocal(4).traerChango(2).crearItem(15, sistema1.traerProducto(3) );
					sistema1.traerLocal(4).traerChango(1).crearItem(27, sistema1.traerProducto(2) );
					
					
					
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
				
				
				for(Local lo: sistema1.traerLocal() ) {
					System.out.println("CHANGOS DEL LOCAL " + lo.getIdLocal() + ": " + lo.getNombreLocal() );
					System.out.println(lo.getListaChangos().toString());

				}
				
				
				System.out.println("***********************************************************************************");
				System.out.println("Traer LOTE LOCAL 1, PRODUCTO 4: "+sistema1.traerLocal(1).calcularStockLocal(sistema1.traerProducto(4)));
				System.out.println("Validar de stock Producto 4 en la tienda 1, 45 und: " + sistema1.traerLocal(1).validarStock(sistema1.traerProducto(4), 45) );
				System.out.println("Validar de stock Producto 4 en la tienda 1, 145 und: " + sistema1.traerLocal(1).validarStock(sistema1.traerProducto(4), 145) );
				System.out.println();
				System.out.println("***********************************************************************************");
				System.out.println("\nPosibilidades de locales a solicitar stock: ");
				for (Local lo : sistema1.traerLocal()) {
					if(lo.validarStock(sistema1.traerProducto(1), 30)) System.out.println(lo.toString());					
				}
				System.out.println("\nTienda m�s cercana al Local 1: " + sistema1.calcularTiendaMasCercana(sistema1.traerLocal(1)).getNombreLocal() );
				System.out.println("Distancia entre la tienda 1 y la tienda 2: " + sistema1.traerLocal(1).calcularDistancia(sistema1.traerLocal(2)) );
				System.out.println("Distancia entre la tienda 1 y la tienda 3: " + sistema1.traerLocal(1).calcularDistancia(sistema1.traerLocal(3)) );
				System.out.println("Distancia entre la tienda 1 y la tienda 4: " + sistema1.traerLocal(1).calcularDistancia(sistema1.traerLocal(4)) );
				System.out.println();
				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				//CARGA DE PEDIDOSSTOCK
				try { //Producto, cantidad, empleadoSolicitante
					sistema1.crearPedidoStock(sistema1.traerProducto(1), 20, sistema1.traerLocal(1).traerEmpleado(111) );
				//	sistema1.crearPedidoStock(sistema1.traerProducto(1), 30, sistema1.traerLocal(1).traerEmpleado(222) );
					sistema1.crearPedidoStock(sistema1.traerProducto(2), 40, sistema1.traerLocal(1).traerEmpleado(222) );
					sistema1.crearPedidoStock(sistema1.traerProducto(3), 50, sistema1.traerLocal(2).traerEmpleado(333) );
					
					
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
				
				
				System.out.println("PEDIDOS STOCK");
				for(PedidoStock pe: sistema1.traerPedidoStock() ) {
					System.out.println("ID: " + pe.getIdPedido() );
					System.out.println("Producto: " + pe.getProducto().getNombre() );
					System.out.println("Cantidad: " + pe.getCantidad() );
					System.out.println("Solicitante: " + pe.getSolicitante().getNombre() + " " + pe.getSolicitante().getApellido() );
					System.out.println("Aceptado: " + pe.isAceptado() );
					System.out.println("Oferente: " + pe.getOferente() );
					System.out.println();
				}
				try {
					System.out.println("Aceptar o rechazar la solicitud de stock: ");
					System.out.println("Traer LOTE LOCAL 2, PRODUCTO 1: "+sistema1.traerLocal(2).calcularStockLocal(sistema1.traerProducto(1)));
					System.out.println("Aceptar  la solicitud de stock del local 1: "+ sistema1.modificarPedidoStock(1, true, sistema1.traerLocal(2).traerEmpleado(333)));
					System.out.println("Traer LOTE LOCAL 2, PRODUCTO 1: "+sistema1.traerLocal(2).calcularStockLocal(sistema1.traerProducto(1)));
					System.out.println();
				} catch (Exception e) {
					System.out.println(e.getMessage() );// TODO: handle exception
				}
				try {
					System.out.println("Aceptar o rechazar la solicitud de stock: ");
					System.out.println("Traer LOTE LOCAL 2, PRODUCTO 2: "+sistema1.traerLocal(2).calcularStockLocal(sistema1.traerProducto(2)));
					System.out.println(sistema1.traerPedidoStock(2) );
					System.out.println("\nRechazar  la solicitud de stock del local 1: "+ sistema1.modificarPedidoStock(2, false, sistema1.traerLocal(2).traerEmpleado(333)));					
					System.out.println("Traer LOTE LOCAL 2, PRODUCTO 2: "+sistema1.traerLocal(2).calcularStockLocal(sistema1.traerProducto(2)));
					System.out.println("Traer el PEDIDO DE STOCK 2: " + sistema1.traerPedidoStock(2) ); 
					System.out.println();
				} catch (Exception e) {
					System.out.println(e.getMessage() );// TODO: handle exception
				}
				
				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("Traer LOTE LOCAL 3, PRODUCTO 1: "+sistema1.traerLocal(3).calcularStockLocal(sistema1.traerProducto(1)));
				System.out.println("Traer LOTE LOCAL 3, PRODUCTO 2: "+sistema1.traerLocal(3).calcularStockLocal(sistema1.traerProducto(2)));
				System.out.println("Traer LOTE LOCAL 3, PRODUCTO 3: "+sistema1.traerLocal(3).calcularStockLocal(sistema1.traerProducto(3)));
				System.out.println("Traer LOTE LOCAL 3, PRODUCTO 4: "+sistema1.traerLocal(3).calcularStockLocal(sistema1.traerProducto(4)));
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				
				//CARGA DE FACTURAS
				try { //Cliente, chango, costeTotal, empleado
//					sistema1.traerLocal(1).crearFactura(sistema1.traerCliente(99991111), sistema1.traerLocal(1).traerChango(1), sistema1.traerLocal(1).traerChango(1).calcularTotalChango(), sistema1.traerLocal(1).traerEmpleado(111) );
//					sistema1.traerLocal(1).crearFactura(sistema1.traerCliente(99992222), sistema1.traerLocal(1).traerChango(2), sistema1.traerLocal(1).traerChango(2).calcularTotalChango(), sistema1.traerLocal(1).traerEmpleado(222) );
//				
//					sistema1.traerLocal(2).crearFactura(sistema1.traerCliente(99993333), sistema1.traerLocal(2).traerChango(1), sistema1.traerLocal(2).traerChango(1).calcularTotalChango(), sistema1.traerLocal(2).traerEmpleado(333) );
//					sistema1.traerLocal(2).crearFactura(sistema1.traerCliente(99994444), sistema1.traerLocal(2).traerChango(2), sistema1.traerLocal(2).traerChango(2).calcularTotalChango(), sistema1.traerLocal(2).traerEmpleado(333) );
//					sistema1.traerLocal(2).crearFactura(sistema1.traerCliente(99995555), sistema1.traerLocal(2).traerChango(3), sistema1.traerLocal(2).traerChango(3).calcularTotalChango(), sistema1.traerLocal(2).traerEmpleado(444) );
				
					sistema1.traerLocal(3).crearFactura(sistema1.traerCliente(99996666), sistema1.traerLocal(3).traerChango(1),LocalDate.of(2019, 9, 15), sistema1.traerLocal(3).traerChango(1).calcularTotalChango(), sistema1.traerLocal(3).traerEmpleado(555) );
					sistema1.traerLocal(3).crearFactura(sistema1.traerCliente(99996666), sistema1.traerLocal(3).traerChango(2),LocalDate.of(2019, 9, 16), sistema1.traerLocal(3).traerChango(2).calcularTotalChango(), sistema1.traerLocal(3).traerEmpleado(555) );
					sistema1.traerLocal(3).crearFactura(sistema1.traerCliente(99996666), sistema1.traerLocal(3).traerChango(3),LocalDate.of(2019, 10, 15), sistema1.traerLocal(3).traerChango(3).calcularTotalChango(), sistema1.traerLocal(3).traerEmpleado(555) );
					sistema1.traerLocal(3).crearFactura(sistema1.traerCliente(99996666), sistema1.traerLocal(3).traerChango(4),LocalDate.of(2019, 10, 16), sistema1.traerLocal(3).traerChango(4).calcularTotalChango(), sistema1.traerLocal(3).traerEmpleado(555) );
					
//					sistema1.traerLocal(4).crearFactura(sistema1.traerCliente(99991111), sistema1.traerLocal(4).traerChango(1), sistema1.traerLocal(4).traerChango(1).calcularTotalChango(), sistema1.traerLocal(4).traerEmpleado(777) );
//					sistema1.traerLocal(4).crearFactura(sistema1.traerCliente(16572222), sistema1.traerLocal(4).traerChango(2), sistema1.traerLocal(4).traerChango(2).calcularTotalChango(), sistema1.traerLocal(4).traerEmpleado(888) );

					
				}catch(Exception e) {
					System.out.println(e.getMessage() );
				}
				
				
				for(Local lo: sistema1.traerLocal() ) {
					System.out.println("FACTURAS DEL LOCAL " + lo.getIdLocal() + ": " + lo.getNombreLocal() );
					for(Factura fa: lo.getListaFacturas() ) {
						System.out.println("ID: " + fa.getIdFactura() );
						System.out.println("Cliente: " + fa.getCliente().getNombre() + " " + fa.getCliente().getApellido() );
						System.out.println("Chango: " + fa.getChango() );
						System.out.println("Fecha de Factura: " + fa.getFechaFactura() );
						System.out.println("Coste total: " + fa.getCosteTotal() );
						System.out.println("Empleado: " + fa.getEmpleado().getNombre() );
						System.out.println();
					}
				}
				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("Traer LOTE LOCAL 3, PRODUCTO 1: "+sistema1.traerLocal(3).calcularStockLocal(sistema1.traerProducto(1)));
				System.out.println("Traer LOTE LOCAL 3, PRODUCTO 2: "+sistema1.traerLocal(3).calcularStockLocal(sistema1.traerProducto(2)));
				System.out.println("Traer LOTE LOCAL 3, PRODUCTO 3: "+sistema1.traerLocal(3).calcularStockLocal(sistema1.traerProducto(3)));
				System.out.println("Traer LOTE LOCAL 3, PRODUCTO 4: "+sistema1.traerLocal(3).calcularStockLocal(sistema1.traerProducto(4)));
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				
				System.out.println("\nCierre del mes para definir el sueldo de los vendedores.");
				System.out.println(sistema1.traerLocal(3).traerEmpleado(555).getNombre()+" "+sistema1.traerLocal(3).traerEmpleado(555).getApellido()+" :"+sistema1.calcularSueldo(sistema1.traerLocal(3).traerEmpleado(555)));
				System.out.println(sistema1.traerLocal(3).traerEmpleado(666).getNombre()+" "+sistema1.traerLocal(3).traerEmpleado(666).getApellido()+" :"+sistema1.calcularSueldo(sistema1.traerLocal(3).traerEmpleado(666)));
				System.out.println(sistema1.traerLocal(3).traerEmpleado(103).getNombre()+" "+sistema1.traerLocal(3).traerEmpleado(103).getApellido()+" :"+sistema1.calcularSueldo(sistema1.traerLocal(3).traerEmpleado(103)));
				
				
				
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("\nEmitir reporte de productos vendidos entre fechas por local.");				
				sistema1.reporte(sistema1.traerLocal(3), LocalDate.of(2019, 10, 10), LocalDate.of(2019, 10, 20));
				System.out.println();
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				
			
				System.out.println("\nEmitir ranking de producto m�s vendido.");				
				try {System.out.println(sistema1.rankingProductosGlobales() );}
				catch(Exception e) {System.out.println(e.getMessage() );}
				System.out.println();
				System.out.println();
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				
				
			
				System.out.println("TEST DE METODOS");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
				System.out.println("***********************************************************************************");
					
				
				System.out.println("Cantidad vendido del producto 1 en el local 1: " + sistema1.traerLocal(1).cantidadLocalProductoVendido(sistema1.traerProducto(1)) );
				System.out.println("Cantidad vendido del producto 2 en el local 3: " + sistema1.traerLocal(3).cantidadLocalProductoVendido(sistema1.traerProducto(2)) );
				System.out.println();
				
				
				int i;
				for(i=1; i<=sistema1.getListaProductos().size();i++) {
					System.out.println("Cantidad global de stock del producto "+ i +": " + sistema1.calcularStockGlobal(sistema1.traerProducto(i)) );
				}
				System.out.println();
				
				
				
				for(i=1; i<=sistema1.getListaProductos().size();i++) {
					System.out.println("Cantidad global vendido del producto " + i +": " + sistema1.cantidadGlobalProductoVendido(sistema1.traerProducto(i)) );		
				}
				System.out.println();
				
				
				
			
				
				
				
				
				//System.out.println(sistema1.traerFacturaMesPasado());
				
				
			}

		}

	


