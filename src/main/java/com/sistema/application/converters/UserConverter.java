package com.sistema.application.converters;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.Local;
import com.sistema.application.entities.User;
import com.sistema.application.repositories.IEmpleadoRepository;
import com.sistema.application.dto.LocalDto;
import com.sistema.application.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter {
	@Autowired
	@Qualifier("localConverter")
	private static LocalConverter localConverter;
	@Autowired
	@Qualifier("empleadoRepository")
	private static IEmpleadoRepository empleadoRepository;
	
	public UserDto entityToDto(User user) {
		Empleado empleado = user.getEmpleado();
		String nombreCompleto = empleado.getNombre() + " " + empleado.getApellido();
		Local local = empleado.getLocal();
		Long legajoGerente = null;
		if(local.getGerente() != null)
			legajoGerente = local.getGerente().getLegajo();
		LocalDto localDto = new LocalDto(local.getIdLocal(), local.getNombreLocal(), local.getLatitud(), local.getLongitud(),
										 local.getDireccion(), local.getTelefono(), legajoGerente);
		return new UserDto(user.getId(), nombreCompleto, user.getUsername(), user.getEmail(), empleado.getLegajo(), localDto);
	}

	public User dtoToEntity(UserDto userDto) {
		Empleado empleado = empleadoRepository.findByLegajo(userDto.getLegajo());
		return new User(userDto.getIdUser(), empleado, userDto.getEmailUser(), userDto.getUsername());
	}
}
