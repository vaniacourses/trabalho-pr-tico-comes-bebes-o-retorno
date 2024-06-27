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
        this.pedidoRepository.findById(transacao.getPedidoId()).ifPresentOrElse(
            pedido -> {
                this.transacaoRepository.save(transacao);
            },
            () -> {
                throw new EntityNotFoundException("Pedido com ID " + transacao.getPedidoId() + " não encontrado");
            }
        );
        return transacao;
    }
    
}
