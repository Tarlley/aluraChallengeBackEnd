package com.devtarlley.controlefinanceiro.controller;

import com.devtarlley.controlefinanceiro.model.Despesa;
import com.devtarlley.controlefinanceiro.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public ResponseEntity<?> buscarTodasDespesas(@RequestParam(required = false) String descricao){
        return despesaService.buscarTodasDespesas(descricao);
    }

    @PostMapping
    public ResponseEntity<?> salvarDespesa(@Valid @RequestBody Despesa despesa){
        return despesaService.salvarDespesa(despesa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarDespesaPorId(@PathVariable("id") Integer id){
        return despesaService.buscarDespesaPorId(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDespesa(@Valid @PathVariable("id") Integer id,@RequestBody Despesa despesa){
        return despesaService.atualizarDespesa(id,despesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarDespesa(@PathVariable("id") Integer id){
        return despesaService.deletarDespesa(id);
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<?> buscarDespesasPorAnoEMes(@PathVariable("ano")Integer ano,@PathVariable("mes")Integer mes){
        return despesaService.buscarReceitasPorAnoEMes(ano,mes);
    }
}
