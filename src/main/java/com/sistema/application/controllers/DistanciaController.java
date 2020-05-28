package com.sistema.application.controllers;

import com.sistema.application.converters.LocalConverter;
import com.sistema.application.dto.LocalDistanciaDto;
import com.sistema.application.dto.LocalDto;
import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Local;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.models.LocalModel;
import com.sistema.application.models.ProductoModel;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.services.ILocalService;
import com.sistema.application.services.IProductoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("distancia")
public class DistanciaController {
	@Autowired
	@Qualifier("localConverter")
	private LocalConverter localConverter;
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	// Para que el model pueda ejecutar los services debe ser usado como una instancia de componente
	@Autowired
	private LocalModel localModel;

	@GetMapping("")
	public String distancia(Model modelo) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelo.addAttribute("username", user.getUsername());
		boolean isGerente = user.getAuthorities().contains(new SimpleGrantedAuthority(UtilHelper.ROLE_GERENTE));
		modelo.addAttribute("isGerente", isGerente);
		
		List<LocalModel> localesModels = localService.getAllModel();
		List<LocalDto> locales = new ArrayList<LocalDto>();
		for (LocalModel model : localesModels) {
			locales.add(localConverter.modelToDto(model));
		}
		// Obtenemos el local actual donde trabaja el usuario
		LocalModel localActual = this.getLocalGivenUser(user.getUsername());
		modelo.addAttribute("local", localActual);
		modelo.addAttribute("locales", locales);
		modelo.addAttribute("productos", productoService.getAllModel());
		return ViewRouteHelper.DISTANCIA_ROOT;
	}

	@GetMapping("traer/{idLocal}/{idProducto}/{cantidad}")
	public ModelAndView traerLocalesCercanos(@PathVariable("idLocal") long idLocal,
			@PathVariable("idProducto") long idProducto, @PathVariable("cantidad") int cantidad) 
	{
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LISTA_LOCALES_CERCANOS);
		LocalModel local = localService.findByIdLocal(idLocal);
		ProductoModel producto = productoService.findByIdProducto(idProducto);
		localModel.setInstance(local);
		List<LocalModel> listaLocales = localModel.localesCercanos(producto, cantidad);
		List<LocalDistanciaDto> localesCercanos = new ArrayList<LocalDistanciaDto>();
		for (LocalModel model : listaLocales) {
			LocalDistanciaDto dto = new LocalDistanciaDto(model.getIdLocal(), model.getNombreLocal(),
					local.calcularDistancia(model), model.getDireccion(), model.getTelefono(), 
					localModel.calcularStockLocal(model, producto));
			localesCercanos.add(dto);
		}
		mAV.addObject("localesCercanos", localesCercanos);
		return mAV;
	}
	
	/**
	* Método que retorna el local donde trabaja el usuario
	* que está logueado actualmente dado su username
	* @param username Tipo String. Ej: 'empleado1'
	* @return LocalModel
	*/
	private LocalModel getLocalGivenUser(String username) {
		com.sistema.application.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		Local local = user.getEmpleado().getLocal();
		return localConverter.entityToModel(local);
		
	}
}
