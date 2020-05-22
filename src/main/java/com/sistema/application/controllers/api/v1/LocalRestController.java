package com.sistema.application.controllers.api.v1;

import java.util.ArrayList;
import java.util.List;

import com.sistema.application.converters.EmpleadoConverter;
import com.sistema.application.models.EmpleadoModel;
import com.sistema.application.models.dto.EmpleadoDto;
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
}
