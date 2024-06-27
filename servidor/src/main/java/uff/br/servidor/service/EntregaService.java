package uff.br.servidor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.model.Entrega;
import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.Restaurante;
import uff.br.servidor.model.StatusEntrega;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.repository.EntregaRepository;
import uff.br.servidor.repository.PedidoRepository;
import uff.br.servidor.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Entrega> listarEntregas() {
        return this.entregaRepository.findAll();
    }

    public Entrega buscarEntregaPorId(UUID id) {
        return this.entregaRepository.findById(id).orElseThrow(() -> new BadRequestException("Entrega não encontrada"));
    }

    public Entrega salvarEntrega(Entrega entrega) {
        Pedido pedido = pedidoRepository.findById(entrega.getPedido().getId()).orElse(null);
        Usuario usuario = usuarioRepository.findById(entrega.getUsuario().getId()).orElse(null);
        if (pedido == null || usuario == null) {
            new RuntimeException("Usuário não encontrado ou pedido não encontrado.");
        }
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataCriacao(new Date());
        return this.entregaRepository.save(entrega);
    }

    public Entrega atualizarEntrega(UUID id, Entrega entregaAtualizada) {
        Entrega entrega = buscarEntregaPorId(id);
        entrega.setStatus(entregaAtualizada.getStatus());
        entrega.setDataAtualizacao(new Date());
        return this.entregaRepository.save(entrega);
    }

    public void deletarEntrega(UUID id) {
        this.entregaRepository.deleteById(id);
    }
}
