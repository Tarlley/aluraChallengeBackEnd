package com.devtarlley.controlefinanceiro.service;

import com.devtarlley.controlefinanceiro.model.Receita;
import com.devtarlley.controlefinanceiro.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public ResponseEntity<?> buscarTodasReceitas(String descricao){
         return (descricao == null)?
             ResponseEntity.status(HttpStatus.OK).body(receitaRepository.findAll()):
             ResponseEntity.status(HttpStatus.OK).body(receitaRepository.findByDescricao(descricao));
    }

    public ResponseEntity<?> salvarReceita(Receita receita) {
        try {
            Receita receitaExistente = receitaRepository.buscarReceitaPorMes(receita.getData().getMonth().getValue())
                    .stream()
                    .filter(item -> item.getDescricao().equals(receita.getDescricao())).findAny().orElse(null);

            if (receitaExistente != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Receita já cadastrada");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(receitaRepository.saveAndFlush(receita));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar receita");
        }
    }

    public ResponseEntity<?> buscarReceitaPorId(Integer id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(receitaRepository.findById(id).orElse(null));
    }

    public ResponseEntity<?>  atualizarReceita(Integer id,Receita receita) {
        try {
            Receita receitaExistente = receitaRepository.buscarReceitaPorMes(receita.getData().getMonth().getValue())
                    .stream()
                    .filter(item -> item.getDescricao().equals(receita.getDescricao())).findAny().orElse(null);

            if (receitaExistente != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Receita já cadastrada");
            }
            receita.setId(id);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(receitaRepository.saveAndFlush(receita));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar receita");
        }
    }

    public ResponseEntity<?> deletarReceita(Integer id) {
        receitaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
