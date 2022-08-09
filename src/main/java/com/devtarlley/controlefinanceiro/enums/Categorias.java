package com.devtarlley.controlefinanceiro.enums;

public enum Categorias {
    ALIMENTACAO(0,"Gastos com alimentação"),
    SAUDE(1,"Gastos com saúde"),
    MORADIA(2,"Gastos com moradia"),
    TRANSPORTE(3,"Gastos com transportes"),
    EDUCACAO(4,"Gastos com Educacao"),
    LAZER(5,"Gastos com lazer"),
    IMPREVISTOS(6,"Gastos com imprevistos"),
    OUTRAS(7,"Gastos sem categorias");

    Categorias(Integer id,String descricao) {
    }

}
