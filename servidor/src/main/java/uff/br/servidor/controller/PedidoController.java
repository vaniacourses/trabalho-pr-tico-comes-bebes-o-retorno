package uff.br.servidor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uff.br.servidor.model.Pedido;
import uff.br.servidor.request.PedidoPostRequestBody;
import uff.br.servidor.request.PedidoPutRequestBody;
import uff.br.servidor.service.PedidoService;

import java.util.UUID;

@RestController
@RequestMapping("pedido")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<Pedido>> findAll(Pageable pageable){
        return new ResponseEntity<>(pedidoService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/cpf")
    public ResponseEntity<Page<Pedido>> findByCpf(Pageable pageable, @RequestParam String cpf){
        return new ResponseEntity<>(pedidoService.findByCpfUsuario(pageable, cpf), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody PedidoPostRequestBody pedidoPostRequestBody){
        return new ResponseEntity<>(pedidoService.salvar(pedidoPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody PedidoPutRequestBody pedidoPutRequestBody){
        pedidoService.atualizar(pedidoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        pedidoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
