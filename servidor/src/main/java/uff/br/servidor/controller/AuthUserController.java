package uff.br.servidor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import uff.br.servidor.dto.AuthUserDTO;
import uff.br.servidor.dto.RegisterUserDTO;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.security.AuthUserResponseDTO;
import uff.br.servidor.service.AuthUserService;
import uff.br.servidor.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthUserController {
    private AuthUserService authUserService;
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Object>  login(@RequestBody AuthUserDTO entity) {
        try {
            var result = authUserService.login(entity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    
    
    @PostMapping("/registrar")
    public ResponseEntity<Object> salvarUsuario(@RequestBody RegisterUserDTO usuario) {
        // try {
            Usuario usuarioSalvo = authUserService.registrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
        // } catch (Exception e) {
        //     return ResponseEntity.status(422).body(e.getMessage());
        // }
    }
}
