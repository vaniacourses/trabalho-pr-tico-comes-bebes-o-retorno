package uff.br.servidor.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import uff.br.servidor.model.SolicitacaoCadastro;


public interface SolicitacaoCadastroRepository extends JpaRepository<SolicitacaoCadastro, UUID>{

    Optional<SolicitacaoCadastro> findById(UUID id);
}