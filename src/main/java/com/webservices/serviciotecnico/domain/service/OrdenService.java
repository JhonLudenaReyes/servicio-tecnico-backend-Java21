package com.webservices.serviciotecnico.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.webservices.serviciotecnico.domain.repository.OrdenRepository;
import com.webservices.serviciotecnico.persistence.model.Orden;

@Service
public class OrdenService {

    private final OrdenRepository ordenRepository;

    public OrdenService(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    public Optional<List<Orden>> getAllActive() {
        return ordenRepository.getAll("A");
    }

    public Optional<Orden> getOrden(int idOrden) {
        return ordenRepository.getOrden(idOrden);
    }

    public Orden save(Orden orden) {
        if (orden.getFechaRecepcion() == null) {
            orden.setFechaRecepcion(LocalDateTime.now());
        }
        return ordenRepository.save(orden);
    }

    public Optional<Orden> update(int idOrden, Orden orden) {
        return ordenRepository.getOrden(idOrden).map(existingOrden -> {
            existingOrden.setIdPersona(orden.getIdPersona());
            existingOrden.setIdUsuario(orden.getIdUsuario());
            existingOrden.setIdEquipo(orden.getIdEquipo());
            existingOrden.setIdEstadoOrden(orden.getIdEstadoOrden());
            existingOrden.setPosibleProblema(orden.getPosibleProblema());
            existingOrden.setTrabajoRealizar(orden.getTrabajoRealizar());
            existingOrden.setObservaciones(orden.getObservaciones());
            existingOrden.setCondicionFisicaIngreso(orden.getCondicionFisicaIngreso());
            existingOrden.setFechaReparacion(orden.getFechaReparacion());
            existingOrden.setFechaAproximada(orden.getFechaAproximada());
            existingOrden.setFechaEntrega(orden.getFechaEntrega());
            existingOrden.setReporteTecnico(orden.getReporteTecnico());
            existingOrden.setEstado(orden.getEstado());
            return ordenRepository.save(existingOrden);
        });
    }

    public boolean delete(int idOrden) {
        return ordenRepository.getOrden(idOrden).map(orden -> {
            orden.setEstado("I");
            ordenRepository.save(orden);
            return true;
        }).orElse(false);
    }
}
