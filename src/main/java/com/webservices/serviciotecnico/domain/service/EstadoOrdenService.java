package com.webservices.serviciotecnico.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.webservices.serviciotecnico.domain.repository.EstadoOrdenRepository;
import com.webservices.serviciotecnico.persistence.model.EstadoOrden;

@Service
public class EstadoOrdenService {

    private final EstadoOrdenRepository estadoOrdenRepository;

    public EstadoOrdenService(EstadoOrdenRepository estadoOrdenRepository) {
        this.estadoOrdenRepository = estadoOrdenRepository;
    }

    public Optional<List<EstadoOrden>> getAllActive() {
        return estadoOrdenRepository.getAll("A");
    }

    public Optional<EstadoOrden> getEstadoOrden(int idEstadoOrden) {
        return estadoOrdenRepository.getEstadoOrden(idEstadoOrden);
    }

    public EstadoOrden save(EstadoOrden estadoOrden) {
        return estadoOrdenRepository.save(estadoOrden);
    }

    public Optional<EstadoOrden> update(int idEstadoOrden, EstadoOrden estadoOrden) {
        return estadoOrdenRepository.getEstadoOrden(idEstadoOrden).map(existingEstado -> {
            existingEstado.setEstadoOrden(estadoOrden.getEstadoOrden());
            existingEstado.setDescripcion(estadoOrden.getDescripcion());
            existingEstado.setEstado(estadoOrden.getEstado());
            return estadoOrdenRepository.save(existingEstado);
        });
    }

    public boolean delete(int idEstadoOrden) {
        return estadoOrdenRepository.getEstadoOrden(idEstadoOrden).map(estado -> {
            estado.setEstado("I");
            estadoOrdenRepository.save(estado);
            return true;
        }).orElse(false);
    }
}
