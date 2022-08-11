package com.devtarlley.controlefinanceiro.service;

import com.devtarlley.controlefinanceiro.model.Despesa;
import com.devtarlley.controlefinanceiro.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.devtarlley.controlefinanceiro.enums.Categorias.OUTRAS;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<Despesa> buscarTodasDespesas(String descricao){
        return (descricao == null)?despesaRepository.findAll():despesaRepository.findByDescricao(descricao);
    }

    public Despesa salvarDespesa(Despesa despesa) {
        List<Despesa> despesaExistente = despesaRepository.buscarDespesaPorAnoEMes(despesa.getData().getYear(),despesa.getData().getMonth().getValue(),despesa.getDescricao());
        if (!despesaExistente.isEmpty()){
            throw new ResponseStatusException(HttpStatus.FOUND);
        }

            if (despesa.getCategoria() == null){
                despesa.setCategoria(OUTRAS);
            }

            return despesaRepository.saveAndFlush(despesa);

    }

    public Despesa buscarDespesaPorId(Integer id) {
        return despesaRepository.findById(id).orElse(null);
    }

    public Despesa atualizarDespesa(Integer id, Despesa despesa) {
            List<Despesa> despesaExistente = despesaRepository.buscarDespesaPorAnoEMes(despesa.getData().getYear(),despesa.getData().getMonth().getValue(),despesa.getDescricao());
            if (!despesaExistente.isEmpty()){
                throw new ResponseStatusException(HttpStatus.FOUND);
            }
            despesa.setId(id);

        despesaRepository.saveAndFlush(despesa);
        return despesa;
    }

    public void deletarDespesa(Integer id) {
        despesaRepository.deleteById(id);
    }

    public List<Despesa> buscarReceitasPorAnoEMes(Integer ano, Integer mes) {
        return despesaRepository.buscarReceitasPorAnoEMes(ano, mes);
    }

    public Double buscarValorDespesaPorAnoEMes(Integer ano,Integer mes){
        return despesaRepository.buscarValorReceitaPorAnoEMes(ano,mes);
    }

    public List<Despesa> buscarResumoPorCategoria(Integer ano, Integer mes) {
        return despesaRepository.buscarResumoPorCategoria(ano,mes);
    }
}
