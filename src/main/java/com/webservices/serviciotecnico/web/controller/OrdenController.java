package com.webservices.serviciotecnico.web.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webservices.serviciotecnico.domain.service.OrdenService;
import com.webservices.serviciotecnico.persistence.model.Orden;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orden>> getAll() {
        return ordenService.getAllActive()
                .map(ordenes -> new ResponseEntity<>(ordenes, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getById(@PathVariable int id) {
        return ordenService.getOrden(id)
                .map(orden -> new ResponseEntity<>(orden, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Orden> save(@RequestBody Orden orden) {
        return new ResponseEntity<>(ordenService.save(orden), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Orden> update(@PathVariable int id, @RequestBody Orden orden) {
        return ordenService.update(id, orden)
                .map(updatedOrden -> new ResponseEntity<>(updatedOrden, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (ordenService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
