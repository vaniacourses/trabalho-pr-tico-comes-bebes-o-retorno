package uff.br.servidor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uff.br.servidor.model.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    Page<Produto> findByNome(Pageable pageable, String nome);
    Optional<Produto> findByNome(String nome);
    Page<Produto> findById(Pageable pageable, UUID id);
    Page<Produto> findByCategoria_Nome(Pageable pageable, String categoria);
    List<Produto> findByRestauranteId(UUID id);
}
