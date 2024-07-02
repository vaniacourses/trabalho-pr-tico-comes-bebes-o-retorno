package uff.br.servidor.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import uff.br.servidor.model.Transacao;


public interface TransacaoRepository  extends JpaRepository<Transacao, UUID> {

    Optional <Transacao> findByCpf(String cpf);
    Optional <Transacao> findById(UUID id);
}
