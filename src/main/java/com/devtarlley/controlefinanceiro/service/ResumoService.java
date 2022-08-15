package com.devtarlley.controlefinanceiro.service;

import com.devtarlley.controlefinanceiro.model.Despesa;
import com.devtarlley.controlefinanceiro.model.Resumo;
import com.devtarlley.controlefinanceiro.model.ResumoCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumoService {

    private final ReceitaService receitaService;

    private final DespesaService despesaService;

    @Autowired
    public ResumoService(ReceitaService receitaService, DespesaService despesaService) {
        this.receitaService = receitaService;
        this.despesaService = despesaService;
    }

    public Resumo buscarResumoPorAnoEMes(Integer ano, Integer mes) {
        Resumo resumo = new Resumo();
        List<ResumoCategoria> resumoCategorias = new ArrayList<>();

        resumo.setValorTotalReceitas(receitaService.buscarValorReceitaPorAnoEMes(ano,mes));
        resumo.setValorTotalDespesas(despesaService.buscarValorDespesaPorAnoEMes(ano,mes));

        resumo.setSaldoFinal(resumo.getValorTotalReceitas() - resumo.getValorTotalDespesas());
        despesaService.buscarResumoPorCategoria(ano, mes).forEach(
                item -> resumoCategorias.add(new ResumoCategoria(item.getValor(),item.getCategoria())));

        resumo.setResumoCategorias(resumoCategorias);
        return resumo;
    }
}
