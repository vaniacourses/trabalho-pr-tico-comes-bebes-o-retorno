package uff.br.servidor.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import uff.br.servidor.dto.PaymentDTO;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.model.Transacao;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.providers.JwtProvider;
import uff.br.servidor.repository.PedidoRepository;
import uff.br.servidor.repository.TransacaoRepository;
import uff.br.servidor.repository.UsuarioRepository;
import uff.br.servidor.strategy.TransacaoStrategy;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ApplicationContext context;

    @Autowired
    public TransacaoService(ApplicationContext context) {
        this.context = context;
    }

    public Transacao criar(String token, PaymentDTO paymentDTO) {
        String userId = jwtProvider.validateToken(token);
        Usuario user = usuarioRepository.findById(UUID.fromString(userId))
            .orElseThrow(()-> new BadRequestException("id usuario nao encontrado"));
        Transacao transacao = Transacao.builder()
            .cpf(paymentDTO.getCpf())
            .pedido(paymentDTO.getPedido())
            .pedidoId(paymentDTO.getPedidoId())
            .tipo(paymentDTO.getTipo())
            .build();
        this.pedidoRepository.findById(paymentDTO.getPedidoId()).ifPresentOrElse(
            pedido -> {
                TransacaoStrategy strategy = (TransacaoStrategy) context.getBean(paymentDTO.getTipo().toString());
                strategy.pagar(user, paymentDTO);
                this.transacaoRepository.save(transacao);
            }, () -> {
                throw new EntityNotFoundException("Pedido com ID " + paymentDTO.getPedidoId() + " não encontrado");
            }
        );
        return transacao;
    }
    
}
