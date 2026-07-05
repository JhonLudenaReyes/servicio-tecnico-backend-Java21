package com.webservices.serviciotecnico.domain.repository;


import com.webservices.serviciotecnico.persistence.model.Persona;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Optional<List<Persona>> getClients();
}
