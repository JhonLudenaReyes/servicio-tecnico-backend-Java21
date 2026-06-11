package com.webservices.serviciotecnico.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "personas")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona")
	private Integer idPersona;
	
	@Column(name = "id_ciudad")
	private Integer idCiudad;
	
	private String nombres;
	
	private String apellidos;
	
	private String cedula;
	
	private String ruc;
	
	private String direccion;
	
	private String celular;
	
	private String email;
	
	private String telefono;
	
	private String telefono_adicional;
	
	private String estado = "A";
	
	@ManyToOne
	@JoinColumn(name = "id_ciudad", insertable = false, updatable = false)
	private Ciudad ciudad;

}
