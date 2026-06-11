package com.webservices.serviciotecnico.domain.repository;

import java.util.List;
import java.util.Optional;

import com.webservices.serviciotecnico.persistence.model.Ciudad;

public interface CiudadRepository {

	Optional<List<Ciudad>> getCiudades(String estado);
	Optional<Ciudad> getCiudad(int idCiudad);
	Ciudad save(Ciudad ciudad);
	void delete(int idCiudad);
}
