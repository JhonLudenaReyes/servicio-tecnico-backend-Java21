package com.webservices.serviciotecnico.persistence.model;

import java.time.LocalDateTime;
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
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Integer idOrden;

    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_equipo", nullable = false)
    private Integer idEquipo;

    @Column(name = "id_estado_orden", nullable = false)
    private Integer idEstadoOrden;

    @Column(name = "fecha_recepcion", nullable = false)
    private LocalDateTime fechaRecepcion;

    @Column(name = "posible_problema", length = 255, nullable = false)
    private String posibleProblema;

    @Column(name = "trabajo_realizar", length = 255, nullable = false)
    private String trabajoRealizar;

    @Column(length = 255, nullable = false)
    private String observaciones;

    @Column(name = "condicion_fisica_ingreso", length = 150)
    private String condicionFisicaIngreso;

    @Column(name = "fecha_reparacion")
    private LocalDateTime fechaReparacion;

    @Column(name = "fecha_aproximada")
    private LocalDateTime fechaAproximada;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    @Column(name = "reporte_tecnico", columnDefinition = "TEXT")
    private String reporteTecnico;

    @Column(columnDefinition = "char(1) not null default 'A'")
    private String estado = "A";

    @ManyToOne
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_equipo", insertable = false, updatable = false)
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "id_estado_orden", insertable = false, updatable = false)
    private EstadoOrden estadoOrden;

}
