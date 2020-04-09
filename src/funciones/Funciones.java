package funciones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Funciones {
	/**
   	* Método que retorna una fecha de tipo LocalDate
   	* dado un String 
   	* @param fecha Tiene que ser del tipo "AAAA-MM-DD"
   	* @return LocalDate
   	*/
	public static LocalDate traerFecha(String fecha) {
		//Parámetro de la forma "AAAA-MM-DD"
		int aaaa,mm,dd;
		String anio="",mes="",dia="";
		dia = fecha.substring(8, 10);//el ultimo no sale
		mes = fecha.substring(5, 7);
		anio = fecha.substring(0, 4);
		aaaa = Integer.parseInt(anio);
		mm = Integer.parseInt(mes);
 		dd = Integer.parseInt(dia);
		LocalDate fechaNueva = LocalDate.of(aaaa, mm, dd);
		return fechaNueva;
	}

	/**
   	* Método que retorna una fecha de tipo String 
   	* @param fecha de tipo GregorianCalendar
   	* @return String
   	*/
	public static String fechaForm(GregorianCalendar fecha){
		int anyo = 0;
		int mes = 0;
		int dia = 0;
		int indexDia = 0;
		String[] days = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
		String day = "";
		anyo = fecha.get(GregorianCalendar.YEAR);
		mes = fecha.get(GregorianCalendar.MONTH)+1;
		dia = fecha.get(GregorianCalendar.DAY_OF_MONTH);
		indexDia = fecha.get(GregorianCalendar.DAY_OF_WEEK) - 1; // Le restamos 1 porque comienza en 1

		day = days[indexDia];
		return ("El " + day + " - " + dia +"/" + mes + "/" + anyo);
	}

	/**
   	* Método Para obtener el nombre de un mes dado un GregorianCalendar 
   	* @param fecha de tipo GregorianCalendar
   	* @return String
   	*/
	public static String mesForm(GregorianCalendar fecha){
		String[] months = {"Enero", "Febrero", "Marzo", "Abril", "mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre"};
		int index = fecha.get(GregorianCalendar.MONTH);
		return months[index];
	}
	
	/**
   	* Método para  calcular el tiempo transcurrido entre 2 fechas 
   	* @param fechaActual de tipo GregorianCalendar
   	* @param fechaDeNacimiento de tipo GregorianCalendar
   	* @return String
   	*/
	public static String calcularEdad (GregorianCalendar fechaActual, GregorianCalendar fechaDeNacimiento) {
		int anios=0;  int meses=0;  int dias=0;

		int yA = fechaActual.get(GregorianCalendar.YEAR);
		int mAct = fechaActual.get(GregorianCalendar.MONTH)+1;
		int dAct = fechaActual.get(GregorianCalendar.DAY_OF_MONTH);

		int yNac = fechaDeNacimiento.get(GregorianCalendar.YEAR);
		int mNac = fechaDeNacimiento.get(GregorianCalendar.MONTH);  		
		int dNac = fechaDeNacimiento.get(GregorianCalendar.DAY_OF_MONTH);

		GregorianCalendar fechaCumple= new GregorianCalendar(yA, mNac, dNac); 

		anios =  fechaActual.get(GregorianCalendar.YEAR) - fechaDeNacimiento.get(GregorianCalendar.YEAR);  // aca calculamos la edad como diferencia en a�os entre fechaNac y fechaActual

		if (fechaCumple.before(fechaActual)){   // ahora vemos las diferencias Si ya pas� el cumplea�os: La diferencia en a�os sigue igual?

			dias = dAct - dNac;
			meses = mAct - mNac;

			if (dias < 0  && dNac < 31){
				dias = 30 + dias;
				meses--;
			}	  			
			if (dias < 0 && dNac == 31){
				dias = 31 + dias;
				meses--;
			}
			if ( dias > 0 && meses <= 1){
				anios =  yA - yNac ;
			}
			if (dias == 0){
				meses = mAct - mNac;
			}
		} 

		if(fechaCumple.after(fechaActual)){
			// Si todavia no lleg� el cumplea�os: La diferencia en a�os sigue igual?
			anios --;		
			dias = dAct - dNac;   // Y dias?
			meses = 11 - mNac + mAct;

			if (dias > 0 || dias == 0){
				meses = 12 - mNac + mAct;
				if (meses == 12){
					meses = 0;
					anios ++;
				}	  		
			}	 
			if (dias < 0 && dNac < 31){
				dias = 30 + dias;
				meses = 11 - mNac + mAct;
				if (meses == 12){
					meses = 0;
					anios ++;
				}	  
			}
			if (dias < 0 && dNac == 31){
				dias = 31 + dias;
				meses = 11 - mNac + mAct;
				if (meses == 12){
					meses = 0;
					anios ++;
				}	  
			}  		 		
			if (fechaCumple == fechaActual){             // Como calculamos la diferencia sabiendo lo anterior? es el dia del cumplea�os!   Hay que hacer algo? 

				meses = 0;
				dias  = 0;		
			}
		}  				
		return "Tiene: " + anios + " años, " + meses + " meses  y  " + dias + " dias";      

	}
	
	public static int calculaEdad(GregorianCalendar fHoy, GregorianCalendar fechaNacimiento) {
		int anios=0;	int meses=0;	int dias=0;

		int yA = fHoy.get(GregorianCalendar.YEAR);
		int mAct = fHoy.get(GregorianCalendar.MONTH)+1;
		int dAct = fHoy.get(GregorianCalendar.DAY_OF_MONTH);
		
		int yNac = fechaNacimiento.get(GregorianCalendar.YEAR);
		int mNac = fechaNacimiento.get(GregorianCalendar.MONTH)+1;   // +1  		
		int dNac = fechaNacimiento.get(GregorianCalendar.DAY_OF_MONTH);

		GregorianCalendar fechaCumple= new GregorianCalendar(yA, mNac, dNac); 

		anios =  fHoy.get(GregorianCalendar.YEAR) - fechaNacimiento.get(GregorianCalendar.YEAR);

		if (fechaCumple.before(fHoy)){

			dias = dAct - dNac;
			meses = mAct - mNac;

			if (dias < 0  && dNac < 31){
				dias = 30 + dias;
				meses--;
			}	  			
			if (dias < 0 && dNac == 31){
				dias = 31 + dias;
				meses--;
			}
			if ( dias > 0 && meses <= 1){
				anios =  yA - yNac ;
			}
			if (dias == 0){
				meses = mAct - mNac;
			}
		} 
		if(fechaCumple.after(fHoy)){
			anios --;		
			dias = dAct - dNac;
			meses = 11 - mNac + mAct;

			if (dias > 0 || dias == 0){
				meses = 12 - mNac + mAct;
				if (meses == 12){
					meses = 0;
					anios ++;
				}	  		
			}	 
			if (dias < 0 && dNac < 31){
				dias = 30 + dias;
				meses = 11 - mNac + mAct;
				if (meses == 12){
					meses = 0;
					anios ++;
				}	  
			}
			if (dias < 0 && dNac == 31){
				dias = 31 + dias;
				meses = 11 - mNac + mAct;
				if (meses == 12){
					meses = 0;
					anios ++;
				}	  
			}  		 		
			if (fechaCumple == fHoy){

				meses = 0;
				dias  = 0;		
			}
		}  				
		return anios ;
	}

	public static boolean esBisiesto(int anio){
		if ((anio % 4 == 0) && (anio % 100 != 0) || (anio % 400 == 0)){
			return true;
		}
		else {
			return false;
		}
	}

	public static int traerAnio(GregorianCalendar f){
		return f.get(Calendar.YEAR); 
	}

	public static int traerMes(GregorianCalendar f){
		return f.get(Calendar.MONTH)+1;
	}

	public static int traerDia(GregorianCalendar f){
		return f.get(Calendar.DAY_OF_MONTH);
	}

	public static int traerHora(GregorianCalendar f){
		return f.get(Calendar.HOUR);
	}

	public static int traerMinuto(GregorianCalendar f){
		return f.get(Calendar.MINUTE);
	}

	public static int traerSegundo(GregorianCalendar f){
		return f.get(Calendar.SECOND);
	}	

	public static boolean esFechaValida(int anio, int mes, int dia){
		boolean valida = false;	
		if((mes-1 < 12) && (dia < 32)){
			if( (mes-1 == 0 || mes-1 == 2 || mes-1 == 4 || mes-1 == 6 || mes-1 == 7 || mes-1 == 9 || mes-1 == 11) && (dia < 32)) {
				valida = true;
			} 
			else if( (mes-1 == 3 || mes-1 == 5 || mes-1 == 8 || mes-1 == 10) && (dia < 31) ){
				valida = true;
			} 
			else if ((mes-1 == 1) && (esBisiesto(anio) == true) && (dia == 29)){
				valida = true;
			} 
			else if ((mes-1 == 1) && (dia < 29)) {
				valida = true;
			} 
		} 
		return valida;
	}

	public static GregorianCalendar traerFecha(int anio, int mes, int dia){
		GregorianCalendar fecha = new GregorianCalendar(anio, (mes-1), dia);
		return fecha;
	}

	public static String traeFechaCorta(GregorianCalendar fecha){		
		int anio=0, mes=0, dia=0;
		anio = fecha.get(GregorianCalendar.YEAR);
		mes = fecha.get(GregorianCalendar.MONTH)+1;
		dia = fecha.get(GregorianCalendar.DAY_OF_MONTH);
		return (dia +"/" + mes + "/" + anio);	
	}

	public static GregorianCalendar aGregorianCalendar(int anio, int mes, int dia){
		GregorianCalendar fecha = new GregorianCalendar(anio, (mes)-1, dia);
		fecha.setLenient(false);
		return fecha;
	}
	
    public static GregorianCalendar aGregorianCalendar(int anio, int mes, int dia, int hora, int minutos, int segundos){
    	GregorianCalendar fecha = new GregorianCalendar(anio, (mes)-1, dia);
		fecha.set(Calendar.HOUR_OF_DAY, hora);
		fecha.set(Calendar.MINUTE, minutos);
		fecha.set(Calendar.SECOND, segundos);
    	fecha.setLenient(false);
    	return fecha;
	}
	
	public static String fechaCorta(GregorianCalendar fecha){
		return (fecha.get(GregorianCalendar.DAY_OF_MONTH) +"/" + (fecha.get(GregorianCalendar.MONTH)+1) + 
				"/" + fecha.get(GregorianCalendar.YEAR));
	}

	public static String traerFechaCorta(LocalDateTime fecha){
		int anio=0, mes=0, dia=0;
		anio = fecha.getYear();
		mes = fecha.getMonthValue();
		dia = fecha.getDayOfMonth();
		return (dia +"/" + ((mes)) + "/" + anio);
	}
	
	public static String traerFechaCorta(LocalDate fecha){
		int anio=0, mes=0, dia=0;
		anio = fecha.getYear();
		mes = fecha.getMonthValue();
		dia = fecha.getDayOfMonth();
		return (dia +"/" + mes + "/" + anio);
	}
	
	public static LocalTime traerHora(LocalDateTime fecha){
		LocalTime hora = fecha.toLocalTime();
		return (hora);
	}

	public static String traerFecha(LocalDate t) {
		//retorna "dd de mes de aaaa"
		return(t.getDayOfMonth()+" de "+t.getMonth()+" de "+t.getYear());
	}
	
	public static String traeFechaCorta(LocalDate t) {
		//retorna dd/mm/aaaa
		return (t.getDayOfMonth()+"/"+t.getMonthValue()+"/"+t.getYear());
	}
	
	
	public static LocalDate sumarMes(LocalDate fecha) {	
		int mes,anio;
		int dia=fecha.getDayOfMonth();
		if(fecha.getMonthValue()==12) {
			mes=1;
			anio=fecha.getYear()+1;
		}else{
			mes=fecha.getMonthValue()+1;
			anio=fecha.getYear();
		}
		LocalDate f= LocalDate.of(anio, mes, dia);
		return f;
	}

	public static String traerFechaCortaHora ( GregorianCalendar fecha ){		                          
		return (fecha.get(GregorianCalendar.DAY_OF_MONTH) +"/" + (fecha.get(GregorianCalendar.MONTH)+1) + "/" + fecha.get(GregorianCalendar.YEAR) +
				" "+ fecha.get(GregorianCalendar.HOUR_OF_DAY)+ ":"+ fecha.get(GregorianCalendar.MINUTE)+":"+ fecha.get(GregorianCalendar.SECOND));
	}  

	public static String traeFecha(GregorianCalendar f){
		f.setLenient(false);
		int anyo = 0;   int mes = 0;   int dia = 0;

		anyo = f.get(GregorianCalendar.YEAR);
		mes = f.get(GregorianCalendar.MONTH)+1;
		dia = f.get(GregorianCalendar.DAY_OF_MONTH);
		return "Hoy es: " + dia +"/" + mes + "/" + anyo + "  y " + calculoBisiesto(f);     
	}
	
	public static String calculoBisiesto(GregorianCalendar f){
		GregorianCalendar este = new GregorianCalendar();
		int anyo = este.get(GregorianCalendar.YEAR); 
		if ((anyo % 4 == 0) && (anyo % 100 == 0)  || (anyo % 400 == 0)){
			return "El año es bisiesto";
		}else {
			return  "El año no es bisiesto";	
		}		
	}

	public static String traerFechaCortaHoraMin(GregorianCalendar fecha){
		String f =(fecha.get(GregorianCalendar.DAY_OF_MONTH) + "/"
				+ (fecha.get(GregorianCalendar.MONTH)+1)	
				+ "/" + fecha.get(GregorianCalendar.YEAR) 
				+ "-" + fecha.get(GregorianCalendar.HOUR) 
				+ ":" + fecha.get(GregorianCalendar.MINUTE)+ ":" + fecha.get(GregorianCalendar.SECOND));

		return f;
	}

	public int calculaDiasEntreDosFechas(){
		int dias=0;
		GregorianCalendar c = new GregorianCalendar();
		GregorianCalendar fechaInicio = new GregorianCalendar(2011, 03, 1);
		GregorianCalendar fechaFin= new GregorianCalendar(2011, 03, 15);		
		//aca puedo poner condiciones if
		c.setTimeInMillis(fechaFin.getTime().getTime()- fechaInicio.getTime().getTime());

		dias=c.get(GregorianCalendar.DAY_OF_YEAR);

		return dias;				
	}

	@SuppressWarnings("deprecation")
	public static String traerFechaHora (GregorianCalendar fecha){
		GregorianCalendar f1 = new GregorianCalendar(fecha.get(GregorianCalendar.YEAR), 
				(fecha.get(GregorianCalendar.MONTH)),
				fecha.get(GregorianCalendar.DAY_OF_MONTH),fecha.get(GregorianCalendar.HOUR_OF_DAY),
				fecha.get(GregorianCalendar.MINUTE), fecha.get(GregorianCalendar.SECOND));
		return f1.getTime().toLocaleString();
	}

	public static GregorianCalendar traerFechaProximo(GregorianCalendar fecha, int cantDias){
		int dia = fecha.get(GregorianCalendar.DAY_OF_MONTH) + cantDias;
		fecha = aGregorianCalendar(fecha.get(GregorianCalendar.YEAR), fecha.get(GregorianCalendar.MONTH)+1, dia);
		return fecha;
	}

	public static String traerFechaProx(LocalDate fecha, TemporalAmount cantDias){
		fecha.plus(cantDias);
		return traerFechaCorta(fecha);
	}

	public static boolean esDiaHabil(GregorianCalendar fecha){
		boolean habil = false;
		int dia = 0;
		fecha = aGregorianCalendar(fecha.get(GregorianCalendar.YEAR), fecha.get(GregorianCalendar.MONTH)+1, fecha.get(GregorianCalendar.DAY_OF_MONTH));
		dia = fecha.get(GregorianCalendar.DAY_OF_WEEK);
		if(dia>1 && dia <7 ){
			habil = true;
		}
		return habil;
	}

	public static boolean esHabil(GregorianCalendar fecha){
		boolean habil = false;
		int dia = 0;
		dia = fecha.get(GregorianCalendar.DAY_OF_WEEK)-1;
		if(dia >=2 && dia <= 6){
			habil = true;
		}
		return habil;
	}

	public static String traerDiaDeLaSemana(GregorianCalendar fecha){
		int dia = 0;
		dia = fecha.get(GregorianCalendar.DAY_OF_WEEK)-1;
		//creo un array con los dias de la semana
		String vdia[] = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"}; 
		return vdia[dia];
	}

	public static String traerMesEnLetras(GregorianCalendar fecha){
		int mes = 0;
		mes = fecha.get(GregorianCalendar.MONTH);
		String vmes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
				"Septiembre", "Octubre", "Noviembre", "Diciembre"};
		return vmes[mes];
	}

	public static String traerMesLetras(int anio, int mes){
		int mesint = 0;  //en un array de string cargo los meses
		String mes1[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio",	
				"Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
		//el mes de la fecha lo convierto en la posicion de mi array y me devuelve el mes en letras
		return mes1[mesint+1]; 
	}

	public static boolean enVigencia(GregorianCalendar fecha){
		boolean vigente=false;
		GregorianCalendar fechaIni = new GregorianCalendar(2016,8,6); 
		GregorianCalendar fechaFin = new GregorianCalendar(2016,9,12); 
		if(fecha.after(fechaIni)&&fecha.before(fechaFin)){
			vigente=true;
		}
		return vigente;
	}	
	
	public static boolean enVigencia(GregorianCalendar f1, GregorianCalendar f2){
		boolean valida = false;
		GregorianCalendar fecha = new GregorianCalendar();
		if(f1.after(fecha ) && f2.before(fecha)){
			valida=true;
		}
		return valida;
	}
	
	public static boolean esAnterior(GregorianCalendar fecha1) throws Exception{
		boolean anterior = false;
		GregorianCalendar fecha = new GregorianCalendar(); 
		if(fecha1.before(fecha)){
			anterior=true;
		}else{
			throw new Exception("La Fecha NO es valida");
		}
		return anterior;
	}

	public static String traerFechaLarga(GregorianCalendar fecha){
		int dia = 0;
		int anio = 0;
		dia = fecha.get(GregorianCalendar.DAY_OF_MONTH);
		anio = fecha.get(GregorianCalendar.YEAR);
		return traerDiaDeLaSemana(fecha) + " " + dia + " de " + traerMesEnLetras(fecha) + " del " + anio;
	}

	public static String traeFechaLarga(GregorianCalendar fecha){

		return ("Hoy es "+ Funciones.fechaForm(fecha) +"  "+ fecha.get(GregorianCalendar.DAY_OF_MONTH) + 
				"  de  " + Funciones.mesForm(fecha) + "  de  " + fecha.get(GregorianCalendar.YEAR));
	}

	public static boolean sonFechasIguales(GregorianCalendar fecha, GregorianCalendar fecha1){
		boolean iguales = false;
		if(fecha.get(GregorianCalendar.DAY_OF_MONTH) == fecha1.get(GregorianCalendar.DAY_OF_MONTH) &&
				fecha.get(GregorianCalendar.MONTH) == fecha1.get(GregorianCalendar.MONTH) &&
				fecha.get(GregorianCalendar.YEAR) == fecha1.get(GregorianCalendar.YEAR) ) {
			iguales = true;
		}
		return iguales;
	}

	public static boolean fechasIguales(GregorianCalendar fecha, GregorianCalendar fecha1){ 
		boolean sonIguales = false;  //inicializo en false la variable sonIguales
		if(fecha.equals(fecha1)){  //comparo las dos fechas con equals
			sonIguales = true;	//si son iguales se pone en true la variable sonIguales
		}
		return sonIguales; 	//retorno sonIguales		
	}
	
	public static boolean sonFechasHorasIguales(GregorianCalendar fecha, GregorianCalendar fecha1){
		boolean iguales = false;
		if( fecha.get(GregorianCalendar.YEAR) == fecha1.get(GregorianCalendar.YEAR) &&
				fecha.get(GregorianCalendar.MONTH) == fecha1.get(GregorianCalendar.MONTH) &&
				fecha.get(GregorianCalendar.DAY_OF_MONTH) == fecha1.get(GregorianCalendar.DAY_OF_MONTH) &&
				fecha.get(GregorianCalendar.HOUR) == fecha1.get(GregorianCalendar.HOUR) &&
				fecha.get(GregorianCalendar.MINUTE) == fecha1.get(GregorianCalendar.MINUTE) &&
				fecha.get(GregorianCalendar.SECOND) == fecha1.get(GregorianCalendar.SECOND) ) {
			iguales = true;
		} // end_if
		return iguales;
	} // end_public

	//  16.1 - METODO  fechasHorasIguales/////////////////////////////////////////

	public static boolean fechasHorasIguales(GregorianCalendar fecha, GregorianCalendar fecha1){
		boolean sonIguales = false;  //inicializo en false la variable sonIguales
		if(fecha.equals(fecha1)){  //comparo las dos fechas con equals
			sonIguales = true;	//si son iguales se pone en true la variable sonIguales
		}
		return sonIguales; 	//retorno sonIguales		
	}


	// 16.2 - METODO sonFechasIguales PARA QUE DE ERROR    /////////////////////////////////////////////

	public static boolean sonFechasHorasIgualesError(GregorianCalendar fecha, GregorianCalendar fecha1){
		boolean iguales = false;
		int minutos = fecha1.get(GregorianCalendar.MINUTE) + 1;
		if( fecha.get(GregorianCalendar.YEAR) == fecha1.get(GregorianCalendar.YEAR) &&
				fecha.get(GregorianCalendar.MONTH) == fecha1.get(GregorianCalendar.MONTH) &&
				fecha.get(GregorianCalendar.DAY_OF_MONTH) == fecha1.get(GregorianCalendar.DAY_OF_MONTH) &&
				fecha.get(GregorianCalendar.HOUR) == fecha1.get(GregorianCalendar.HOUR) &&
				fecha.get(GregorianCalendar.MINUTE) == (minutos) &&
				fecha.get(GregorianCalendar.SECOND) == fecha1.get(GregorianCalendar.SECOND) ) {
			iguales = true;
		} // end_if
		return iguales;
	} // end_public

	// 17 - METODO traerCantDiasDeUnMes    ///////////////////////////////////////////////////////////

	public static int traerCantDiasDeUnMes(int anio, int mes){
		int cantidad = 0;
		if( (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) ) {
			cantidad = 31;
		} // end_if
		if( (mes == 4 || mes == 6 || mes == 9 || mes == 11) ){
			cantidad = 30;
		} // end_if
		if(mes == 2) {
			cantidad = 28;
			if(esBisiesto(anio) == true){
				cantidad = 29;
			} // end_if
		} // end_if
		return cantidad;
	} // end_public

	// 18 - METODO CORTO FECHA VALIDA USANDO ===>  METODO CANT DE DIAS DEL MES  //////////////////////

	public static boolean esFechaValida2(int anio, int mes, int dia){
		boolean valida = true;
		if ( (anio < 1) || (mes < 1 || mes > 12) || (dia < 1 || dia > traerCantDiasDeUnMes(anio, mes)) ){
			valida = false;
		} // end_if
		return valida;
	} // end_public_esFechaValida2


	// 19 - METODO aproximar2Decimal //////////////////////////////////////////////////////////////

	public static double aproximar2Decimal(double valor){
		return Math.rint(valor*100)/100; 
	} // end_public


	// 20 - METODO esNumero (boolean)   ///////////////////////////////////////////

	public static boolean esNumero(char c) throws Exception{
		int i = 0;  
		boolean numero = false;
		String diccionarioNum = "0123456789";

		char arrayNum[] = diccionarioNum.toCharArray();
		int n = diccionarioNum.length();
		for (i = 0;  i<n; i++){
			if (c == arrayNum[i]){
				numero = true;
			}
		}
		if (numero == false){
			throw new Exception("ERROR: No es un numero");  
		}
		return numero;
	}

	// 20.1 METODO   esNum   SIN TROWS EXCEPTION   /////////////////////////////////////////////

	public static boolean esNum(char c){
		int i=0;
		boolean num= false;
		String tablaNum = "0123456789";
		char arrayNum[] = tablaNum.toCharArray();
		int size = tablaNum.length();
		for ( i = 0; i<size; i++){
			if(c == arrayNum[i]){
				num= true;
			}
		}
		return num;		
	}

	// 21 - METODO    esCaracter    //////////////////////////////////////////

	public static boolean esCaracter(char c) throws Exception{
		int i = 0;
		boolean caracter = false;
		String diccionarioLetras = "abcdefghijklmn�opqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ ";
		int n = diccionarioLetras.length();
		char arrayLetras[] = diccionarioLetras.toCharArray();
		for (i = 0; i<n; i++){
			if (c == arrayLetras[i]){
				caracter = true;
			}
		}
		if (caracter == false){ 
			throw new Exception("ERROR: No es una Letra");
		}        
		return caracter;
	}

	// 21.1 -  METODO   esCaract   SIN TROWS EXCEPTION  /////////////////////////////////////////

	public static boolean esCaract(char c){
		int i=0;
		boolean caracter=false;
		String tablaLetras = "abcdefghijklmn�opqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ";
		int size = tablaLetras.length();
		char arrayLetras[] = tablaLetras.toCharArray();
		for (i = 0; i < size; i++){
			if(c == arrayLetras[i]){
				caracter=true;
			}
		}
		return caracter;
	}

	// 22 - METODO    esCadNum    //////////////////////////////////////////

	public static boolean esCadNum(String cadena) throws Exception{
		boolean numero = false;
		int i;
		int q = cadena.length();
		char arrayCad[] = cadena.toCharArray();
		for (i=0; i<q; i++){
			if( esNumero(arrayCad[i])){
				numero = true;
			}else{
				throw new Exception("ERROR: No es cadena de Numeros");
			}
		}
		return numero;
	}

	// 22.1 METODO esCadenaNumeros SIN TROWS EXCEPTION ///////////////////////////////////

	public static boolean esCadNumeros(String cadena){
		boolean numero = false;
		int i = 0;    
		int esLetra=0;
		int size = cadena.length();
		char arrayCad[] = cadena.toCharArray();
		for (i=0;i<size;i++){
			if( esCaract(arrayCad[i])== true){
				esLetra= esLetra +1;        		
			} 
		} 
		if (esLetra == 0){
			numero= true;
		}
		return numero;
	} 

	// 23 - METODO   esCadLetras     /////////////////////////////////////////    
	public static boolean esCadLetras(String cadena) throws Exception{
		boolean caracter = false;
		//	cadena = cadena.toLowerCase();
		int i;
		int q = cadena.length();
		char arrayCad[] = cadena.toCharArray();
		for (i=0; i<q; i++){
			if( esCaracter(arrayCad[i])){
				caracter = true;
			}
			else{
				throw new Exception("ERROR: No es cadena de Letras");
			}
		}
		return caracter;
	}

	// 23.1 - METODO  esCadenaLetras SIN TROWS EXCEPTION   ///////////////////////////////////////

	public static boolean esCadLetra(String cadena){
		boolean escadena = false;
		int i = 0;
		int  esNumero = 0; //la variable entera esNumero en cero indica que no hay ningun numero en la cadena
		int size = cadena.length();
		char arrayCad[] = cadena.toCharArray();
		for (i=0; i<size; i++){
			if( (esNum(arrayCad[i])) == true ){ ///si esNumero me da verdadero entonces
				esNumero = esNumero + 1; //incremento en 1 la variable entera esNumero
			} 
		} //termino de recorrer la cadena con el for 
		if(esNumero == 0){ //si esNumero no se incremento entonces significa que la cadena es de letras
			escadena = true;
		}
		return escadena;
	} 

	// 23.3  METODO   sonDiferentes  ////////// PARA PRACTICO DE CETA 
	// DETERMINA SI LAS DOS CADENAS SON IGUALES, EN ESTE CASO COMPRADOR Y VENDEDOR

	public static boolean sonDiferentes(String cad1, String cad2, String cad3, String cad4) throws Exception{
		boolean distintos = false;
		if( (!cad1.equals(cad2)) || (!cad3.equals(cad4))){
			distintos = true;
		}
		else{
			throw new Exception("ERROR: Comprador y vendedor son el mismo");
		}
		return distintos;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////


	// 24 - METODO convertirADouble   ////////////////////////////////////////////////
	public static double convertirADouble(int n){
		//String.valueOf(n) PASA EL NUMERO n A TIPO STRING
		return Double.parseDouble(String.valueOf(n)); // DESDE UN STRING DEVUELVE UN DOUBLE
	}

	public static String convertirAString(int n){
		String str;
		str = String.valueOf(n);
		return str;
	}
	
	public static String imprimeFecha(GregorianCalendar FechaHoraSalida){
		String dia = Funciones.traerDiaDeLaSemana(FechaHoraSalida);
		String mes = Funciones.traerMesEnLetras(FechaHoraSalida);
		int diaNumero = FechaHoraSalida.get(Calendar.DAY_OF_MONTH);
		int anio = FechaHoraSalida.get(Calendar.YEAR);
		int hora = FechaHoraSalida.get(Calendar.HOUR_OF_DAY);
		int minuto = FechaHoraSalida.get(Calendar.MINUTE);
		return dia + " " + diaNumero + " de " + mes + " de " + anio + " - " + hora + ":" + minuto;
	}
	
	public static boolean esPar(int n) {
		boolean par;
		if( n % 2 == 0){
			par = true;
		} else {
			par = false;
		}			
		return par;
	}

	public static boolean esPrimo(int n){
		boolean primo = true;
		int s = 2;
		if (n % s == 0 || n == 1) {
			if (n % s == 0){
				primo = false;
			}
			s++;
		}
		return primo;
	}

	public static int factorial(int n) {
		if (n<=1) {
			return 1;
		} else {
			return n * factorial(n-1);
		}	
	}
	
	public static boolean validarSexo(char sex) throws Exception{
		boolean sexoValido=false;
		if(sex=='M'||sex=='F' || sex=='m' || sex=='f'){			
			sexoValido=true;
		} else {
			throw new Exception("Sexo mal ingresado");
		}
		return sexoValido;
	}

	public static boolean esSexoValido(char c){ 
		String diccionario_sexo = "FfMm";
		boolean sexoValido = false;
		int i = 0;
		for (i= 0; i<(diccionario_sexo.length()); i++){ 
			if (c == diccionario_sexo.charAt(i)){
				sexoValido = true;
			}
		}
		return sexoValido;  							
	}	

	public static int[] arrayCuil(String stringCuil){
		int xcuil[] = new int [3];
		String[] j= stringCuil.split("-");
		xcuil[0] = Integer.parseInt(j[0]);
		xcuil[1] = Integer.parseInt(j[1]);
		xcuil[2] = Integer.parseInt(j[2]);
		int cuil[] = new int [11];
		long xdni = 0;
		xdni = xcuil[1];
		int n = 0;
		int carga = 0;
		long previo = 0;
		long divisor = 10000000;
		int verificador = xcuil[0];
		previo = verificador/10;
		carga = (int) previo;
		cuil[0] = carga;
		previo = verificador%10;
		carga = (int) previo;
		cuil[1] = carga;
		for(n=2;n<=9;n++){
			previo = xdni/divisor;
			carga = (int) previo;
			cuil[n] = carga; 
			previo = xdni%divisor;
			xdni = (long) previo;
			divisor = divisor/10;
		}
		cuil[10] = xcuil[2];
		return cuil;
	}

	public static boolean validarCuil(String stringCuil) throws Exception {
		boolean verifica = false;
		int[] cuil = arrayCuil(stringCuil); 
		if (cuil.length != 11) { 
			throw new Exception("El Cuil NO es valido");
		} else {
			int[] mult = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
			int valor1 = 0;

			for (int i = 0; i < mult.length; i++) {
				valor1 += cuil[i] * mult[i];
			}
			int mod = 0;   int digito = 0;
			mod = valor1 % 11;
			int valor3 = 11 - mod;
			if (valor3 == 11) {
				digito = 0;
			}else if (valor3 == 10) {
				digito = 9;
			}else {
				digito = valor3;
			}
			if (digito == cuil[10]){
				verifica = true;
			}else{
				throw new Exception("El Cuil NO es valido");
			} // end_else
		return verifica;
	}

	public static boolean validarDominio(String dominio) throws Exception {
		boolean patente = false;
		if(Funciones.esCadLetra(dominio.substring(0, 2)) && Funciones.esCadNumeros(dominio.substring(3, 5))){
			patente = true;
		}else{
			throw new Exception("Error: No es un dominio valido");
		}
		return patente;
	}

	protected void intercambiar(int[] array, int indice1, int indice2){		                  
		int tmp = array[indice1];  
		array[indice1] = array[indice2];
		array[indice2] = tmp;
	}

	public int[] ordenar(int[] array) {
		return quickSort(array, 0, array.length);
	}

	public int[] quickSort(int[] arr, int comienzo, int fin) {
		if (fin - comienzo < 2) return arr;
		int p = comienzo + ((fin-comienzo)/2);
		p = particionar(arr,p,comienzo,fin);
		quickSort(arr, comienzo, p);
		quickSort(arr, p+1, fin);
		return arr;
	}

	private int particionar(int[] array, int p, int comienzo, int fin) {
		int c = comienzo;
		int f = fin - 2;
		int pivote = array[p];
		intercambiar(array,p,fin-1);
		while (c < f) {
			if (array[c] < pivote) {
				c++;
			} else if (array[f] >= pivote) {
				f--;
			} else {
				intercambiar(array,c,f);
			}
		}
		int indice = f;
		if (array[f] < pivote) indice++;
		intercambiar(array,fin-1,indice);
		return indice;
	}

	public int[] burbuja(int[] array) {
		int lenD = array.length;
		boolean ordenado=false;
		for(int i=0; i<lenD && !ordenado; i++){
			ordenado=true;
			for(int j=(lenD-1); j>=(i+1); j--){
				if(array[j] < array[j-1]){
					ordenado=false;
					intercambiar(array,j,j-1);
				}
			}
		}
		return array;
	}

	public int[] insercion(int[] array) {
		int p, j;
		int aux;
		for (p = 1; p < array.length; p++){
			aux = array[p];
			j = p - 1;
			while ((j >= 0) && (aux < array[j])){
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = aux;  
		}	
		return array;
	}

	public int[] seleccion(int[] array) {
		int i, j, menor, pos;
		for (i = 0; i < array.length - 1; i++) {
			menor = array[i]; 
			pos = i;

			for (j = i + 1; j < array.length; j++){
				if (array[j] < menor) {
					menor = array[j];
					pos = j;
				}
			}
			if (pos != i){
				intercambiar(array, i, pos);  
			}
		}
		return array;
	}
}

