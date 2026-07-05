package com.webservices.serviciotecnico.domain.service;

import com.webservices.serviciotecnico.domain.repository.ClienteRepository;
import com.webservices.serviciotecnico.persistence.model.Persona;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Optional<List<Persona>> getClients(){
        return clienteRepository.getClients();
    }

}
