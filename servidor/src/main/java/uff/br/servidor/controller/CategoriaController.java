package uff.br.servidor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uff.br.servidor.model.Categoria;
import uff.br.servidor.request.CategoriaPostRequestBody;
import uff.br.servidor.request.CategoriaPutRequestBody;
import uff.br.servidor.service.CategoriaService;

import java.util.UUID;

@RestController
@RequestMapping("categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<Categoria>> findAll(Pageable pageable){
        Page<Categoria> categoriaPage = categoriaService.findAll(pageable);
        return new ResponseEntity<>(categoriaPage, HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<Page<Categoria>> findByNome(Pageable pageable, @RequestParam String nome){
        Page<Categoria> categoriaPage = categoriaService.findByNome(pageable, nome);
        return new ResponseEntity<>(categoriaPage, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody CategoriaPostRequestBody categoriaPostRequestBody){
        return new ResponseEntity<>(categoriaService.salvar(categoriaPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody CategoriaPutRequestBody categoriaPutRequestBody){
        categoriaService.atualizar(categoriaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        categoriaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
