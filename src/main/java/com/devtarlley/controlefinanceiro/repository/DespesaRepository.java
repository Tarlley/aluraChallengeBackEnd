package com.devtarlley.controlefinanceiro.repository;

import com.devtarlley.controlefinanceiro.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {
    @Query(value = "SELECT * FROM DESPESAS WHERE MONTH(DATA) = ?1",nativeQuery = true)
    List<Despesa> buscarDespesaPorMes(Integer month);

    List<Despesa> findByDescricao(String descricao);
}