package uff.br.servidor.service;

//import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import uff.br.servidor.exceptions.BadRequestException;
import uff.br.servidor.model.Produto;
import uff.br.servidor.model.Restaurante;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.repository.RestauranteRepository;
import uff.br.servidor.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RestauranteService(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarRestaurantePorId(UUID id) {
        return restauranteRepository.findById(id).orElse(null);
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
