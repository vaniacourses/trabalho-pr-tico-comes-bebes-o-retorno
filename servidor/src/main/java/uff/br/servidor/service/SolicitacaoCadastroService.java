package uff.br.servidor.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import uff.br.servidor.model.SolicitacaoCadastro;
import uff.br.servidor.repository.SolicitacaoCadastroRepository;
import uff.br.servidor.repository.UsuarioRepository;

@Service
public class SolicitacaoCadastroService {
    
    @Autowired
    private SolicitacaoCadastroRepository solicitacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public SolicitacaoCadastro criar(SolicitacaoCadastro solicitacao) {
            usuarioRepository.findById(solicitacao.getUsuarioId()).ifPresentOrElse(
            pedido -> {
                solicitacaoRepository.save(solicitacao);
            },
            () -> {
                throw new EntityNotFoundException("Pedido com ID " + solicitacao.getUsuarioId() + " não encontrado");
            }
        );
        return solicitacao;
    }

    public List<SolicitacaoCadastro> getAll() {
        return solicitacaoRepository.findAll();
    }

    public void delete(UUID id) {
        solicitacaoRepository.findById(id).ifPresentOrElse(
            solicitacao -> {
                solicitacaoRepository.delete(solicitacao);
            },
            () -> {
                throw new EntityNotFoundException("Solicitação com ID " + id + " não encontrada");
            }
        );
    }

    public SolicitacaoCadastro update(UUID id, SolicitacaoCadastro solicitacao) {
        SolicitacaoCadastro entity = solicitacaoRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException("Solicitação com ID " + id + " não encontrada");
        }
        entity.setStatus(solicitacao.getStatus());
        entity.setVeiculo(solicitacao.getVeiculo());
        entity.setCnh(solicitacao.getCnh());
        entity.setUsuarioId(solicitacao.getUsuarioId());
        return solicitacaoRepository.save(solicitacao);
    }

}
