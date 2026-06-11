package com.webservices.serviciotecnico.domain.repository;

import java.util.List;
import java.util.Optional;
import com.webservices.serviciotecnico.persistence.model.Tipo;

public interface TipoRepository {
    Optional<List<Tipo>> getAll(String estado);
    Optional<Tipo> getTipo(int idTipo);
    Tipo save(Tipo tipo);
    void delete(int idTipo);
}
