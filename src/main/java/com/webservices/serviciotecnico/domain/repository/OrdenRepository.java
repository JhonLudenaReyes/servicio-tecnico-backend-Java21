package com.webservices.serviciotecnico.domain.repository;

import java.util.List;
import java.util.Optional;
import com.webservices.serviciotecnico.persistence.model.Orden;

public interface OrdenRepository {
    Optional<List<Orden>> getAll(String estado);
    Optional<Orden> getOrden(int idOrden);
    Orden save(Orden orden);
    void delete(int idOrden);
}
