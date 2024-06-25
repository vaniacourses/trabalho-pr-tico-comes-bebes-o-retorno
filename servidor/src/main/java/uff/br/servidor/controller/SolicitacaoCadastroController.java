package uff.br.servidor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uff.br.servidor.model.SolicitacaoCadastro;
import uff.br.servidor.model.Transacao;
import uff.br.servidor.service.SolicitacaoCadastroService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(path = "/solicitacao-de-cadastro")
public class SolicitacaoCadastroController {


    @Autowired
    private SolicitacaoCadastroService solicitacaoService;

    @PostMapping
    public ResponseEntity<Object> criar(@Valid @RequestBody SolicitacaoCadastro entity) {
       try {
        var result = solicitacaoService.criar(entity);
        return ResponseEntity.status(201).body(result);
       } catch (Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
       }
        
    }
    
    @GetMapping
    public ResponseEntity<Object> getALL() {
        try {
            List<SolicitacaoCadastro> result = solicitacaoService.getAll();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody SolicitacaoCadastro entity) {
        try {
            SolicitacaoCadastro result = solicitacaoService.update(id, entity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            solicitacaoService.delete(id);
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
