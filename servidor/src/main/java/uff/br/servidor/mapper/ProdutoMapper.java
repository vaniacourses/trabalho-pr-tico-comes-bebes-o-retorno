package uff.br.servidor.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.model.Produto;
import uff.br.servidor.repository.CategoriaRepository;
import uff.br.servidor.request.ProdutoPostRequestBody;

@Component
@RequiredArgsConstructor
public class ProdutoMapper {
    private final CategoriaRepository categoriaRepository;
    public Produto toProduto(ProdutoPostRequestBody produtoPostRequestBody){
        return Produto
                .builder()
                .nome(produtoPostRequestBody.getNome())
                .descricao(produtoPostRequestBody.getDescricao())
                .preco(produtoPostRequestBody.getPreco())
                .categoria(categoriaRepository.findByNome(produtoPostRequestBody.getCategoria())
                        .orElseThrow(() -> new BadRequestException("Nome da categoria nao encontrada")))
                .build();
    }
}
