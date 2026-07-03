package com.webservices.serviciotecnico.web.controller;

import java.util.List;

import com.webservices.serviciotecnico.domain.dto.TipoDTO;
import com.webservices.serviciotecnico.domain.mapper.TipoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webservices.serviciotecnico.domain.service.TipoService;
import com.webservices.serviciotecnico.persistence.model.Tipo;

@RestController
@RequestMapping("/tipos")
public class TipoController {

    private final TipoService tipoService;
    private final TipoMapper tipoMapper;

    public TipoController(TipoService tipoService, TipoMapper tipoMapper) {
        this.tipoService = tipoService;
        this.tipoMapper = tipoMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoDTO>> getAll() {
        return tipoService.getAllActive()
                .map(tipos -> new ResponseEntity<>(tipoMapper.toDTOList(tipos), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDTO> getById(@PathVariable int id) {
        return tipoService.getTipo(id)
                .map(tipo -> new ResponseEntity<>(tipoMapper.toDTO(tipo), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<TipoDTO> save(@RequestBody TipoDTO tipoDTO) {
        return new ResponseEntity<>(tipoMapper.toDTO(tipoService.save(tipoMapper.toEntity(tipoDTO))), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TipoDTO> update(@PathVariable int id, @RequestBody Tipo tipo) {
        return tipoService.update(id, tipo)
                .map(updatedTipo -> new ResponseEntity<>(tipoMapper.toDTO(updatedTipo), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (tipoService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
