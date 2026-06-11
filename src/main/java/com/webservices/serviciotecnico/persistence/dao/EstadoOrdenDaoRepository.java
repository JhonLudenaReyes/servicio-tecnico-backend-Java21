package com.webservices.serviciotecnico.persistence.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.webservices.serviciotecnico.persistence.model.EstadoOrden;

public interface EstadoOrdenDaoRepository extends JpaRepository<EstadoOrden, Integer> {
    Optional<List<EstadoOrden>> findByEstado(String estado);
}
