package com.sistema.application.converters;

import com.sistema.application.entities.Empleado;
import com.sistema.application.entities.User;
import com.sistema.application.repositories.IEmpleadoRepository;
import com.sistema.application.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter {
	@Autowired
	@Qualifier("empleadoRepository")
	private static IEmpleadoRepository empleadoRepository;
	
	public UserDto entityToDto(User user) {
		Empleado empleado = user.getEmpleado();
		String nombreCompleto = empleado.getNombre() + " " + empleado.getApellido();
		return new UserDto(user.getId(), nombreCompleto, user.getUsername(), user.getEmail(), empleado.getLegajo(),
						   empleado.getLocal().getIdLocal());
	}

	public User dtoToEntity(UserDto userDto) {
		Empleado empleado = empleadoRepository.findByLegajo(userDto.getLegajo());
		return new User(userDto.getIdUser(), empleado, userDto.getEmailUser(), userDto.getUsername());
	}
}
