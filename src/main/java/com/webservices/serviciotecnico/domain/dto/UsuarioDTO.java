package com.webservices.serviciotecnico.domain.dto;

import java.util.List;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer idUsuario;
    private String usuario;
    private PersonaDTO persona;
    private List<RolDTO> roles;
}
