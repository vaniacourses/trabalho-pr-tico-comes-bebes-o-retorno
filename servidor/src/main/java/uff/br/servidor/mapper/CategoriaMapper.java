package uff.br.servidor.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uff.br.servidor.model.Categoria;
import uff.br.servidor.request.CategoriaPostRequestBody;

@Component
@RequiredArgsConstructor
public class CategoriaMapper {

    public Categoria toCategoria(CategoriaPostRequestBody categoriaPostRequestBody){
        return Categoria
                .builder()
                .nome(categoriaPostRequestBody.getNome())
                .build();
    }
}
