package com.devtarlley.controlefinanceiro.service;

import com.devtarlley.controlefinanceiro.enums.Categorias;
import com.devtarlley.controlefinanceiro.model.Despesa;
import com.devtarlley.controlefinanceiro.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.devtarlley.controlefinanceiro.enums.Categorias.OUTRAS;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public ResponseEntity<?> buscarTodasDespesas(){
        return ResponseEntity.status(HttpStatus.OK).body(despesaRepository.findAll());
    }

    public ResponseEntity<?> salvarDespesa(Despesa despesa) {
        try {
            Despesa despesaExistente = despesaRepository.buscarDespesaPorMes(despesa.getData().getMonth().getValue())
                    .stream()
                    .filter(item -> item.getDescricao().equals(despesa.getDescricao())).findAny().orElse(null);

            if (despesaExistente != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Despesa já cadastrada");
            }

            if (despesa.getCategoria() == null){
                despesa.setCategoria(OUTRAS);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(despesaRepository.saveAndFlush(despesa));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar despesa");
        }
    }

    public ResponseEntity<?> buscarDespesaPorId(Integer id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(despesaRepository.findById(id).orElse(null));
    }

    public ResponseEntity<?>  atualizarDespesa(Integer id,Despesa despesa) {
        try {
            Despesa despesaExistente = despesaRepository.buscarDespesaPorMes(despesa.getData().getMonth().getValue())
                    .stream()
                    .filter(item -> item.getDescricao().equals(despesa.getDescricao())).findAny().orElse(null);

            if (despesaExistente != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Despesa já cadastrada");
            }
            despesa.setId(id);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(despesaRepository.saveAndFlush(despesa));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar despesa");
        }
    }

    public ResponseEntity<?> deletarDespesa(Integer id) {
        despesaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
