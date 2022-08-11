package com.devtarlley.controlefinanceiro.controller;

import com.devtarlley.controlefinanceiro.model.Despesa;
import com.devtarlley.controlefinanceiro.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public ResponseEntity<?> buscarTodasDespesas(@RequestParam(required = false) String descricao){
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.buscarTodasDespesas(descricao));
    }

    @PostMapping
    public ResponseEntity<?> salvarDespesa(@Valid @RequestBody Despesa despesa){
        Despesa entity = despesaService.salvarDespesa(despesa);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarDespesaPorId(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.buscarDespesaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDespesa(@Valid @PathVariable("id") Integer id,@RequestBody Despesa despesa){
        return ResponseEntity.status(HttpStatus.OK).body(despesaService.atualizarDespesa(id,despesa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarDespesa(@PathVariable("id") Integer id){
        try{
            despesaService.deletarDespesa(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<?> buscarDespesasPorAnoEMes(@PathVariable("ano")Integer ano,@PathVariable("mes")Integer mes){
        List<Despesa> despesas;
        try{
             despesas = despesaService.buscarReceitasPorAnoEMes(ano, mes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return (despesas.isEmpty())?ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.OK).body(despesas);
    }
}
