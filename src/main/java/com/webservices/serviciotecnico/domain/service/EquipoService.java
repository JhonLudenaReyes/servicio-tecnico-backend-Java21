package com.webservices.serviciotecnico.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.webservices.serviciotecnico.domain.repository.EquipoRepository;
import com.webservices.serviciotecnico.persistence.model.Equipo;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public Optional<List<Equipo>> getAllActive() {
        return equipoRepository.getAll("A");
    }

    public Optional<Equipo> getEquipo(int idEquipo) {
        return equipoRepository.getEquipo(idEquipo);
    }

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Optional<Equipo> update(int idEquipo, Equipo equipo) {
        return equipoRepository.getEquipo(idEquipo).map(existingEquipo -> {
            existingEquipo.setIdTipo(equipo.getIdTipo());
            existingEquipo.setMarca(equipo.getMarca());
            existingEquipo.setModelo(equipo.getModelo());
            existingEquipo.setSerie(equipo.getSerie());
            existingEquipo.setMainboard(equipo.getMainboard());
            existingEquipo.setProcesador(equipo.getProcesador());
            existingEquipo.setMemoria(equipo.getMemoria());
            existingEquipo.setDiscoDuro(equipo.getDiscoDuro());
            existingEquipo.setFuente(equipo.getFuente());
            existingEquipo.setBox(equipo.getBox());
            existingEquipo.setEstado(equipo.getEstado());
            return equipoRepository.save(existingEquipo);
        });
    }

    public boolean delete(int idEquipo) {
        return equipoRepository.getEquipo(idEquipo).map(equipo -> {
            equipo.setEstado("I");
            equipoRepository.save(equipo);
            return true;
        }).orElse(false);
    }
}
