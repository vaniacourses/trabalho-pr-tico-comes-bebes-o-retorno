package uff.br.servidor.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.mapper.PedidoMapper;
import uff.br.servidor.model.Pedido;
import uff.br.servidor.model.ProdutoPedido;
import uff.br.servidor.model.Status;
import uff.br.servidor.providers.JwtProvider;
import uff.br.servidor.repository.PedidoRepository;
import uff.br.servidor.repository.ProdutoPedidoRepository;
import uff.br.servidor.repository.UsuarioRepository;
import uff.br.servidor.request.PedidoPostRequestBody;
import uff.br.servidor.request.PedidoPutRequestBody;
import uff.br.servidor.state.AbertoState;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    @Autowired
    private ProdutoPedidoRepository produtoPedidoRepository;
    @Autowired
    private JwtProvider jwtProvider;
    private final UsuarioRepository usuarioRepository;

    public Page<Pedido> findAll(Pageable pageable){
        return pedidoRepository.findAll(pageable);
    }

    public List<Pedido> findByCpfUsuario(String cpf){
        return pedidoRepository.findPedidosByUsuarioCpf(cpf);
    }

    public Pedido getCarrinho(String token){
        String userId = jwtProvider.validateToken(token);
        Pedido pedido = pedidoRepository.findByUsuario_IdAndStatus(UUID.fromString(userId), Status.ABERTO);
        System.out.println(pedido);
        List<ProdutoPedido> produtoPedidos = produtoPedidoRepository.findProdutoPedidoByPedido_Id(pedido.getId());
        pedido.setItens(produtoPedidos);
        return pedido;
    }

    public Pedido salvar(PedidoPostRequestBody pedidoPostRequestBody){
        pedidoPostRequestBody.setStatus(Status.ABERTO);
        pedidoPostRequestBody.setSituacaoPedido(new AbertoState());
        return pedidoRepository.save(pedidoMapper.toPedido(pedidoPostRequestBody));
    }

    public Pedido nextStatus(UUID id){
    Pedido pedido = pedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
       pedido.inicializarSituacaoPedido();
       pedido.performAction();
        return pedidoRepository.save(pedido);
    }


    public Pedido cancelarPedido(UUID id){
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
           pedido.inicializarSituacaoPedido();
           pedido.cancelAction();
            return pedidoRepository.save(pedido);
        }
    
    // public void atualizar(PedidoPutRequestBody pedidoPutRequestBody){
    //     Pedido pedidoSalvo = findByIdOrElse(pedidoPutRequestBody.getId());

    //     pedidoSalvo.setUsuario(usuarioRepository.findById(pedidoPutRequestBody.getUsuario())
    //             .orElseThrow(()-> new BadRequestException("Nome do usuario nao encontrado")));
    //     pedidoSalvo.setStatus(Status.valueOf(pedidoPutRequestBody.getStatus()));

    //     pedidoRepository.save(pedidoSalvo);
    // }

    public Pedido findByIdOrElse(UUID id){
        return pedidoRepository.findById(id).orElseThrow(()-> new BadRequestException("Id do pedido nao encontrado"));
    }

    public void deletar(UUID id){
        pedidoRepository.deleteById(id);
    }

}
