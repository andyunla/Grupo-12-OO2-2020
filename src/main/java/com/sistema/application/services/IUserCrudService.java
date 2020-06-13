package com.sistema.application.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sistema.application.exception.UsernameOrIdNotFound;
import com.sistema.application.dto.ChangePasswordForm;
import com.sistema.application.entities.User;

public interface IUserCrudService {

	public List<User> getAllUsers();

	public User createUser(User user) throws Exception;

	public User getUserById(Long id) throws Exception;
	
	public User updateUser(User user) throws Exception;
	
	public void deleteUser(Long id) throws UsernameOrIdNotFound;
	
	public User changePassword(ChangePasswordForm form) throws Exception;
}
