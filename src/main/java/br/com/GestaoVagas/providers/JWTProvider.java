package br.com.GestaoVagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    public DecodedJWT validateToken(String token){

        String cleanedToken = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try{
            DecodedJWT tokenDecoded = JWT.require(algorithm).build().verify(cleanedToken);
            return tokenDecoded;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
