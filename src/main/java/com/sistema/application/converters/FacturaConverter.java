package com.sistema.application.converters;

import com.sistema.application.entities.Factura;
import com.sistema.application.models.FacturaModel;

public class FacturaConverter {

	//De entidad a modelo
	public FacturaModel entityToModel(Factura factura) {
		return new FacturaModel();
	}
	
	//De modelo a entidad
	public Factura modelToEntity(FacturaModel facturaModel) {
		return new Factura();
	}
	.
}
