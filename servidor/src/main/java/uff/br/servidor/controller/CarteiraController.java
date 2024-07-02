package uff.br.servidor.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uff.br.servidor.model.Carteira;
import uff.br.servidor.service.CarteiraService;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {
    
    @Autowired
    private CarteiraService carteiraService;

    @PostMapping
    public ResponseEntity<Object> criar(@Valid @RequestBody Carteira entity) {
       try {
        var result = this.carteiraService.criar(entity);
        return ResponseEntity.status(201).body(result);
       } catch (Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
       }
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        try {
            Carteira result = this.carteiraService.getById(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Carteira entity) {
        try {
            Carteira result = this.carteiraService.update(id, entity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            this.carteiraService.delete(id);
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
