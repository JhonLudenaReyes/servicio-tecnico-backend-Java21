package com.webservices.serviciotecnico.persistence.dao;

import com.webservices.serviciotecnico.persistence.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteDaoRepository extends JpaRepository<Persona, Integer> {

    //Optional<List<Usuario>> findAllByEstadoAndRoles(String estado);

    @Query("SELECT p FROM Usuario u " +
            "JOIN u.persona p " +
            "JOIN u.roles r " +
            "WHERE r.rol = 'CLIENTE' AND p.estado = 'A' AND u.estado = 'A'")
    Optional<List<Persona>> findClientesActivos();
}
