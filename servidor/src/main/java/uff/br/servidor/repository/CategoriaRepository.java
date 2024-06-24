package uff.br.servidor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uff.br.servidor.model.Categoria;

import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    Page<Categoria> findByNome(Pageable pageable, String nome);
    Optional<Categoria> findByNome(String nome);
}
