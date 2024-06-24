package uff.br.servidor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.mapper.CategoriaMapper;
import uff.br.servidor.model.Categoria;
import uff.br.servidor.repository.CategoriaRepository;
import uff.br.servidor.request.CategoriaPostRequestBody;
import uff.br.servidor.request.CategoriaPutRequestBody;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public Page<Categoria> findAll(Pageable pageable){
        return categoriaRepository.findAll(pageable);
    }

    public Page<Categoria> findByNome(Pageable pageable, String nome){
        return categoriaRepository.findByNome(pageable, nome);
    }

    public Categoria salvar(CategoriaPostRequestBody categoriaPostRequestBody){
        return categoriaRepository.save(categoriaMapper.toCategoria(categoriaPostRequestBody));
    }

    public void atualizar(CategoriaPutRequestBody categoriaPutRequestBody){
        Categoria categoriaSalva = findByIdOrElse(categoriaPutRequestBody.getId());
        categoriaSalva.setNome(categoriaPutRequestBody.getNome());
        categoriaRepository.save(categoriaSalva);
    }

    public Categoria findByIdOrElse(UUID id){
        return categoriaRepository.findById(id).orElseThrow(() -> new BadRequestException("Id da categoria nao encontrado"));
    }

    public void deletar(UUID id){
        categoriaRepository.deleteById(id);
    }
}
