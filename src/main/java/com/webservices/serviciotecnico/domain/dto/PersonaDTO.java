package com.webservices.serviciotecnico.domain.dto;

import lombok.Data;

@Data
public class PersonaDTO {
    private Integer idPersona;
    private String nombres;
    private String apellidos;
    private String email;
}
