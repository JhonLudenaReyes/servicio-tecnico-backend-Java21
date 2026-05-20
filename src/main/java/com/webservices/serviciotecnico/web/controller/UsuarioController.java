package com.webservices.serviciotecnico.web.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.serviciotecnico.domain.service.UsuarioService;
import com.webservices.serviciotecnico.persistence.model.Usuario;

import org.springframework.security.core.Authentication;

import com.webservices.serviciotecnico.domain.dto.UsuarioDTO;
import com.webservices.serviciotecnico.domain.mapper.UsuarioMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import com.webservices.serviciotecnico.web.security.JwtUtils;
import com.webservices.serviciotecnico.web.security.UserDetailsServiceImpl;
import com.webservices.serviciotecnico.domain.dto.AuthenticationResponse;
import com.webservices.serviciotecnico.domain.dto.LoginCredentials;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final UsuarioMapper usuarioMapper;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsServiceImpl userDetailsService;
	private final JwtUtils jwtUtils;

	public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper,
			AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils) {
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginCredentials loginCredentials)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentials.getUsuario(),
					loginCredentials.getContrasenia()));
		} catch (Exception e) {
			throw new Exception("Usuario y/o contraseña incorrectos", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginCredentials.getUsuario());
		final String jwt = jwtUtils.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/login")
	public ResponseEntity<UsuarioDTO> getUserLogin(@RequestBody LoginCredentials loginCredentials) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentials.getUsuario(),
					loginCredentials.getContrasenia()));
		} catch (Exception e) {
			throw new Exception("Usuario y/o contraseña incorrectos", e);
		}

		return usuarioService.getByUsuario(loginCredentials.getUsuario())
				.map(user -> new ResponseEntity<>(usuarioMapper.toDTO(user), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@PostMapping("/save")
	public ResponseEntity<UsuarioDTO> save(@RequestBody Usuario usuario) {
		Usuario savedUser = usuarioService.save(usuario);
		return new ResponseEntity<>(usuarioMapper.toDTO(savedUser), HttpStatus.CREATED);
	}

	@GetMapping("/me")
	public ResponseEntity<UsuarioDTO> getAuthenticatedUser(Authentication authentication) {
		return usuarioService.getByUsuario(authentication.getName())
				.map(user -> new ResponseEntity<>(usuarioMapper.toDTO(user), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
		return usuarioService.update(id, usuario)
				.map(user -> new ResponseEntity<>(usuarioMapper.toDTO(user), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable Integer id) {
		return usuarioService.delete(id).map(user -> new ResponseEntity<>(usuarioMapper.toDTO(user), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
