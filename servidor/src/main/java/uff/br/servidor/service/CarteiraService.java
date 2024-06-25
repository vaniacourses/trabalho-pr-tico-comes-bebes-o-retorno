package uff.br.servidor.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import uff.br.servidor.model.Carteira;
import uff.br.servidor.model.SolicitacaoCadastro;
import uff.br.servidor.repository.CarteiraRepository;
import uff.br.servidor.repository.UsuarioRepository;

@Service
public class CarteiraService {
    
    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Carteira criar(Carteira carteira) {
        usuarioRepository.findById(carteira.getUsuarioId()).ifPresentOrElse(
            pedido -> {
                carteiraRepository.save(carteira);
            },
            () -> {
                throw new EntityNotFoundException("Pedido com ID " + carteira.getUsuarioId() + " não encontrado");
            }
        );
        return carteira;
    }


    public Carteira getById(UUID id) {
        return carteiraRepository.findById(id).orElse(null);
    }

    public void delete(UUID id) {
        carteiraRepository.findById(id).ifPresentOrElse(
            solicitacao -> {
                carteiraRepository.delete(solicitacao);
            },
            () -> {
                throw new EntityNotFoundException("Carteira com ID " + id + " não encontrada");
            }
        );
    }

    public Carteira update(UUID id, Carteira carteira) {
        Carteira entity = carteiraRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException("Solicitação com ID " + id + " não encontrada");
        }
        entity.setSaldo(carteira.getSaldo());
        entity.setUsuarioId(carteira.getUsuarioId());
        return carteiraRepository.save(carteira);
    }

}
