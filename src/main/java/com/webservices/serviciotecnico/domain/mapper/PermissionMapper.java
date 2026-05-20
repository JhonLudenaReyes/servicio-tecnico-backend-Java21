package com.webservices.serviciotecnico.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.webservices.serviciotecnico.domain.dto.PermissionDTO;
import com.webservices.serviciotecnico.persistence.model.Permiso;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
	
	@Mapping(source = "idPermiso", target = "permissionId")
	@Mapping(source = "permiso", target = "permission")
	PermissionDTO toDTO(Permiso permiso);
	
	@Mapping(source = "permissionId", target = "idPermiso")
	@Mapping(source = "permission", target = "permiso")
	Permiso toEntity(PermissionDTO dto);
	
	List<PermissionDTO> toDTOList(List<Permiso> permisos);
	
	List<Permiso> toEntityList(List<PermissionDTO> dto);

}
