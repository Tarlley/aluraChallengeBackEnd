package com.devtarlley.controlefinanceiro.repository;

import com.devtarlley.controlefinanceiro.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Month;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
    @Query(value = "SELECT * FROM RECEITAS where year(DATA) = ?1 and month(DATA) = ?2",nativeQuery = true)
    List<Receita> buscarReceitaPorAnoEMes(Integer ano,Integer mes);

    List<Receita> findByDescricao(String descricao);

    @Query(value = "SELECT * FROM RECEITAS WHERE MONTH(DATA) = ?2 AND year(DATA) = ?1",nativeQuery = true)
    List<Receita> buscarReceitasPorAnoEMes(Integer ano,Integer mes);

    @Query(value = "select IFNULL(sum(valor),0) from receitas where year(DATA) = ?1 and month(DATA) = ?2",nativeQuery = true)
    Double buscarValorReceitaPorAnoEMes(Integer ano,Integer mes);
}