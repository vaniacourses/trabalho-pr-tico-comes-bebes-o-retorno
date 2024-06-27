package uff.br.servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uff.br.servidor.model.Entrega;

import java.util.UUID;

public interface EntregaRepository extends JpaRepository<Entrega, UUID> {
}
