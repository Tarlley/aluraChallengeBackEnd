package com.devtarlley.controlefinanceiro.repository;

import com.devtarlley.controlefinanceiro.model.Despesa;
import com.devtarlley.controlefinanceiro.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {
    @Query(value = "SELECT * FROM DESPESAS where year(DATA) = ?1 and month(DATA) = ?2 and descricao = ?3",nativeQuery = true)
    List<Despesa> buscarDespesaPorAnoEMes(Integer ano, Integer mes,String descricao);

    List<Despesa> findByDescricao(String descricao);

    @Query(value = "SELECT * FROM DESPESAS WHERE MONTH(DATA) = ?2 AND year(DATA) = ?1",nativeQuery = true)
    List<Despesa> buscarReceitasPorAnoEMes(Integer ano, Integer mes);

    @Query(value = "select IFNULL(sum(valor),0) from despesas where year(DATA) = ?1 and month(DATA) = ?2",nativeQuery = true)
    Double buscarValorReceitaPorAnoEMes(Integer ano, Integer mes);

    @Query(value = "select IFNULL(sum(valor),0) as valor, categoria,descricao,id,data from despesas where year(DATA) = ?1 and month(DATA) = ?2 group by categoria",nativeQuery = true)
    List<Despesa> buscarResumoPorCategoria(Integer ano,Integer mes);


}