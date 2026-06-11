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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer idRol;
	
	@NotNull(message = "No debe enviar datos nulos")
	@Size(min = 5, message = "Debe ingresar un mÃ­nimo de 5 carÃ¡teres")
	@Size(max = 20, message = "Debe ingresar un mÃ¡ximo de 20 carÃ¡teres")
	@Column(length = 20, nullable = false)
	private String rol;
	
	@Column(columnDefinition = "varchar(1) not null default 'A'")
	private String estado = "A";
	
	@ManyToMany
	@JoinTable(name= "roles_permisos", joinColumns = @JoinColumn(name="id_rol"), inverseJoinColumns = @JoinColumn(name="id_permiso"))
	private List<Permiso> permisos;

}
