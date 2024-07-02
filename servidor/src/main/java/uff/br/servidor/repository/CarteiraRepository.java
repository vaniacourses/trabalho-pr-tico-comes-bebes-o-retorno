package uff.br.servidor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import uff.br.servidor.model.Carteira;

public interface CarteiraRepository extends JpaRepository<Carteira, UUID>{}
