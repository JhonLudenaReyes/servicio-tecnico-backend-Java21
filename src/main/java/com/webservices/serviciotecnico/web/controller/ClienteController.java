package com.webservices.serviciotecnico.web.controller;

import com.webservices.serviciotecnico.domain.service.ClienteService;
import com.webservices.serviciotecnico.persistence.model.Persona;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/listado")
    public ResponseEntity<List<Persona>> getClients(){
        return clienteService.getClients()
                .map(clients -> new ResponseEntity<>(clients, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Persona persona) {
        try {
            Persona clienteGuardado = clienteService.save(persona);
            return new ResponseEntity<>(clienteGuardado, HttpStatus.CREATED);

        } catch (Exception e) {
            // En lugar de romper la aplicación con throw new Exception,
            // retornamos un error 500 estructurado y controlado.
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ha ocurrido un error al registrar al cliente: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Persona persona) {
        // 1. Validar que el ID no venga nulo en el JSON
        if (persona.getIdPersona() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("El ID de la persona es obligatorio para poder actualizar.");
        }

        try {
            Optional<Persona> clientExisting = clienteService.getClientById(persona.getIdPersona());

            // 2. Si existe, actualizamos y retornamos 200 OK
            if (clientExisting.isPresent()) {
                Persona clienteActualizado = clienteService.update(persona);
                return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
            }

            // 3. Si NO existe, retornamos 404 Not Found (Esto soluciona el error de compilación)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El cliente con el ID " + persona.getIdPersona() + " no fue encontrado.");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ha ocurrido un error al actualizar al cliente: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable int clientId){
        if(clienteService.delete(clientId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
