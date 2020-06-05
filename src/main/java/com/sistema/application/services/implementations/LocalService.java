package com.sistema.application.services.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.sistema.application.converters.LocalConverter;
import com.sistema.application.converters.ProductoConverter;
import com.sistema.application.dto.ProductoRankingDto;
import com.sistema.application.entities.Local;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.models.ItemModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.ILocalRepository;
import com.sistema.application.services.IFacturaService;
import com.sistema.application.services.IItemService;
import com.sistema.application.services.ILocalService;
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
	
	//Converters
	@Autowired
	@Qualifier("productoConverter")
	ProductoConverter productoConverter;
	
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
