package com.sistema.application.converters;

import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.LoteModel;

import java.util.Set;

import com.sistema.application.entities.Local;

public class LocalConverter {

	//De entidad a modelo
	public LocalModel entityToModel(Local local) {
		return new LocalModel(local.getIdLocal(), local.getNombreLocal(), local.getLatitud(), local.getLongitud(), local.getDireccion(),local.getTelefono(),
				local.getGerente().getLegajo(), local.getListaLotes(), local.getListaEmpleados(), local.getListaChangos(),
				local.getListaFacturas());
	}
	
	//De modelo a entidad
	public Local modelToEntity(LocalModel localModel) {
		return new Local();
	}
	
}
