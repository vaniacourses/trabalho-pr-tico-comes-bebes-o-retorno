package uff.br.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uff.br.servidor.model.Categoria;
import uff.br.servidor.model.Restaurante;

import java.util.Optional;
import java.util.UUID;

public interface RestauranteRepository extends JpaRepository<Restaurante, UUID> {
    Optional<Restaurante> findByNome(String nome);
}
