package uff.br.servidor.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.model.ProdutoPedido;
import uff.br.servidor.repository.PedidoRepository;
import uff.br.servidor.repository.ProdutoRepository;
import uff.br.servidor.request.ProdutoPedidoPostRequestBody;

@Component
@RequiredArgsConstructor
public class ProdutoPedidoMapper {
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public ProdutoPedido toProdutoPedido(ProdutoPedidoPostRequestBody produtoPedidoPostRequestBody){
        return ProdutoPedido.builder()
                .quantidade(produtoPedidoPostRequestBody.getQuantidade())
                .produto(produtoRepository.findByNome(produtoPedidoPostRequestBody.getProduto())
                        .orElseThrow(()-> new BadRequestException("Nome do produto nao encontrado")))
                .pedido(pedidoRepository.findById(produtoPedidoPostRequestBody.getPedido_id())
                        .orElseThrow(()-> new BadRequestException("Id do pedido nao encontrado")))
                .build();
    }
}
