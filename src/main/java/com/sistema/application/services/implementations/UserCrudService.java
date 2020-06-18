package com.sistema.application.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.application.exception.UsernameOrIdNotFound;
import com.sistema.application.dto.ChangePasswordForm;
import com.sistema.application.entities.User;
import com.sistema.application.services.IUserCrudService;

@Service
public class UserCrudService implements IUserCrudService {

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) throws UsernameOrIdNotFound {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User changePassword(ChangePasswordForm form) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
