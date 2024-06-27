package uff.br.servidor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uff.br.servidor.model.Produto;
import uff.br.servidor.request.ProdutoPostRequestBody;
import uff.br.servidor.request.ProdutoPutRequestBody;
import uff.br.servidor.service.ProdutoService;

import java.util.UUID;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<Produto>> findAll(Pageable pageable){
        return new ResponseEntity<>(produtoService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<Produto>> findById(Pageable pageable, @PathVariable UUID id){
        return new ResponseEntity<>(produtoService.findById(pageable, id), HttpStatus.OK);
    }

    @GetMapping("/categoria")
    public ResponseEntity<Page<Produto>> findByCategoria(Pageable pageable, String categoria){
        return new ResponseEntity<>(produtoService.findByCategoria(pageable, categoria), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody ProdutoPostRequestBody produtoPostRequestBody){
        return new ResponseEntity<>(produtoService.salvar(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody ProdutoPutRequestBody produtoPutRequestBody){
        produtoService.atualizar(produtoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
