package com.webservices.serviciotecnico.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.webservices.serviciotecnico.domain.repository.EquipoRepository;
import com.webservices.serviciotecnico.persistence.dao.EquipoDaoRepository;
import com.webservices.serviciotecnico.persistence.model.Equipo;

@Repository
public class EquipoRepositoryImpl implements EquipoRepository {

    private final EquipoDaoRepository equipoDaoRepository;

    public EquipoRepositoryImpl(EquipoDaoRepository equipoDaoRepository) {
        this.equipoDaoRepository = equipoDaoRepository;
    }

    @Override
    public Optional<List<Equipo>> getAll(String estado) {
        return equipoDaoRepository.findByEstado(estado);
    }

    @Override
    public Optional<Equipo> getEquipo(int idEquipo) {
        return equipoDaoRepository.findById(idEquipo);
    }

    @Override
    public Equipo save(Equipo equipo) {
        return equipoDaoRepository.save(equipo);
    }

    @Override
    public void delete(int idEquipo) {
        equipoDaoRepository.deleteById(idEquipo);
    }
}
