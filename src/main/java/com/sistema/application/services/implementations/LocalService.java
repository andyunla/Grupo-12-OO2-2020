package com.sistema.application.services.implementations;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.converters.LocalConverter;
import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.dto.EmpleadoDto;
import com.sistema.application.dto.LocalDistanciaDto;
import com.sistema.application.dto.ProductoRankingDto;
import com.sistema.application.entities.Factura;
import com.sistema.application.entities.Local;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.repositories.ILocalRepository;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.IFacturaService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.ILoteService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("localService")
public class LocalService implements ILocalService {

	//Atributos
    @Autowired
	@Qualifier("localRepository")
	private ILocalRepository localRepository;
	
	@Autowired
	@Qualifier("localConverter")
    private LocalConverter localConverter;
	
	//Services
	@Autowired
	@Qualifier("facturaService")
	IFacturaService facturaService;
	@Autowired
	@Qualifier("productoService")
	IProductoService productoService;
	@Autowired
	@Qualifier("itemService")
	IItemService itemService;
	@Autowired
	@Qualifier("empleadoService")
	IEmpleadoService empleadoService;
	@Autowired
	@Qualifier("loteService")
	ILoteService loteService;
	
	//Converters
	@Autowired
	@Qualifier("productoConverter")
	ProductoConverter productoConverter;
	@Autowired
	@Qualifier("empleadoConverter")
	private EmpleadoConverter empleadoConverter;
	
	//Métodos
    @Override
    public LocalModel findByIdLocal(long idLocal) {
         return localConverter.entityToModel(localRepository.findByIdLocal(idLocal) );
    }
	
     @Override
     public List<Local> getAll(){
    	 return localRepository.findAll();
     }
     
     public List<LocalModel> getAllModel() {
          List <LocalModel> localesModelos = new ArrayList<LocalModel>();
          for(Local entidadLocal: localRepository.findAll()){
               LocalModel modeloLocal = localConverter.entityToModel(entidadLocal);
               localesModelos.add(modeloLocal);
          }
          return localesModelos;
     }

     @Override
     public LocalModel insertOrUpdate(LocalModel localModel) {
          Local localEntidad = localConverter.modelToEntity(localModel);
          Local localGuardado = localRepository.save(localEntidad);
          return localConverter.entityToModel(localGuardado);
     }

     @Override
     public boolean remove(long id) {
          try{
               localRepository.deleteById(id);
               return true;
          }
          catch(Exception e){
               return false;
          }
     }
     
     @Override
     public LocalModel findByNombreLocal(String nombreLocal) {
    	 return localConverter.entityToModel(localRepository.findByNombreLocal(nombreLocal) );
     }
     
     @Override
     public LocalModel findByDireccion(String direccion) {
    	 return localConverter.entityToModel(localRepository.findByDireccion(direccion) );
     }
     /****************************************************************************************************/
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	// 6) CALCULO DE DIISTANCIA ENTRE LOCALES/////////////////////////////////////////////////////////////
 	////////////////////////////////////////////////////////////////////////////////////////////////////// 
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	/****************************************************************************************************/
 	public List<LocalDistanciaDto> localesCercanos(long idProducto, int cantidad, long idLocal) {
 		List<LocalDistanciaDto> lista = new ArrayList<LocalDistanciaDto>();
 		ProductoModel producto = productoService.findByIdProducto(idProducto);
 		LocalModel localModel = findByIdLocal(idLocal);
 		
 		for (LocalModel lo : getAllModel()) {
 			if(lo.getIdLocal() != idLocal && loteService.verificarStock(producto, lo, cantidad)) {
 			LocalDistanciaDto localDistanciaDto = localConverter.modelToDistanciaDto(lo);
 			localDistanciaDto.setDistancia(localModel.calcularDistancia(lo));
 			localDistanciaDto.setStock(loteService.calcularStock(producto, lo));
 			lista.add(localDistanciaDto);
 			}
		} 		
 		//ordeno la lista
 		Collections.sort(lista); 		
 		return lista;
 	}
		
