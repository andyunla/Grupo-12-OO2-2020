package com.sistema.application.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sistema.application.entities.Factura;
import com.sistema.application.models.ChangoModel;
import com.sistema.application.models.ClienteModel;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.FacturaModel;
import com.sistema.application.models.LocalModel;

@Component("facturaConverter")
public class FacturaConverter {
	@Autowired
	@Qualifier("clienteConverter")
	private  ClienteConverter clienteConverter;
	@Autowired
	@Qualifier("empleadoConverter")
	private  EmpleadoConverter empleadoConverter;
	@Autowired
	@Qualifier("changoConverter")
	private  ChangoConverter changoConverter;
	
	@Autowired
	@Qualifier("localConverter")

	private  LocalConverter localConverter;
	public FacturaModel entityToModel(Factura factura) {
		ClienteModel cli = clienteConverter.entityToModel(factura.getCliente());
		ChangoModel chango =changoConverter.entityToModel(factura.getChango());
		EmpleadoModel empleado = empleadoConverter.entityToModel(factura.getEmpleado());
		LocalModel local = localConverter.entityToModel(factura.getLocal());
		return new FacturaModel(factura.getIdFactura(),cli ,chango, factura.getFechaHoraFactura(), factura.getCosteTotal()
				,empleado,local  );
	}
	
	public Factura modelToEntity(FacturaModel facturaModel) {
		//return new Factura(clienteConverter.modelToEntity(facturaModel.getCliente()), changoConverter.modelToEntity(facturaModel.getChango()), facturaModel.getFechaFactura(), facturaModel.getCosteTotal(), 
		//				   clienteConverter.modelToEntity(facturaModel.getEmpleado(), clienteConverter.modelToEntity(facturaModel.getLocal())));
		return new Factura(facturaModel.getIdFactura(),clienteConverter.modelToEntity(facturaModel.getCliente()),
				changoConverter.modelToEntity(facturaModel.getChango()), facturaModel.getFechaHoraFactura(), facturaModel.getCosteTotal(),
				empleadoConverter.modelToEntity(facturaModel.getEmpleado()), localConverter.modelToEntity(facturaModel.getLocal()));
	}
	
}
