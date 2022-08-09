package com.devtarlley.controlefinanceiro.model;

import com.devtarlley.controlefinanceiro.enums.Categorias;
import lombok.Data;

import java.time.Month;

@Data
public class ResumoCategoria {

    private Categorias categorias;

    private Double valor;

    public ResumoCategoria(Double valor, Categorias categoria) {
        this.valor = valor;
        this.categorias = categoria;
    }
}
