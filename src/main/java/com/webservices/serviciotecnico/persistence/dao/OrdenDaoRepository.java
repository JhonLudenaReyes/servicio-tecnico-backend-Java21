package com.webservices.serviciotecnico.persistence.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.webservices.serviciotecnico.persistence.model.Orden;

public interface OrdenDaoRepository extends JpaRepository<Orden, Integer> {
    Optional<List<Orden>> findByEstado(String estado);
}
