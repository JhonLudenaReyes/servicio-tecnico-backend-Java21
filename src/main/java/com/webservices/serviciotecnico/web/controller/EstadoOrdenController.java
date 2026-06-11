package com.webservices.serviciotecnico.web.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webservices.serviciotecnico.domain.service.EstadoOrdenService;
import com.webservices.serviciotecnico.persistence.model.EstadoOrden;

@RestController
@RequestMapping("/estados-orden")
public class EstadoOrdenController {

    private final EstadoOrdenService estadoOrdenService;

    public EstadoOrdenController(EstadoOrdenService estadoOrdenService) {
        this.estadoOrdenService = estadoOrdenService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EstadoOrden>> getAll() {
        return estadoOrdenService.getAllActive()
                .map(estados -> new ResponseEntity<>(estados, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoOrden> getById(@PathVariable int id) {
        return estadoOrdenService.getEstadoOrden(id)
                .map(estado -> new ResponseEntity<>(estado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<EstadoOrden> save(@RequestBody EstadoOrden estadoOrden) {
        return new ResponseEntity<>(estadoOrdenService.save(estadoOrden), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EstadoOrden> update(@PathVariable int id, @RequestBody EstadoOrden estadoOrden) {
        return estadoOrdenService.update(id, estadoOrden)
                .map(updatedEstado -> new ResponseEntity<>(updatedEstado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (estadoOrdenService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
