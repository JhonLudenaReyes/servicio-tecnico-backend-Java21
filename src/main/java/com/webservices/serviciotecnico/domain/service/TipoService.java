package com.webservices.serviciotecnico.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.webservices.serviciotecnico.domain.repository.TipoRepository;
import com.webservices.serviciotecnico.persistence.model.Tipo;

@Service
@Transactional
public class TipoService {

    private final TipoRepository tipoRepository;

    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public Optional<List<Tipo>> getAllActive() {
        return tipoRepository.getAll("A");
    }

    public Optional<Tipo> getTipo(int idTipo) {
        return tipoRepository.getTipo(idTipo);
    }

    public Tipo save(Tipo tipo) {
        return tipoRepository.save(tipo);
    }

    public Optional<Tipo> update(int idTipo, Tipo tipo) {
        return tipoRepository.getTipo(idTipo).map(existingTipo -> {
            existingTipo.setTipo(tipo.getTipo());
            existingTipo.setEstado(tipo.getEstado());
            return tipoRepository.save(existingTipo);
        });
    }

    public boolean delete(int idTipo) {
        return tipoRepository.getTipo(idTipo).map(tipo -> {
            tipo.setEstado("I");
            tipoRepository.save(tipo);
            return true;
        }).orElse(false);
    }
}
