package com.webservices.serviciotecnico.web.controller;

import java.util.List;

import com.webservices.serviciotecnico.domain.dto.CiudadDTO;
import com.webservices.serviciotecnico.domain.mapper.CiudadMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webservices.serviciotecnico.domain.service.CiudadService;
import com.webservices.serviciotecnico.persistence.model.Ciudad;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

	private final CiudadService ciudadService;
	private final CiudadMapper ciudadMapper;

	public CiudadController(CiudadService ciudadService, CiudadMapper ciudadMapper) {
		this.ciudadService = ciudadService;
		this.ciudadMapper = ciudadMapper;
	}

	@GetMapping("/all")
	public ResponseEntity<List<CiudadDTO>> getAll(){
		return ciudadService.getAllActive().map(ciudades -> new ResponseEntity<>(ciudadMapper.toDTOList(ciudades), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CiudadDTO> getById(@PathVariable int id) {
		return ciudadService.getCiudad(id)
				.map(ciudad -> new ResponseEntity<>(ciudadMapper.toDTO(ciudad), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save")
	public ResponseEntity<CiudadDTO> save(@RequestBody Ciudad ciudad) {
		return new ResponseEntity<>(ciudadMapper.toDTO(ciudadService.save(ciudad)), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CiudadDTO> update(@PathVariable int id, @RequestBody Ciudad ciudad) {
		return ciudadService.update(id, ciudad)
				.map(updatedCiudad -> new ResponseEntity<>(ciudadMapper.toDTO(updatedCiudad), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		if (ciudadService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
