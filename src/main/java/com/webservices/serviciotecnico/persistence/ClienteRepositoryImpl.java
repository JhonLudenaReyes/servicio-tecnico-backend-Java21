package com.webservices.serviciotecnico.persistence;

import com.webservices.serviciotecnico.domain.repository.ClienteRepository;
import com.webservices.serviciotecnico.persistence.dao.ClienteDaoRepository;
import com.webservices.serviciotecnico.persistence.model.Persona;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteDaoRepository clienteDaoRepository;

    public ClienteRepositoryImpl(ClienteDaoRepository clienteDaoRepository){
        this.clienteDaoRepository = clienteDaoRepository;
    }

    @Override
    public Optional<List<Persona>> getClients() {
        return clienteDaoRepository.findClientesActivos();
    }
}
