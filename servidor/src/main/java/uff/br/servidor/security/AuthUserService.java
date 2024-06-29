package uff.br.servidor.security;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import uff.br.servidor.repository.UsuarioRepository;

@Service
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
            throw new RuntimeException("Cpf nao encontrado");
        }else{
            var password = this.passwordEncoder.matches(authUserDTO.getSenha_hash(), usuario.getSenha_hash());
            if(password){
                Algorithm algorithm = Algorithm.HMAC256(secret_key);
                var token = JWT.create().withIssuer("secret").withSubject(usuario.getId().toString()).sign(algorithm);
                var auth = AuthUserResponseDTO.builder().token(token).role(usuario.getRole()).build();
                return auth;   
            }else{
                throw new RuntimeException("Senha invalida");
            }
        }
    }

}
