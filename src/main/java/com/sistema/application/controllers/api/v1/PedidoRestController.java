package com.sistema.application.controllers.api.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.dto.EmpleadoDto;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.PedidoStockModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.IPedidoStockService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("api/v1/pedido")
public class PedidoRestController {
@Autowired
    @Qualifier("empleadoConverter")
    private EmpleadoConverter empleadoConverter;
    @Autowired
    @Qualifier("localService")
    private ILocalService localService;
    @Autowired
    @Qualifier("empleadoService")
    private IEmpleadoService empleadoService;
    @Autowired
    @Qualifier("pedidoStockService")
    private IPedidoStockService pedidoStockService;
    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;
    
    /**
	* Método que solicita un pedido nuevo, donde un empleado realiza el pedido de un pruducto a otro local.
	* El empleado oferente se selecciona al azar.
	* @param idEmpleado de tipo long. También podremos obtener el local donde trabaja
	* @param idLocal2 de tipo long. Representa el local que posee los productos solicitados.
	* @param idProducto de tipo long.
	* @param cantidad de tipo int.
	* @return boolean
    */
    @RequestMapping(value="solicitar/{legajo}/{idLocal2}/{idProducto}/{cantidad}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Boolean solicitar(@PathVariable("legajo") long legajo, @PathVariable("idLocal2") long idLocal2,
							 @PathVariable("idProducto") long idProducto, @PathVariable("cantidad") int cantidad) 
	{
        ProductoModel producto = productoService.findByIdProducto(idProducto);
        boolean aceptado = false;
        EmpleadoModel solicitante = empleadoService.findByLegajo(legajo);
        // Tratamos de buscar aleatoriamente a cualquier empleado del otro local
        List<EmpleadoModel> listaEmpleados = empleadoService.findByIdLocal(idLocal2);
        Random rand = new Random();
        int randIndex = rand.nextInt(listaEmpleados.size());
        EmpleadoModel oferente = listaEmpleados.get(randIndex);
        PedidoStockModel pedido = pedidoStockService.insertOrUpdate(new PedidoStockModel(producto, cantidad, aceptado, solicitante, oferente));
        return pedido != null; // Para verificar si se creó el pedido
	}
}
