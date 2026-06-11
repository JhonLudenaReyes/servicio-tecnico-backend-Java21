package com.webservices.serviciotecnico.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webservices.serviciotecnico.domain.repository.UsuarioRepository;
import com.webservices.serviciotecnico.persistence.model.Usuario;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Usuario save(Usuario usuario) {
		usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
		return usuarioRepository.save(usuario);
	}

	public Optional<Usuario> getByUsuario(String usuario) {
		return usuarioRepository.getByUsuario(usuario);
	}
	
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	public Optional<Usuario> update(Integer id, Usuario usuario) {
		return usuarioRepository.findById(id).map(existingUser -> {
			// Solo actualizamos las propiedades solicitadas
			existingUser.setUsuario(usuario.getUsuario());
			
			// Si la contraseÃ±a viene en la peticiÃ³n, se encripta de nuevo
			if (usuario.getContrasenia() != null && !usuario.getContrasenia().isEmpty()) {
				existingUser.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
			}
			
			return usuarioRepository.save(existingUser);
		});
	}

	public Optional<Usuario> delete(Integer id) {
		return usuarioRepository.findById(id).map(existingUser -> {
			existingUser.setEstado("I");
			return usuarioRepository.save(existingUser);
		});
	}

	public Optional<Usuario> getUsuarioLogin(String usuario, String contrasenia){
		return usuarioRepository.getUsuarioLogin(usuario, contrasenia);
	}

}
