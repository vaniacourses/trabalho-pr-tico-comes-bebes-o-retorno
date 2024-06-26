package uff.br.servidor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uff.br.servidor.model.ProdutoPedido;
import uff.br.servidor.request.ProdutoPedidoPostRequestBody;
import uff.br.servidor.request.ProdutoPedidoPutRequestBody;
import uff.br.servidor.service.ProdutoPedidoService;

import java.util.UUID;

@RestController
@RequestMapping("produto_pedido")
@RequiredArgsConstructor
public class ProdutoPedidoController {
    private final ProdutoPedidoService produtoPedidoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoPedido>> findAll(Pageable pageable){
        return new ResponseEntity<>(produtoPedidoService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoPedido> salvar(@RequestBody ProdutoPedidoPostRequestBody produtoPedidoPostRequestBody){
        return new ResponseEntity<>(produtoPedidoService.salvar(produtoPedidoPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody ProdutoPedidoPutRequestBody produtoPedidoPutRequestBody){
        produtoPedidoService.atualizar(produtoPedidoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        produtoPedidoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
