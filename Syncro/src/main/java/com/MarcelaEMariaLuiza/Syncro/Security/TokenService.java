package com.MarcelaEMariaLuiza.Syncro.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.MarcelaEMariaLuiza.Syncro.Entities.Aluno;
import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
/**
 * Serviço para gerenciar a criação e validação de JSON Web Tokens (JWT).
 * <p>
 * Esta classe é responsável por gerar tokens de autenticação seguros para os
 * usuários após o login e por validar os tokens recebidos em requisições
 * para proteger os endpoints da aplicação.
 * </p>
 *
 * @author Marcela, Maria Luiza
 * @version 1.0
 * @since 2025-07-28
 */
@Service
public class TokenService {
    /**
     * Chave secreta para assinar e verificar os tokens JWT.
     * O valor é injetado a partir do arquivo de propriedades da aplicação (application.properties).
     */
    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Gera um token JWT de autenticação para um aluno.
     * <p>
     * O token é assinado com o algoritmo HMAC256 e inclui informações como
     * o emissor , o e-mail do aluno como sujeito e uma
     * data de expiração.
     * </p>
     *
     * @param aluno A entidade {@link Aluno} para a qual o token será gerado.
     * @return O token JWT como uma {@link String}.
     * @throws RuntimeException Se ocorrer um erro durante a criação do token.
     */
    public String generateToken(Aluno aluno){
        try{
            
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("login-auth-api")
            .withSubject(aluno.getEmail())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
            return token;
        
        }catch(JWTCreationException e){
           throw new RuntimeException("Erro ao criar token de autenticação");
        }
    }
    /**
     * Valida um token JWT e extrai o sujeito (e-mail do aluno).
     * <p>
     * Verifica a assinatura, o emissor e a validade do token. Se o token
     * for considerado válido, o método retorna o e-mail do usuário contido nele.
     * </p>
     *
     * @param token O token JWT a ser validado, recebido no cabeçalho da requisição.
     * @return O e-mail do aluno (sujeito do token) se o token for válido; caso contrário, retorna {@code null}.
     */
    public String validateToken(String token){
        try{
            
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
            .withIssuer("login-auth-api")
            .build()
            .verify(token)
            .getSubject();
        
        }catch(JWTVerificationException e){
           return null;
        }
    }
    /**
     * Calcula a data e hora de expiração para o token JWT.
     *
     * @return Um {@link Instant} representando o momento exato da expiração (4 horas a partir de agora).
     */
    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
