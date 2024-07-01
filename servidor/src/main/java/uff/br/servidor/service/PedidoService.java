package uff.br.servidor.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.mapper.PedidoMapper;
import uff.br.servidor.model.*;
import uff.br.servidor.providers.JwtProvider;
import uff.br.servidor.repository.PedidoRepository;
import uff.br.servidor.repository.ProdutoPedidoRepository;
import uff.br.servidor.repository.ProdutoRepository;
import uff.br.servidor.repository.UsuarioRepository;
import uff.br.servidor.request.PedidoPostRequestBody;
import uff.br.servidor.request.PedidoPutRequestBody;

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
    private final ProdutoRepository produtoRepository;

    public Page<Pedido> findAll(Pageable pageable){
        return pedidoRepository.findAll(pageable);
    }

    public List<Pedido> findByCpfUsuario(String cpf){
        return pedidoRepository.findPedidosByUsuarioCpf(cpf);
    }

    public Pedido getCarrinho(String token){
        String userId = jwtProvider.validateToken(token);
        Pedido pedido = pedidoRepository.findByUsuario_IdAndStatus(UUID.fromString(userId), Status.ABERTO);
        List<ProdutoPedido> produtoPedidos = produtoPedidoRepository.findProdutoPedidoByPedido_Id(pedido.getId());
        pedido.setItens(produtoPedidos);
        return pedido;
    }

    public Pedido adicionarCarrinho(String token, UUID produto_id){
        String userId = jwtProvider.validateToken(token);
        Usuario user = usuarioRepository.findById(UUID.fromString(userId))
                .orElseThrow(()-> new BadRequestException("id usuario nao encontrado"));
        Produto produtoCarrinho = produtoRepository.findById(produto_id).orElseThrow(()->new BadRequestException("Id do produto nao encontrado"));
        Pedido pedido = pedidoRepository.findByUsuario_IdAndStatus(UUID.fromString(userId), Status.ABERTO);
        if (pedido == null){
            Pedido pedidoCriado = pedidoRepository.save(
                    Pedido.builder()
                            .status(Status.ABERTO)
                            .usuario(user)
                            .build());
            pedido = pedidoCriado;
        }

        ProdutoPedido produtoPedidoCriado  = produtoPedidoRepository.save(
                ProdutoPedido.builder()
                        .pedido(pedido)
                        .produto(produtoCarrinho)
                        .quantidade(1)
                        .build()
        );
        List<ProdutoPedido> produtoPedidos = produtoPedidoRepository.findProdutoPedidoByPedido_Id(pedido.getId());
        produtoPedidos.add(produtoPedidoCriado);
        pedido.setItens(produtoPedidos);
        return pedido;
    }

    public void deletarCarrinho(String token, UUID item_id){
        String userId = jwtProvider.validateToken(token);
        Usuario user = usuarioRepository.findById(UUID.fromString(userId))
                .orElseThrow(()-> new BadRequestException("id usuario nao encontrado"));
        produtoPedidoRepository.deleteById(item_id);
        Pedido pedido = pedidoRepository.findByUsuario_IdAndStatus(UUID.fromString(userId), Status.ABERTO);
        if( pedido.getItens() == null){
            pedidoRepository.delete(pedido);
        }

    }

    public Pedido salvar(PedidoPostRequestBody pedidoPostRequestBody){
        pedidoPostRequestBody.setStatus(Status.ABERTO);
        return pedidoRepository.save(pedidoMapper.toPedido(pedidoPostRequestBody));
    }

    public void atualizar(PedidoPutRequestBody pedidoPutRequestBody){
        Pedido pedidoSalvo = findByIdOrElse(pedidoPutRequestBody.getId());

        pedidoSalvo.setUsuario(usuarioRepository.findById(pedidoPutRequestBody.getUsuario())
                .orElseThrow(()-> new BadRequestException("Nome do usuario nao encontrado")));
        pedidoSalvo.setStatus(Status.valueOf(pedidoPutRequestBody.getStatus()));

        pedidoRepository.save(pedidoSalvo);
    }

    public Pedido findByIdOrElse(UUID id){
        return pedidoRepository.findById(id).orElseThrow(()-> new BadRequestException("Id do pedido nao encontrado"));
    }

    public void deletar(UUID id){
        pedidoRepository.deleteById(id);
    }

}
