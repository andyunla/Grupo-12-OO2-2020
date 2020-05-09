package com.sistema.application.converters;

import com.sistema.application.models.LoteModel;
import com.sistema.application.repositories.IProductoRepository;

import org.springframework.stereotype.Component;

import com.sistema.application.entities.Lote;
import com.sistema.application.entities.Producto;

@Component("loteConverter")
public class LoteConverter {
	
	//De entidad a modelo
	public LoteModel entityToModel(Lote lote) {
		return new LoteModel(lote.getIdLote(), lote.getCantidadInicial(), lote.getCantidadActual(),
				lote.getFechaIngreso(), lote.getProducto().getIdProducto(), lote.isActivo() );
	}
	
	//De modelo a entidad
	public Lote modelToEntity(LoteModel loteModel) {
		IProductoRepository ipr = null;
		Producto prod = ipr.findByIdProducto(loteModel.getIdProducto());
		return new Lote(loteModel.getCantidadInicial(), loteModel.getCantidadActual(), 
				loteModel.getFechaIngreso(), prod );
	}
	
}
