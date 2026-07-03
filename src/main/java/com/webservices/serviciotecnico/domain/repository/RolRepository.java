package com.webservices.serviciotecnico.domain.repository;

import java.util.List;
import java.util.Optional;

import com.webservices.serviciotecnico.persistence.model.Rol;

public interface RolRepository {
	
	Optional<Rol> getRole(int idRole);
	
	Optional<List<Rol>> getAllRolesActive();
	Rol saveRol(Rol rol);
}
