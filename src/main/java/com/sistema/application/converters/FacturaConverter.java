package com.sistema.application.converters;

import java.time.LocalDate;

import com.sistema.application.entities.Chango;
import com.sistema.application.entities.Cliente;
import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Factura;
import com.sistema.application.entities.Local;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.repositories.IClienteRepository;

public class FacturaConverter {
	ClienteConverter cliConv = null;
	IClienteRepository iCR = null;
	//De entidad a modelo
	public FacturaModel entityToModel(Factura factura) {
		//revisar el empleado que se trae por legajo
		
		// creo un ClienteModel y lo traigo del repositorio con el nro de cliente de la factura.
		ClienteModel cliente = cliConv.entityToModel(iCR.findByNroCliente(factura.getCliente().getNroCliente()));
		return new FacturaModel(factura.getIdFactura(), cliente, factura.getChango().getIdChango(), factura.getFechaFactura(), factura.getCosteTotal(),
				factura.getEmpleado().getLegajo());
	}
	
	//De modelo a entidad
	public Factura modelToEntity(FacturaModel facturaModel) {
		
		return new Factura(Cliente cliente, Chango chango, LocalDate fechaFactura, double costeTotal, Empleado empleado, Local local);
	}
	
}
