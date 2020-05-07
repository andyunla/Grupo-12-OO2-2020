package com.sistema.application.converters;

import com.sistema.application.models.LoteModel;
import com.sistema.application.entities.Lote;

public class LoteConverter {
	
	//De entidad a modelo
	public LoteModel entityToModel(Lote lote) {
		return new LoteModel(lote.getIdLote(), lote.getCantidadInicial(), lote.getCantidadActual(),
				lote.getFechaIngreso(), lote.getProducto().getIdProducto(), lote.isActivo() );
	}
	
	//De modelo a entidad
	public Lote modelToEntity(LoteModel loteModel) {
		return new Lote(loteModel.getCantidadInicial(), loteModel.getCantidadActual(), 
				loteModel.getFechaIngreso(), "loteModel.getIdProducto()", loteModel.isActivo() );
	}
	
}
