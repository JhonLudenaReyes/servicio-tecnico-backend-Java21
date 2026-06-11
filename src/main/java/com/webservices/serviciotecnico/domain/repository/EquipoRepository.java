package com.webservices.serviciotecnico.domain.repository;

import java.util.List;
import java.util.Optional;
import com.webservices.serviciotecnico.persistence.model.Equipo;

public interface EquipoRepository {
    Optional<List<Equipo>> getAll(String estado);
    Optional<Equipo> getEquipo(int idEquipo);
    Equipo save(Equipo equipo);
    void delete(int idEquipo);
}
