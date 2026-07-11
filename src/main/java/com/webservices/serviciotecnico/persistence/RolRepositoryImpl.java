package com.webservices.serviciotecnico.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webservices.serviciotecnico.domain.repository.RolRepository;
import com.webservices.serviciotecnico.persistence.dao.RolDaoRepository;
import com.webservices.serviciotecnico.persistence.model.Rol;

@Repository
public class RolRepositoryImpl implements RolRepository{
	
	@Autowired
	private RolDaoRepository rolDaoRepository;

	@Override
	public Optional<List<Rol>> getAllRolesActive() {
		// TODO Auto-generated method stub
		
		Optional<List<Rol>> roles = rolDaoRepository.findByEstado("A"); 
		return roles;
	}

	@Override
	public Rol saveRol(Rol rol) {
		// TODO Auto-generated method stub
		return rolDaoRepository.save(rol);
	}

	@Override
	public Optional<Rol> findById(Integer idRol) {
		return rolDaoRepository.findById(idRol);
	}

	@Override
	public Integer SearchRoleId() {
		return rolDaoRepository.searchRoleId();
	}

	@Override
	public Optional<Rol> getRole(int idRole) {
		// TODO Auto-generated method stub
		return rolDaoRepository.findById(idRole);
	}
	
}
