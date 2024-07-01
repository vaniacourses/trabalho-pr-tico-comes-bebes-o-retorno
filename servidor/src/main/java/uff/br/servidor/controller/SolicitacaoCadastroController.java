package uff.br.servidor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uff.br.servidor.model.SolicitacaoCadastro;
import uff.br.servidor.service.SolicitacaoCadastroService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/solicitacao-de-cadastro")
public class SolicitacaoCadastroController {


    @Autowired
    private SolicitacaoCadastroService solicitacaoService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody SolicitacaoCadastro entity) {
       try {
        var result = this.solicitacaoService.criar(entity);
        return ResponseEntity.status(201).body(result);
       } catch (Exception e) {
        return ResponseEntity.status(422).body(e.getMessage());
       }
        
    }
    
    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<SolicitacaoCadastro> result = this.solicitacaoService.getAll();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id,@RequestBody SolicitacaoCadastro entity) {
        try {
            SolicitacaoCadastro result = this.solicitacaoService.update(id,entity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
