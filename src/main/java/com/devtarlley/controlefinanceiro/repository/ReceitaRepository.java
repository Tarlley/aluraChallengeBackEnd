package com.devtarlley.controlefinanceiro.repository;

import com.devtarlley.controlefinanceiro.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Month;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
    @Query(value = "SELECT * FROM RECEITAS WHERE MONTH(DATA) = ?1",nativeQuery = true)
    List<Receita> buscarReceitaPorMes(Integer month);
}