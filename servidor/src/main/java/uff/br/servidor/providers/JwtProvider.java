package uff.br.servidor.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JwtProvider {
    
    @Value("${security.token.secret}")
    private String secret_key;

    public String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret_key);
        try {
            var subject = JWT.require(algorithm).build().verify(token).getSubject();
            return subject;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }
    }

}
