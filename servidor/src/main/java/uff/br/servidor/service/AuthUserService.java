package uff.br.servidor.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;
import uff.br.servidor.dto.AuthUserDTO;
import uff.br.servidor.dto.RegisterUserDTO;
import uff.br.servidor.model.Role;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.model.UsuarioStatus;
import uff.br.servidor.repository.UsuarioRepository;
import uff.br.servidor.security.AuthUserResponseDTO;

@Service
@RequiredArgsConstructor
public class AuthUserService {
    
    @Value("${security.token.secret}")
    private String secret_key;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthUserResponseDTO login(AuthUserDTO authUserDTO) {
        var usuario = this.usuarioRepository.findByCpf(authUserDTO.getCpf()).orElse(null);
        System.out.println(usuario);
        if(usuario == null){
            throw new RuntimeException("Login incorreto");
        }else{
            var password = this.passwordEncoder.matches(authUserDTO.getSenha(), usuario.getSenha_hash());
            if(password){
                Algorithm algorithm = Algorithm.HMAC256(secret_key);
                var token = JWT.create().withIssuer("secret").withSubject(usuario.getId().toString()).sign(algorithm);
                var auth = AuthUserResponseDTO.builder().token(token).role(usuario.getRole()).build();
                return auth;   
            }else{
                throw new RuntimeException("Login incorreto");
            }
        }
    }

    public Usuario registrar(RegisterUserDTO registerUserDTO) {
        usuarioRepository.findByCpf(registerUserDTO.getCpf()).ifPresent(
            (user) -> { throw new IllegalArgumentException("CPF ja existe"); }
        );
        Usuario usuario = Usuario.builder()
            .cpf(registerUserDTO.getCpf())
            .data_nascimento(registerUserDTO.getData_nascimento())
            .email(registerUserDTO.getEmail())
            .nome(registerUserDTO.getNome())
            .role(Role.USER)
            .senha_hash(passwordEncoder.encode(registerUserDTO.getSenha()))
            .status(UsuarioStatus.ATIVO)
            .telefone(registerUserDTO.getTelefone())
            .build();
        return usuarioRepository.save(usuario);
    }
}
