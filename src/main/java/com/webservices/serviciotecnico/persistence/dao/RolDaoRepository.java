package com.webservices.serviciotecnico.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.webservices.serviciotecnico.persistence.model.Rol;
import org.springframework.data.jpa.repository.Query;

public interface RolDaoRepository extends JpaRepository<Rol, Integer> {

	// nativeQuery
	// @Query(value = "select id_rol, rol, estado from roles where estado = 'A'",
	// nativeQuery = true)
	// Optional<List<Rol>> getRoles();

	Optional<List<Rol>> findByEstado(String estado);

	//Trae el identificador de cliente
	@Query("select r.idRol from Rol r where r.rol = 'CLIENTE' and r.estado = 'A'")
	Integer searchRoleId();

}
