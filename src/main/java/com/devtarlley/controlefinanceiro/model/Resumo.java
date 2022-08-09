package com.devtarlley.controlefinanceiro.model;

import lombok.Data;

import java.util.List;

@Data
public class Resumo {
    private Double valorTotalReceitas;

    private Double valorTotalDespesas;

    private Double SaldoFinal;

    private List<ResumoCategoria> resumoCategorias;
}
