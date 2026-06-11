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
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Integer idEquipo;

    @Column(name = "id_tipo", nullable = false)
    private Integer idTipo;

    @Column(length = 45, nullable = false)
    private String marca;

    @Column(length = 45, nullable = false)
    private String modelo;

    @Column(length = 45, nullable = false)
    private String serie;

    @Column(length = 45)
    private String mainboard;

    @Column(length = 45)
    private String procesador;

    @Column(length = 45)
    private String memoria;

    @Column(name = "disco_duro", length = 45)
    private String discoDuro;

    @Column(length = 45)
    private String fuente;

    @Column(name = "`case`" , length = 45)
    private String box; // 'case' is a reserved word in SQL, mapped to 'box' or use backticks

    @Column(columnDefinition = "char(1) not null default 'A'")
    private String estado = "A";

    @ManyToOne
    @JoinColumn(name = "id_tipo", insertable = false, updatable = false)
    private Tipo tipo;

}
