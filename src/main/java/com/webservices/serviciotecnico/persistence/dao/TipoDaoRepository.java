package com.webservices.serviciotecnico.persistence.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.webservices.serviciotecnico.persistence.model.Tipo;

public interface TipoDaoRepository extends JpaRepository<Tipo, Integer> {
    Optional<List<Tipo>> findByEstado(String estado);
}
