package com.webservices.serviciotecnico.web.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webservices.serviciotecnico.domain.service.EquipoService;
import com.webservices.serviciotecnico.persistence.model.Equipo;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Equipo>> getAll() {
        return equipoService.getAllActive()
                .map(equipos -> new ResponseEntity<>(equipos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getById(@PathVariable int id) {
        return equipoService.getEquipo(id)
                .map(equipo -> new ResponseEntity<>(equipo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Equipo> save(@RequestBody Equipo equipo) {
        return new ResponseEntity<>(equipoService.save(equipo), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Equipo> update(@PathVariable int id, @RequestBody Equipo equipo) {
        return equipoService.update(id, equipo)
                .map(updatedEquipo -> new ResponseEntity<>(updatedEquipo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (equipoService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
