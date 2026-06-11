package com.webservices.serviciotecnico.persistence.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class RolSelect {
	
	@Id
	@Column(name = "id_rol")
	private Integer idRol;
	
	private String rol;

}
