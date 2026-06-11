package com.webservices.serviciotecnico.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.webservices.serviciotecnico.domain.repository.EstadoOrdenRepository;
import com.webservices.serviciotecnico.persistence.dao.EstadoOrdenDaoRepository;
import com.webservices.serviciotecnico.persistence.model.EstadoOrden;

@Repository
public class EstadoOrdenRepositoryImpl implements EstadoOrdenRepository {

    private final EstadoOrdenDaoRepository estadoOrdenDaoRepository;

    public EstadoOrdenRepositoryImpl(EstadoOrdenDaoRepository estadoOrdenDaoRepository) {
        this.estadoOrdenDaoRepository = estadoOrdenDaoRepository;
    }

    @Override
    public Optional<List<EstadoOrden>> getAll(String estado) {
        return estadoOrdenDaoRepository.findByEstado(estado);
    }

    @Override
    public Optional<EstadoOrden> getEstadoOrden(int idEstadoOrden) {
        return estadoOrdenDaoRepository.findById(idEstadoOrden);
    }

    @Override
    public EstadoOrden save(EstadoOrden estadoOrden) {
        return estadoOrdenDaoRepository.save(estadoOrden);
    }

    @Override
    public void delete(int idEstadoOrden) {
        estadoOrdenDaoRepository.deleteById(idEstadoOrden);
    }
}
