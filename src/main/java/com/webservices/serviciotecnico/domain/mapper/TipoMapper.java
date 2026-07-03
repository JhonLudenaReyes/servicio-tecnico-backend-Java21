package com.webservices.serviciotecnico.domain.mapper;

import com.webservices.serviciotecnico.domain.dto.TipoDTO;
import com.webservices.serviciotecnico.persistence.model.Tipo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoMapper {

    TipoDTO toDTO(Tipo tipo);
    Tipo toEntity(TipoDTO tipoDTO);
    List<TipoDTO> toDTOList(List<Tipo> tipos);
}
