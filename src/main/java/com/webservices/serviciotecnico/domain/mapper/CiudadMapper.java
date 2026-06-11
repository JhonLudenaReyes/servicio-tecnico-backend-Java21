package com.webservices.serviciotecnico.domain.mapper;


import com.webservices.serviciotecnico.domain.dto.CiudadDTO;
import com.webservices.serviciotecnico.persistence.model.Ciudad;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CiudadMapper {

    CiudadDTO toDTO(Ciudad ciudad);

    List<CiudadDTO> toDTOList(List<Ciudad> ciudades);
}
