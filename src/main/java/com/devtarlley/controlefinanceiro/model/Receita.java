package com.devtarlley.controlefinanceiro.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "receitas")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DESCRICAO", nullable = false)
    @NotBlank
    private String descricao;

    @Column(name = "VALOR", nullable = false)
    @NotNull
    private Double valor;

    @Column(name = "DATA", nullable = false)
    @NotNull
    private LocalDate data;

}