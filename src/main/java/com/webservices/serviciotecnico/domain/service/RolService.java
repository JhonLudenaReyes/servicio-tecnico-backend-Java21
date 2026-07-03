package com.webservices.serviciotecnico.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webservices.serviciotecnico.domain.repository.RolRepository;
import com.webservices.serviciotecnico.persistence.model.Rol;

@Service
@Transactional
public class RolService {

	private final RolRepository rolRepository;

	public RolService(RolRepository rolRepository) {
		this.rolRepository = rolRepository;
	}
	
	public Optional<Rol> getRole(int idRole){
		return rolRepository.getRole(idRole);
	}
	
	public Optional<List<Rol>> getRolesActive(){
		return rolRepository.getAllRolesActive();
	}
	
	public Rol saveRol(Rol rol) {
		return rolRepository.saveRol(rol);
	}

}
