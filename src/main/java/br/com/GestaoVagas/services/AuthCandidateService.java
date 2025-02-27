package br.com.GestaoVagas.services;

import br.com.GestaoVagas.dto.AuthCandidateDTO;
import br.com.GestaoVagas.dto.ResponseAuthCandidateDTO;
import br.com.GestaoVagas.models.CandidateEntity;
import br.com.GestaoVagas.repositories.interfaces.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("security.token.secret.candidate")
    private String secretKey;

    public ResponseAuthCandidateDTO authenticate(AuthCandidateDTO candidateDTO) throws AuthenticationException {

        CandidateEntity candidate = this.repository.findByUsername(candidateDTO.username()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });
        var passwordMatches = this.passwordEncoder.matches(candidateDTO.password(), candidate.getPassword());

        if(!passwordMatches) {
            throw new AuthenticationException("Username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        ResponseAuthCandidateDTO authCandidateResponse = ResponseAuthCandidateDTO.builder().accessToken(token).expires_in(expiresIn.toEpochMilli()).build();

        return authCandidateResponse;
    }
}
