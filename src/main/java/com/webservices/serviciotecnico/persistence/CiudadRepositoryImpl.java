package com.webservices.serviciotecnico.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.webservices.serviciotecnico.domain.repository.CiudadRepository;
import com.webservices.serviciotecnico.persistence.dao.CiudadDaoRepository;
import com.webservices.serviciotecnico.persistence.model.Ciudad;

@Repository
public class CiudadRepositoryImpl implements CiudadRepository {
	
	private final CiudadDaoRepository ciudadDaoRepository;

	public CiudadRepositoryImpl(CiudadDaoRepository ciudadDaoRepository) {
		this.ciudadDaoRepository = ciudadDaoRepository;
	}

	@Override
	public Optional<List<Ciudad>> getCiudades(String estado) {
		return ciudadDaoRepository.findByEstado(estado);
	}

	@Override
	public Optional<Ciudad> getCiudad(int idCiudad) {
		return ciudadDaoRepository.findById(idCiudad);
	}

	@Override
	public Ciudad save(Ciudad ciudad) {
		return ciudadDaoRepository.save(ciudad);
	}

	@Override
	public void delete(int idCiudad) {
		ciudadDaoRepository.deleteById(idCiudad);
	}

}
