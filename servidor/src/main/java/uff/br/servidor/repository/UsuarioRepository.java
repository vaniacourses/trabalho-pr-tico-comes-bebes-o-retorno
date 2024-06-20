package uff.br.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uff.br.servidor.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByNome(String nome);
}
