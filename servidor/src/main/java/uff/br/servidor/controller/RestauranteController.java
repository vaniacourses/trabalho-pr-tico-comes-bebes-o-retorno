package uff.br.servidor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uff.br.servidor.model.Restaurante;
import uff.br.servidor.service.RestauranteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @Autowired
    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<Restaurante> listarRestaurantes() {
        return restauranteService.listarRestaurantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarRestaurantePorId(@PathVariable UUID id) {
        Restaurante restaurante = restauranteService.buscarRestaurantePorId(id);
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<Restaurante> salvarRestaurante(@RequestBody Restaurante restaurante) {
        Restaurante restauranteSalvo = restauranteService.salvarRestaurante(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizarRestaurante(@PathVariable UUID id,
            @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtualizado = restauranteService.atualizarRestaurante(id, restaurante);
        if (restauranteAtualizado != null) {
            return ResponseEntity.ok(restauranteAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable UUID id) {
        restauranteService.deletarRestaurante(id);
        return ResponseEntity.noContent().build();
    }
}
