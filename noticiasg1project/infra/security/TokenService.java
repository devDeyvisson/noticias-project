package br.com.ifs.noticiasg1project.infra.security;

import br.com.ifs.noticiasg1project.model.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

// Classe responsável por gerar os tokens para os usuários e validar os tokens que são passados pelo usuário!
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioModel usuario) {
        try {
            //Definição do algoritmo de criptografia
            var algorithm = Algorithm.HMAC256(secret); //Algoritmo de geração de token
            return JWT.create()
                    .withIssuer("noticias-api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch(JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token!", exception);
        }
    }

    //Método responsável por validar tokens
    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("noticias-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido ou expirado!");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
