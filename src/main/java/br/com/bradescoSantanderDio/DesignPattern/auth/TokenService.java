package br.com.bradescoSantanderDio.DesignPattern.auth; // Ajuste para o pacote que escolheu

import br.com.bradescoSantanderDio.DesignPattern.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;


@Service
public class TokenService {

    // Lê aquela senha que colocamos no application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    // Método 1: Cria um token novo quando o usuário acerta o login
    public String gerarToken(Usuario usuario) {
        try {
            // Define o algoritmo de criptografia e usa a nossa senha secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("api-ecommerce") // O nome do "emissor" do token (quem criou)
                    .withSubject(usuario.getUsername()) // Quem é o dono desse token (o login/email)
                    .withExpiresAt(gerarDataExpiracao()) // Quando o token vence
                    .sign(algorithm); // Assina criptografado

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    // Método 2: Descriptografa e valida o token que vem no Header do Postman
    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("api-ecommerce") // Verifica se fomos nós que emitimos
                    .build()
                    .verify(token) // Verifica a validade e a criptografia
                    .getSubject(); // Devolve o dono do token (o login/email)

        } catch (JWTVerificationException exception) {
            // Se o token for inválido, falso, ou expirado, devolve uma string vazia (barra o usuário)
            return "";
        }
    }

    // Método auxiliar: O token dura 2 horas a partir da hora de criação.
    private Instant gerarDataExpiracao() {
        return OffsetDateTime.now().plusHours(2).toInstant();
    }
}