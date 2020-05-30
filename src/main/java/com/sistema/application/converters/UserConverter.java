package com.sistema.application.converters;

import com.sistema.application.entities.Empleado;
import com.sistema.application.helpers.UtilHelper;
import com.sistema.application.repositories.IEmpleadoRepository;
import com.sistema.application.repositories.IUserRepository;
import com.sistema.application.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter {
	@Autowired
	@Qualifier("empleadoRepository")
	private static IEmpleadoRepository empleadoRepository;
	@Autowired
	@Qualifier("userRepository")
	private static IUserRepository userRepository;
	
	public UserDto entityToDto(com.sistema.application.entities.User user) {
		Empleado empleado = user.getEmpleado();
		String nombreCompleto = empleado.getNombre() + " " + empleado.getApellido();
		return new UserDto(user.getId(), nombreCompleto, user.getUsername(), user.getEmail(), empleado.getLegajo(),
						   empleado.getLocal().getIdLocal());
	}

	public com.sistema.application.entities.User dtoToEntity(UserDto userDto) {
		Empleado empleado = empleadoRepository.findByLegajo(userDto.getLegajo());
		return new com.sistema.application.entities.User(userDto.getIdUser(), empleado, userDto.getEmailUser(), userDto.getUsername());
	}

	/**
	* Método para obtener un objeto DTO dado un User
	* @param user Del tipo org.springframework.security.core.userdetails.User
	* @return UserDto
	*/
	public UserDto userDetailsToDto(User user) {
		UserDto userDto = entityToDto(userRepository.findByUsernameAndFetchUserRolesEagerly(user.getUsername()));
		boolean isGerente = user.getAuthorities().contains(new SimpleGrantedAuthority(UtilHelper.ROLE_GERENTE));
		userDto.setTipoGerente(isGerente);
		return userDto;
	}
}
