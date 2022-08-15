package com.devtarlley.controlefinanceiro.service;

import com.devtarlley.controlefinanceiro.model.Resumo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ResumoServiceTest {

    private ResumoService resumoService;
    @Mock
    private DespesaService despesaService;
    @Mock
    private ReceitaService receitaService;

    @BeforeEach
    public void beforeEach(){
            MockitoAnnotations.initMocks(this);
        this.resumoService = new ResumoService(receitaService,despesaService);
        }
    @Test
    public void deveRetornarValorPositivo(){

        Resumo resumo = valores(900.0,700.0);
        assertEquals(200,resumo.getSaldoFinal());
    }

    @Test
    public void deveRetornarValorNegativo(){
        Resumo resumo = valores(700.0, 900.0);
        assertEquals(-200,resumo.getSaldoFinal());
    }

    @Test
    public void deveRetronarZero(){
        Resumo resumo = valores(800.0, 800.0);
        assertEquals(0,resumo.getSaldoFinal());
    }
    private Resumo valores(Double receita,Double despesa){

        Mockito.when(receitaService.buscarValorReceitaPorAnoEMes(2022,5)).thenReturn(receita);
        Mockito.when(despesaService.buscarValorDespesaPorAnoEMes(2022,5)).thenReturn(despesa);
        return resumoService.buscarResumoPorAnoEMes(2022, 5);
    }
}
