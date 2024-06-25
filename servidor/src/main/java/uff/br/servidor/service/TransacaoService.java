package uff.br.servidor.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import uff.br.servidor.model.Transacao;
import uff.br.servidor.repository.PedidoRepository;
import uff.br.servidor.repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Transacao criar(Transacao transacao) {
        pedidoRepository.findById(transacao.getPedidoId()).ifPresentOrElse(
            pedido -> {
                transacaoRepository.save(transacao);
            },
            () -> {
                throw new EntityNotFoundException("Pedido com ID " + transacao.getPedidoId() + " não encontrado");
            }
        );
        return transacao;
    }

    public List<Transacao> getAll() {
        return transacaoRepository.findAll();
    }

    public void delete(UUID id) {
        transacaoRepository.findById(id).ifPresentOrElse(
            transacao -> {
                transacaoRepository.delete(transacao);
            },
            () -> {
                throw new EntityNotFoundException("Transação com ID " + id + " não encontrada");
            }
        );
    }

    public Transacao update(UUID id, Transacao transacao) {
        Transacao entity = transacaoRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException("Transação com ID " + id + " não encontrada");
        }
        entity.setCpf(transacao.getCpf());
        entity.setTipo(transacao.getTipo());
        entity.setPedidoId(transacao.getPedidoId());
        return transacaoRepository.save(transacao);
    }

}
