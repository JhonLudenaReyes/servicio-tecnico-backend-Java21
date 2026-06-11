package com.webservices.serviciotecnico.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.webservices.serviciotecnico.domain.repository.OrdenRepository;
import com.webservices.serviciotecnico.persistence.dao.OrdenDaoRepository;
import com.webservices.serviciotecnico.persistence.model.Orden;

@Repository
public class OrdenRepositoryImpl implements OrdenRepository {

    private final OrdenDaoRepository ordenDaoRepository;

    public OrdenRepositoryImpl(OrdenDaoRepository ordenDaoRepository) {
        this.ordenDaoRepository = ordenDaoRepository;
    }

    @Override
    public Optional<List<Orden>> getAll(String estado) {
        return ordenDaoRepository.findByEstado(estado);
    }

    @Override
    public Optional<Orden> getOrden(int idOrden) {
        return ordenDaoRepository.findById(idOrden);
    }

    @Override
    public Orden save(Orden orden) {
        return ordenDaoRepository.save(orden);
    }

    @Override
    public void delete(int idOrden) {
        ordenDaoRepository.deleteById(idOrden);
    }
}
