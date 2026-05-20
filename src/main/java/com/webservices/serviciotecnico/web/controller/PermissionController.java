package com.webservices.serviciotecnico.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.serviciotecnico.domain.service.PermisoService;
import com.webservices.serviciotecnico.persistence.model.Permiso;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

	private final PermisoService permisoService;

	public PermissionController(PermisoService permisoService) {
		this.permisoService = permisoService;
	}

	@GetMapping("/all-permisos")
	public ResponseEntity<List<Permiso>> findPermisos() {
		return permisoService.findPermisos().map(permisos -> new ResponseEntity<>(permisos, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/{idPermiso}")
	public ResponseEntity<Permiso> getPermiso(@PathVariable("idPermiso") int idPermiso) {
		return permisoService.getPermiso(idPermiso).map(permiso -> new ResponseEntity<>(permiso, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save-permisos")
	public ResponseEntity<Permiso> save(@RequestBody Permiso permiso) {
		return new ResponseEntity<>(permisoService.save(permiso), HttpStatus.CREATED);
	}

	@PutMapping("/update-permiso")
	public ResponseEntity<Permiso> updatePermiso(@RequestBody Permiso permiso) {
		Optional<Permiso> optionalPermiso = permisoService.getPermiso(permiso.getIdPermiso());
		if (optionalPermiso.isPresent()) {
			return new ResponseEntity<>(permisoService.save(permiso), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/delete-permiso-by-id/{idPermiso}")
	public ResponseEntity<Permiso> deletePermisoById(@PathVariable("idPermiso") int idPermiso) {
		Optional<Permiso> optionalPermiso = permisoService.getPermiso(idPermiso);
		if (optionalPermiso.isPresent()) {
			Permiso updatePermiso = optionalPermiso.get();
			updatePermiso.setEstado("I");
			return new ResponseEntity<>(permisoService.save(updatePermiso), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

