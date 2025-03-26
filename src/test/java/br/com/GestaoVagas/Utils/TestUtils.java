package br.com.GestaoVagas.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtils {

    public static String ObjectToJSON(Object obj){

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException("Erro na conversão do JSON");
        }

    }

    public static String generateToken(UUID companyId, String secretKey){

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expires = Instant.now().plus( Duration.ofHours(2));

        var token =JWT.create().withIssuer("javagas")
                .withSubject(companyId.toString())
                .withExpiresAt(expires)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return token;
    }
}
