package com.sistema.application.converters;

import com.sistema.application.models.LoteModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IProductoRepository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Lote;
import com.sistema.application.entities.Producto;

@Component("loteConverter")
public class LoteConverter {
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	//De entidad a modelo
	public LoteModel entityToModel(Lote lote) {
		return new LoteModel(lote.getIdLote(), lote.getCantidadInicial(), lote.getCantidadActual(),
				lote.getFechaIngreso(),productoConverter.entityToModel( lote.getProducto()), lote.isActivo() );
	}
	
	//De modelo a entidad
	public Lote modelToEntity(LoteModel loteModel) {
		
		return new Lote(loteModel.getIdLote(), loteModel.getCantidadInicial(), loteModel.getCantidadActual(), 
				loteModel.getFechaIngreso(), productoConverter.modelToEntity(loteModel.getProductoModel()) );
	}
	
}
