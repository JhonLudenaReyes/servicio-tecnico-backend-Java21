package com.webservices.serviciotecnico.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webservices.serviciotecnico.domain.repository.CiudadRepository;
import com.webservices.serviciotecnico.persistence.model.Ciudad;

@Service
public class CiudadService {

	private final CiudadRepository ciudadRepository;

	public CiudadService(CiudadRepository ciudadRepository) {
		this.ciudadRepository = ciudadRepository;
	}
	
	public Optional<List<Ciudad>> getAllActive(){
		return ciudadRepository.getCiudades("A");
	}

	public Optional<Ciudad> getCiudad(int idCiudad) {
		return ciudadRepository.getCiudad(idCiudad);
	}

	public Ciudad save(Ciudad ciudad) {
		return ciudadRepository.save(ciudad);
	}

	public Optional<Ciudad> update(int idCiudad, Ciudad ciudad) {
		return ciudadRepository.getCiudad(idCiudad).map(existingCiudad -> {
			existingCiudad.setCiudad(ciudad.getCiudad());
			existingCiudad.setEstado(ciudad.getEstado());
			return ciudadRepository.save(existingCiudad);
		});
	}

	public boolean delete(int idCiudad) {
		return ciudadRepository.getCiudad(idCiudad).map(ciudad -> {
			ciudad.setEstado("I");
			ciudadRepository.save(ciudad);
			return true;
		}).orElse(false);
	}
}
