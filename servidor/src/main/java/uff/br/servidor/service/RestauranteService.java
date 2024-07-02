package uff.br.servidor.service;

//import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
//import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.model.Produto;
import uff.br.servidor.model.Restaurante;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.repository.ProdutoRepository;
import uff.br.servidor.repository.RestauranteRepository;
import uff.br.servidor.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private ProdutoRepository produtoRepository;

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarRestaurantePorId(UUID id) {
        List<Produto> produtos = produtoRepository.findByRestauranteId(id);
        Restaurante restaurante = restauranteRepository.findById(id).orElse(null);
        if(restaurante != null) {
            restaurante.setProdutos(produtos);
        }
        return restaurante;
    }

    public Restaurante salvarRestaurante(Restaurante restaurante) {
        // System.out.println(restaurante.getUsuario().getId());
        Usuario usuario = usuarioRepository.findById(restaurante.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID fornecido."));
        restaurante.setUsuario(usuario);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizarRestaurante(UUID id, Restaurante restauranteAtualizado) {
        Restaurante restaurante = restauranteRepository.findById(id).orElse(null);
        if (restaurante != null) {
            restaurante.setNome(restauranteAtualizado.getNome());
            restaurante.setCnpj(restauranteAtualizado.getCnpj());
            restaurante.setUsuario(restauranteAtualizado.getUsuario());
            return restauranteRepository.save(restaurante);
        }
        return null;
    }

    public void deletarRestaurante(UUID id) {
        restauranteRepository.deleteById(id);
    }
}
