package com.sistema.application.converters;

import com.sistema.application.models.LoteModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Lote;

@Component("loteConverter")
public class LoteConverter {
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;

	// De entidad a modelo
	public LoteModel entityToModel(Lote lote) {
		return new LoteModel(lote.getIdLote(), lote.getCantidadInicial(), lote.getCantidadActual(),
				lote.getFechaIngreso(), productoConverter.entityToModel(lote.getProducto()), lote.isActivo(),
				localConverter.entityToModel(lote.getLocal()));
	}

	// De modelo a entidad
	public Lote modelToEntity(LoteModel loteModel) {

		return new Lote(loteModel.getIdLote(), loteModel.getCantidadInicial(), loteModel.getCantidadActual(),
				loteModel.getFechaIngreso(), productoConverter.modelToEntity(loteModel.getProductoModel()),
				loteModel.isActivo(), localConverter.modelToEntityWithoutGerente(loteModel.getLocal())); 
	}

}
