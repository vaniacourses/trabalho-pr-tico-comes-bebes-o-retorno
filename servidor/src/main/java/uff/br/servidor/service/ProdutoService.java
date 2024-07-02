package uff.br.servidor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.mapper.ProdutoMapper;
import uff.br.servidor.model.Produto;
import uff.br.servidor.repository.CategoriaRepository;
import uff.br.servidor.repository.ProdutoRepository;
import uff.br.servidor.repository.RestauranteRepository;
import uff.br.servidor.request.ProdutoPostRequestBody;
import uff.br.servidor.request.ProdutoPutRequestBody;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    private final CategoriaRepository categoriaRepository;
    private final RestauranteRepository restauranteRepository;

    public Page<Produto> findAll(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> findById(Pageable pageable, UUID id){
        return produtoRepository.findById(pageable, id);
    }

    public Page<Produto> findByCategoria(Pageable pageable, String categoria){
        return produtoRepository.findByCategoria_Nome(pageable, categoria);
    }

    public Produto salvar(ProdutoPostRequestBody produtoPostRequestBody){
        return produtoRepository.save(produtoMapper.toProduto(produtoPostRequestBody));
    }

    public void atualizar(ProdutoPutRequestBody produtoPutRequestBody){
        Produto produtoSalvo = findByIdOrElse(produtoPutRequestBody.getId());
        produtoSalvo.setNome(produtoPutRequestBody.getNome());
        produtoSalvo.setDescricao(produtoPutRequestBody.getDescricao());
        produtoSalvo.setPreco(produtoPutRequestBody.getPreco());
        produtoSalvo.setEstoque(produtoPutRequestBody.getEstoque());
        produtoSalvo.setRestaurante(restauranteRepository.findById(produtoPutRequestBody.getRestaurante_id())
                .orElseThrow(()-> new BadRequestException("Nome do restaurante nao encontrado")));
        produtoSalvo.setCategoria(categoriaRepository.findByNome(produtoPutRequestBody.getCategoria())
                .orElseThrow(()-> new BadRequestException("Nome de categoria nao encontrada")));

        produtoRepository.save(produtoSalvo);
    }

    public Produto findByIdOrElse(UUID id){
        return produtoRepository.findById(id).orElseThrow(()-> new BadRequestException("Id do produto nao encontrado"));
    }

    public void deletar(UUID id){
        produtoRepository.deleteById(id);
    }
}
