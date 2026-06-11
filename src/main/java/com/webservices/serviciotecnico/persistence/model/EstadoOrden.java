package com.webservices.serviciotecnico.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "estados_orden")
public class EstadoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_orden")
    private Integer idEstadoOrden;

    @Column(name = "estado_orden", length = 45, nullable = false)
    private String estadoOrden;

    @Column(length = 150)
    private String descripcion;

    @Column(columnDefinition = "char(1) not null default 'A'")
    private String estado = "A";

}
