package com.sistema.application.controllers.api.v1;

import java.util.ArrayList;
import java.util.List;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.models.dto.EmpleadoDto;
import com.sistema.application.models.dto.LocalDistanciaDto;
import com.sistema.application.services.IEmpleadoService;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("api/v1/local")
public class LocalRestController {
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
    @Qualifier("productoService")
    private IProductoService productoService;
    
    
    // Faltaría implementar en caso de errores
    @GetMapping("distancia/{idLocal1}/{idLocal2}")
    ResponseEntity <Double> distancia(@PathVariable("idLocal1") long idLocal1,
                                      @PathVariable("idLocal2") long idLocal2) {
        LocalModel local1 = localService.findByIdLocal(idLocal1);
        LocalModel local2 = localService.findByIdLocal(idLocal2);
        double distancia = local1.calcularDistancia(local2);
        return new ResponseEntity <Double>(distancia, HttpStatus.OK);
    }
    
    @GetMapping("empleados/{idLocal}")
    ResponseEntity <List<EmpleadoDto>> empleados(@PathVariable("idLocal") long idLocal) {
        List<EmpleadoModel> models = empleadoService.findByIdLocal(idLocal);
        List<EmpleadoDto> empleados = new ArrayList<EmpleadoDto>();
        for(EmpleadoModel model: models) {
            EmpleadoDto empleado = empleadoConverter.modelToDto(model);
            empleados.add(empleado);
        }
        return new ResponseEntity<List<EmpleadoDto>>(empleados, HttpStatus.OK);
    }

    // API REST que retorna los 3 locales más cercano dado el 
    // producto elegido y su cantidad
    @GetMapping("distancia/{idLocal}/{idProducto/{cantidad}/")
    ResponseEntity <List<LocalDistanciaDto>> localesMasCercanos(@PathVariable("idLocal") long idLocal,
                                                                @PathVariable("idProducto") long idProducto,
                                                                @PathVariable("cantidad") int cantidad) {
        LocalModel local = localService.findByIdLocal(idLocal);
        ProductoModel producto = productoService.findByIdProducto(idProducto);
        List<LocalModel> listaLocales = local.localesCercanos(producto, cantidad);
        List<LocalDistanciaDto> localesCercanos = new ArrayList<LocalDistanciaDto>();
        for(LocalModel model: listaLocales) {
            LocalDistanciaDto dto = new LocalDistanciaDto(model.getIdLocal(), model.getNombreLocal(), local.calcularDistancia(model),
                                    model.getDireccion(), model.getTelefono());
            localesCercanos.add(dto);
        }
        return new ResponseEntity<List<LocalDistanciaDto>>(localesCercanos, HttpStatus.OK);
    }
}
