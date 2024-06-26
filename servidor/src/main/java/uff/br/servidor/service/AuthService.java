package uff.br.servidor.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.repository.UsuarioRepository;
import uff.br.servidor.request.LoginRequestBody;
import uff.br.servidor.request.LoginResponseBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;

    public LoginResponseBody login(LoginRequestBody login) {
        Usuario usuario = usuarioRepository.findByNome(login.getUsuario()).get();
        return LoginResponseBody
            .builder()
            .token("a")
            .build();
    }
}
