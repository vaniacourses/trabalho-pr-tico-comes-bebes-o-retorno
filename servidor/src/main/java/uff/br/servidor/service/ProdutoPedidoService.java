package uff.br.servidor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.mapper.ProdutoPedidoMapper;
import uff.br.servidor.model.ProdutoPedido;
import uff.br.servidor.repository.PedidoRepository;
import uff.br.servidor.repository.ProdutoPedidoRepository;
import uff.br.servidor.repository.ProdutoRepository;
import uff.br.servidor.request.ProdutoPedidoPostRequestBody;
import uff.br.servidor.request.ProdutoPedidoPutRequestBody;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoPedidoService {
    private final ProdutoPedidoRepository produtoPedidoRepository;
    private final ProdutoPedidoMapper produtoPedidoMapper;

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public Page<ProdutoPedido> findAll(Pageable pageable){
        return produtoPedidoRepository.findAll(pageable);
    }

    public ProdutoPedido salvar(ProdutoPedidoPostRequestBody produtoPedidoPostRequestBody){
        return produtoPedidoRepository.save(produtoPedidoMapper.toProdutoPedido(produtoPedidoPostRequestBody));
    }

    public void atualizar(ProdutoPedidoPutRequestBody produtoPedidoPutRequestBody){
        ProdutoPedido produtoPedidoSalvo = findByIdOrElse(produtoPedidoPutRequestBody.getId());

        produtoPedidoSalvo.setProduto(produtoRepository.findByNome(produtoPedidoPutRequestBody.getProduto())
                .orElseThrow(()-> new BadRequestException("Nome do produto nao encontrado")));
        produtoPedidoSalvo.setQuantidade(produtoPedidoSalvo.getQuantidade());
        produtoPedidoSalvo.setPedido(pedidoRepository.findById(produtoPedidoPutRequestBody.getPedido_id())
                .orElseThrow(()-> new BadRequestException("Id do pedido nao encontrado")));

        produtoPedidoRepository.save(produtoPedidoSalvo);
    }
    public ProdutoPedido findByIdOrElse(UUID id){
        return produtoPedidoRepository.findById(id).orElseThrow(()-> new BadRequestException("Id do produto_pedido nao encontrado"));
    }

    public void deletar(UUID id){
        produtoPedidoRepository.deleteById(id);
    }
}
