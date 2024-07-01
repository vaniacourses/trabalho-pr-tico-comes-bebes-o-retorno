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

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("pedido")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PedidoController {
    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<Pedido>> findAll(Pageable pageable){
        return new ResponseEntity<>(pedidoService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/carrinho")
    public ResponseEntity<Object> getCarrinho(@RequestHeader(name = "Authorization") String token) {
        try {
            var result  = pedidoService.getCarrinho(token);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/carrinho/{produto_id}")
    public ResponseEntity<Object> postCarrinho(@RequestHeader(name = "Authorization") String token,@PathVariable UUID produto_id) {
        try {
            var result  = pedidoService.adicionarCarrinho(token, produto_id);
            return ResponseEntity.status(204).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/carrinho/{item_id}")
    public ResponseEntity<Void> deleteCarrinho(@RequestHeader(name = "Authorization") String token,@PathVariable UUID item_id) {
        try {
            pedidoService.deletarCarrinho(token, item_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @GetMapping("/{cpf}")
    public ResponseEntity<List<Pedido>> findByCpfUsuario(@PathVariable String cpf){
        return new ResponseEntity<>(pedidoService.findByCpfUsuario(cpf), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable UUID id){
        return new ResponseEntity<>(pedidoService.findByIdOrElse(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody PedidoPostRequestBody pedidoPostRequestBody){
        return new ResponseEntity<>(pedidoService.salvar(pedidoPostRequestBody), HttpStatus.CREATED);
    }


    @PostMapping("/status/{id}")
    public ResponseEntity<Object> nextStatus(@PathVariable UUID id) {
        try {
            var result = pedidoService.nextStatus(id);
            return ResponseEntity.status(200).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        
    }

    @PostMapping("/cancelar/{id}")
    public ResponseEntity<Object> cancelarPedido(@PathVariable UUID id) {
        try {
            var result = pedidoService.cancelarPedido(id);
            return ResponseEntity.status(200).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        
    }
    
    // @PutMapping
    // public ResponseEntity<Void> atualizar(@RequestBody PedidoPutRequestBody pedidoPutRequestBody){
    //     pedidoService.atualizar(pedidoPutRequestBody);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        pedidoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
