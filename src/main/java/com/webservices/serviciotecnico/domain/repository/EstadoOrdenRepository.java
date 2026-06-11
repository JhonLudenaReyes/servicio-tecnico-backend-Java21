package com.webservices.serviciotecnico.domain.repository;

import java.util.List;
import java.util.Optional;
import com.webservices.serviciotecnico.persistence.model.EstadoOrden;

public interface EstadoOrdenRepository {
    Optional<List<EstadoOrden>> getAll(String estado);
    Optional<EstadoOrden> getEstadoOrden(int idEstadoOrden);
    EstadoOrden save(EstadoOrden estadoOrden);
    void delete(int idEstadoOrden);
}
