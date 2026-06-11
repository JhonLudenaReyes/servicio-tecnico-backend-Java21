package com.webservices.serviciotecnico.persistence.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "id_persona")
	private Integer idPersona;

	@NotNull(message = "No debe enviar datos nulos")
	@NotBlank(message = "Debe ingresar un nombre sin dejar espacios en blanco")
	@Size(min = 5, message = "Debe ingresar un mÃ­nimo de 5 carÃ¡teres")
	@Size(max = 10, message = "Debe ingresar un mÃ­nimo de 10 carÃ¡teres")
	@Column(length = 10, nullable = false)
	private String usuario;

	@Column(length = 255, nullable = false)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,255}$", 
	         message = "La contraseÃ±a debe tener al menos un nÃºmero, una mayÃºscula, una minÃºscula, un carÃ¡cter especial, y tener entre 8 y 255 caracteres.")
	private String contrasenia;

	@Column(columnDefinition = "varchar(1) not null default 'A'")
	private String estado = "A";

	@ManyToOne
	@JoinColumn(name = "id_persona", insertable = false, updatable = false)
	private Persona persona;

	@ManyToMany
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
	private List<Rol> roles;

}
