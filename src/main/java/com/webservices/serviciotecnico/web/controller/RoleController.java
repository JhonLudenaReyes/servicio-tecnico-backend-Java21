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

import com.webservices.serviciotecnico.domain.dto.RolSelectDTO;
import com.webservices.serviciotecnico.domain.mapper.PermissionMapper;
import com.webservices.serviciotecnico.domain.mapper.RolSelectMapper;
import com.webservices.serviciotecnico.domain.service.RolService;
import com.webservices.serviciotecnico.persistence.dtos.RolSelect;
import com.webservices.serviciotecnico.persistence.model.Rol;

@RestController
@RequestMapping("/roles")
public class RoleController {

	private final RolService rolService;
	private final RolSelectMapper rolSelectMapper;
	private final PermissionMapper permissionMapper;

	public RoleController(RolService rolService, RolSelectMapper rolSelectMapper, PermissionMapper permissionMapper) {
		this.rolService = rolService;
		this.rolSelectMapper = rolSelectMapper;
		this.permissionMapper = permissionMapper;
	}
	
	@GetMapping("/permissions/{roleId}")
	public ResponseEntity<?> getPermissions(@PathVariable("roleId") Integer roleId) {
		Optional<Rol> role = rolService.getRole(roleId);
		if(role.isPresent()) {
			return new ResponseEntity<>(permissionMapper.toDTOList(role.get().getPermisos()), HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping("/role-permits/{roleId}")
	public ResponseEntity<?> getRole(@PathVariable("roleId") int roleId) {
		Optional<Rol> role = rolService.getRole(roleId);
		if(role.isPresent()) {
			return new ResponseEntity<>(role, HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/role-select/{roleId}")
	public ResponseEntity<?> getRoleSelect(@PathVariable("roleId") int roleId) {
		Optional<RolSelect> roleSelect = rolService.getRoleSelect(roleId);
		if(roleSelect.isPresent()) {
			return new ResponseEntity<>(roleSelect, HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		
	}

	@GetMapping("/active-roles")
	public ResponseEntity<List<Rol>> getAllRoles() {
		return rolService.getRolesActive().map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/actives/roles-select")
	public ResponseEntity<List<RolSelectDTO>> getRolSelectDTO() {
		return rolService.getRolesActive()
				.map(roles -> new ResponseEntity<>(rolSelectMapper.toDTOList(roles), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/save")
	public ResponseEntity<Rol> saveRol(@RequestBody Rol rol) {
		return new ResponseEntity<>(rolService.saveRol(rol), HttpStatus.OK);
	}

	@PutMapping("/update-role")
	public ResponseEntity<Rol> updateRole(@RequestBody Rol rol) {
		Optional<Rol> optionalRole = rolService.getRole(rol.getIdRol());
		if (optionalRole.isPresent()) {
			return new ResponseEntity<>(rolService.saveRol(rol), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/delete-role/{idRole}")
	public ResponseEntity<Rol> deleteRole(@PathVariable("idRole") int idRole) {
		Optional<Rol> optionalRole = rolService.getRole(idRole);
		if (optionalRole.isPresent()) {
			Rol updateRole = optionalRole.get();
			updateRole.setEstado("I");
			return new ResponseEntity<>(rolService.saveRol(updateRole), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Controlador para los metodos de la entidad RolSelect
	@GetMapping("/roles-select")
	public ResponseEntity<List<RolSelect>> getActiveRoles() {
		return rolService.getRolesSelect().map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
