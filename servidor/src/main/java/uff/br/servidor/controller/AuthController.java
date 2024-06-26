package uff.br.servidor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import uff.br.servidor.model.Usuario;
import uff.br.servidor.request.LoginRequestBody;
import uff.br.servidor.request.LoginResponseBody;
import uff.br.servidor.request.RegisterRequestBody;
import uff.br.servidor.service.AuthService;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseBody> login(@RequestBody LoginRequestBody loginRequestBody){
        return ResponseEntity.ok(authService.login(loginRequestBody));
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody RegisterRequestBody registerRequestBody) {
        return null;
    }

    @PostMapping("/register-deliveryman")
    public ResponseEntity<Usuario> registerDeliveryman(@RequestBody RegisterRequestBody registerRequestBody) {
        return null;
    }
}
