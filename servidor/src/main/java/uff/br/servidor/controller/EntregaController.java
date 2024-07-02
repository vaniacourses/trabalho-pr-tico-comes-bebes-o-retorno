package uff.br.servidor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uff.br.servidor.model.Entrega;
import uff.br.servidor.service.EntregaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("entregas")
public class EntregaController {

    private final EntregaService entregaService;

    @Autowired
    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public List<Entrega> listarEntregas() {
        return entregaService.listarEntregas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarEntregaPorId(@PathVariable UUID id) {
        Entrega entrega = entregaService.buscarEntregaPorId(id);
        return ResponseEntity.ok(entrega);
    }

    @PostMapping
    public ResponseEntity<Entrega> criarEntrega(@RequestBody Entrega entrega) {
        Entrega novaEntrega = entregaService.salvarEntrega(entrega);
        return ResponseEntity.ok(novaEntrega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega> atualizarEntrega(@PathVariable UUID id, @RequestBody Entrega entregaAtualizada) {
        Entrega entrega = entregaService.atualizarEntrega(id, entregaAtualizada);
        return ResponseEntity.ok(entrega);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntrega(@PathVariable UUID id) {
        entregaService.deletarEntrega(id);
        return ResponseEntity.noContent().build();
    }
}
