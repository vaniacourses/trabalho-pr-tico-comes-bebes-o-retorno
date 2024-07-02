package uff.br.servidor.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.model.Produto;
import uff.br.servidor.repository.CategoriaRepository;
import uff.br.servidor.repository.RestauranteRepository;
import uff.br.servidor.request.ProdutoPostRequestBody;

@Component
@RequiredArgsConstructor
public class ProdutoMapper {
    private final CategoriaRepository categoriaRepository;
    private final RestauranteRepository restauranteRepository;
    public Produto toProduto(ProdutoPostRequestBody produtoPostRequestBody){
        return Produto
                .builder()
                .nome(produtoPostRequestBody.getNome())
                .descricao(produtoPostRequestBody.getDescricao())
                .preco(produtoPostRequestBody.getPreco())
                .estoque(produtoPostRequestBody.getEstoque())
                .restaurante(restauranteRepository.findById(produtoPostRequestBody.getRestaurante_id())
                        .orElseThrow(()-> new BadRequestException("Nome do restaurante nao encontrado")))
                .categoria(categoriaRepository.findByNome(produtoPostRequestBody.getCategoria())
                        .orElseThrow(() -> new BadRequestException("Nome da categoria nao encontrada")))
                .build();
    }
}
