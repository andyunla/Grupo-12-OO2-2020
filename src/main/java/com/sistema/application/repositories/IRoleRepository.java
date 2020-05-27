package com.sistema.application.repositories;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistema.application.entities.UserRole;

@Repository("roleRepository")
public interface IRoleRepository extends JpaRepository<UserRole, Serializable> {
	
	public abstract UserRole findByRole(String role);
	
	@Query("SELECT ur FROM UserRole ur where ur.user = (:userId)")
	public abstract Set<UserRole> loadUserRolesGivenUserId(@Param("userId") Long userId);

	@Query("SELECT ur FROM User u JOIN u.userRoles ur WHERE u.username = (:username)")
	public abstract Set<UserRole> loadUserRolesByUsername(@Param("username") String username);
}
