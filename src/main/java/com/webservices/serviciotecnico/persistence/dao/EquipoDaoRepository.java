package com.webservices.serviciotecnico.persistence.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.webservices.serviciotecnico.persistence.model.Equipo;

public interface EquipoDaoRepository extends JpaRepository<Equipo, Integer> {
    Optional<List<Equipo>> findByEstado(String estado);
}
