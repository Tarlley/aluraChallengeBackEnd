package com.devtarlley.controlefinanceiro.controller;

import com.devtarlley.controlefinanceiro.service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<?> buscarResumoPorAnoEMes(@PathVariable("ano")Integer ano, @PathVariable("mes")Integer mes){
        return resumoService.buscarResumoPorAnoEMes(ano,mes);
    }
}
