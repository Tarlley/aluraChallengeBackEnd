package com.devtarlley.controlefinanceiro.controller;

import com.devtarlley.controlefinanceiro.model.Receita;
import com.devtarlley.controlefinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<?> buscarTodasReceitas(@RequestParam(required = false) String descricao){
        return receitaService.buscarTodasReceitas(descricao);
    }

    @PostMapping
    public ResponseEntity<?> salvarReceita(@Valid @RequestBody Receita receita){
        return receitaService.salvarReceita(receita);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarReceitaPorId(@PathVariable("id") Integer id){
        return receitaService.buscarReceitaPorId(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarReceita(@Valid @PathVariable("id") Integer id,@RequestBody Receita receita){
        return receitaService.atualizarReceita(id,receita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarReceita(@PathVariable("id") Integer id){
        return receitaService.deletarReceita(id);
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<?> buscarReceitasPorAnoEMes(@PathVariable("ano") Integer ano, @PathVariable("mes")Integer mes){
        return receitaService.buscarReceitasPorAnoEMes(ano,mes);
    }
}