    /****************************************************************************************************/
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	// 13) CIERRE DEL MES PARA DEFINIR EL SUELDO DE LOS EMPLEADOS/////////////////////////////////////////
 	////////////////////////////////////////////////////////////////////////////////////////////////////// 
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	/****************************************************************************************************/
 	public EmpleadoDto calcularSueldo(EmpleadoModel empleado, LocalDate fecha) {
 		EmpleadoDto emp = empleadoConverter.modelToDto(empleado);
 		double comisionVentaCompleta = 0;
 		double comisionVentaExterna = 0;
 		double comisionStockCedido = 0;
 		
 		for (Factura fa : traerFacturaMes(fecha)) {// para cada factura del mes pasado
 			
 			if (fa.getEmpleado().getDni() == empleado.getDni() ) { // si la factura pertenece a este empleado
 				// el chango de la factura tiene un pedido stock, esta factura es con stock de otro local
 				if (fa.getChango().getPedidostock() != null && fa.getChango().getPedidostock().getEmpleadoSolicitante().getDni() == empleado.getDni() ) {
 					// si el empleado solicito stock de otro local se calcula la comision de 3%
 					comisionVentaExterna = comisionVentaExterna + ((fa.getCosteTotal() * 3) / 100);
 				}				
 				// si este empleado no pidio stock se calcula la comision del 5%
 				if (fa.getChango().getPedidostock() == null) {
 					comisionVentaCompleta = comisionVentaCompleta + ((fa.getCosteTotal() * 5) / 100);
 				} 				
 			}
 			else {
 				// si la factura no es de este empleado y si este empleado ofreció stock se le calcula el 2%
 				if (fa.getChango().getPedidostock() != null && fa.getChango().getPedidostock().getEmpleadoOferente().getDni() == empleado.getDni() ) {
 					comisionStockCedido = comisionStockCedido + ((fa.getCosteTotal() * 2) / 100);
 				} 
 			}
 		} 		
 		emp.setSueldoFinal(emp.getSueldoBasico() + comisionVentaCompleta + comisionVentaExterna + comisionStockCedido);
 		emp.setComisionVentaCompleta(comisionVentaCompleta);
 		emp.setComisionVentaExterna(comisionVentaExterna);
 		emp.setComisionStockCedido(comisionStockCedido); 		
 		return emp;
 	}	
 	public List<Factura> traerFacturaMes(LocalDate fecha) {
		LocalDate fecha1 = fecha.withDayOfMonth(1);// mes pasado dia 1
		LocalDate fecha2 = fecha.withDayOfMonth(fecha1.lengthOfMonth());// último día del mes pasado
		return facturaService.findByFechaFacturaBetween(fecha1, fecha2);// retorno la lista de facturas
	}
 	/***********************************************************************************************************************************************************/
 	public List<EmpleadoDto> calcularSueldos(long idLocal, LocalDate fecha) {
 		List<EmpleadoDto> empleadosDto = new ArrayList<EmpleadoDto>();
 		List<EmpleadoModel> empleadosModel = empleadoService.findByIdLocal(idLocal);
 		// a cada empleado del local correspondiente le calculo el sueldo y lo agrego a la lista
 		for (EmpleadoModel e : empleadosModel) {
			empleadosDto.add(calcularSueldo(e, fecha));
		} 		
 		return empleadosDto;
 	} 	
 	/*****************************************************************************************************************************************************************/
 	public List<EmpleadoDto> calcularSueldoGlobal(LocalDate fecha) { 		
 		List<EmpleadoDto> listaEmpleados = new ArrayList<EmpleadoDto>();
 		//calculo el sueldo de todos los empleados en cada local
 		for (LocalModel l : getAllModel()) {
			for (EmpleadoDto empleadoDto : calcularSueldos(l.getIdLocal(), fecha)) {
				listaEmpleados.add(empleadoDto);
			}
		} 
 		//lo retorno en una sola lista general
 		return listaEmpleados;
 	}
     
     
    /****************************************************************************************************/
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	// 14) EMITIR REPORTE DE PRODUCTOS VENDIDOS ENTRE FECHAS POR LOCAL////////////////////////////////////
 	////////////////////////////////////////////////////////////////////////////////////////////////////// 
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	/****************************************************************************************************/
     public List<ProductoRankingDto> reporte(LocalDate fecha1, LocalDate fecha2, long idLocal){
    	 List<ProductoRankingDto> productoReporte = new ArrayList<ProductoRankingDto>();
	 List<FacturaModel> listaFacturas = facturaService.findByFechaFacturaBetweenAndIdLocal(fecha1, fecha2, idLocal);
    	 List<ProductoModel> productos = productoService.getAllModel();
    	 for (ProductoModel pro : productos ) {
    		int cantidad = cantidadProductoVendido(pro, listaFacturas);
			productoReporte.add(productoConverter.modelToDto(pro, cantidad));
		}
    	//orden de mayor a menor
 		Collections.sort(productoReporte, Collections.reverseOrder());	
    	return productoReporte;
     }
    /****************************************************************************************************/
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	// 15) RANKING DE PRODUCTOS MÁS VENDIDOS////////////////////////////////////////////////////////////// 
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	//////////////////////////////////////////////////////////////////////////////////////////////////////
 	/****************************************************************************************************/
     public List<ProductoRankingDto> ranking(){
    	 List<ProductoRankingDto> productoRanking = new ArrayList<ProductoRankingDto>();
    	 List<FacturaModel> listaFacturas =  facturaService.getAllModel();
    	 List<ProductoModel> productos = productoService.getAllModel();
    	 for (ProductoModel pro : productos ) {
    		int cantidad = cantidadProductoVendido(pro, listaFacturas);
			productoRanking.add(productoConverter.modelToDto(pro, cantidad));
		}
    	//orden de mayor a menor
 		Collections.sort(productoRanking, Collections.reverseOrder());	
    	return productoRanking;
     }
     public int cantidadProductoVendido(ProductoModel producto, List<FacturaModel> listaFacturas) {
 		int cantidad = 0;
 		for (FacturaModel fa : listaFacturas) {
 			// de cada factura obtengo el chango y traigo el item que tenga el producto que
 			// estamos evaluando
 			// si el producto está en la factura, se suma la cantidad correspondiente
 			ItemModel it = itemService.findByChangoAndProducto(fa.getChango().getIdChango(), producto.getIdProducto());
 			if (it != null) {
 				cantidad = cantidad + it.getCantidad();
 			}				
 		}
 		return cantidad;
 	}
     
}
