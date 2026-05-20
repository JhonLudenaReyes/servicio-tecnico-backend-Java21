package com.webservices.serviciotecnico.domain.mapper;

import java.util.List;
import org.mapstruct.Mapper;

import com.webservices.serviciotecnico.domain.dto.RolSelectDTO;
import com.webservices.serviciotecnico.persistence.model.Rol;

@Mapper(componentModel = "spring")
public interface RolSelectMapper {
	
	RolSelectDTO toDTO(Rol rol);
	
	Rol toEntity(RolSelectDTO dto);
	
	List<RolSelectDTO> toDTOList(List<Rol> roles);
	
	

}
