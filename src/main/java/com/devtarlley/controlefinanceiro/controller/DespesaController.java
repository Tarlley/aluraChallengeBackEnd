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
    private DespesaService receitaService;

    @GetMapping
    public ResponseEntity<?> buscarTodasDespesas(){
        return receitaService.buscarTodasDespesas();
    }

    @PostMapping
    public ResponseEntity<?> salvarDespesa(@Valid @RequestBody Despesa receita){
        return receitaService.salvarDespesa(receita);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarDespesaPorId(@PathVariable("id") Integer id){
        return receitaService.buscarDespesaPorId(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDespesa(@Valid @PathVariable("id") Integer id,@RequestBody Despesa receita){
        return receitaService.atualizarDespesa(id,receita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarDespesa(@PathVariable("id") Integer id){
        return receitaService.deletarDespesa(id);
    }
}
