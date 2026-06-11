package com.webservices.serviciotecnico.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.webservices.serviciotecnico.domain.repository.TipoRepository;
import com.webservices.serviciotecnico.persistence.dao.TipoDaoRepository;
import com.webservices.serviciotecnico.persistence.model.Tipo;

@Repository
public class TipoRepositoryImpl implements TipoRepository {

    private final TipoDaoRepository tipoDaoRepository;

    public TipoRepositoryImpl(TipoDaoRepository tipoDaoRepository) {
        this.tipoDaoRepository = tipoDaoRepository;
    }

    @Override
    public Optional<List<Tipo>> getAll(String estado) {
        return tipoDaoRepository.findByEstado(estado);
    }

    @Override
    public Optional<Tipo> getTipo(int idTipo) {
        return tipoDaoRepository.findById(idTipo);
    }

    @Override
    public Tipo save(Tipo tipo) {
        return tipoDaoRepository.save(tipo);
    }

    @Override
    public void delete(int idTipo) {
        tipoDaoRepository.deleteById(idTipo);
    }
}
