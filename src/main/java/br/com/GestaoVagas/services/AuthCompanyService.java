package br.com.GestaoVagas.services;

import br.com.GestaoVagas.dto.AuthCompanyDTO;
import br.com.GestaoVagas.dto.ResponseAuthCompanyDTO;
import br.com.GestaoVagas.models.CompanyEntity;
import br.com.GestaoVagas.repositories.interfaces.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public ResponseAuthCompanyDTO authenticate(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{

       CompanyEntity company = this.repository.findByName(authCompanyDTO.getName()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Company doesn't exist");
                }
        );

       boolean isPasswordCorrect = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

       if(!isPasswordCorrect) throw new AuthenticationException("The Password is incorrect");

       Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
       var expires = Instant.now().plus(Duration.ofHours(5));

        var token =JWT.create().withIssuer("javagas")
                .withSubject(company.getId().toString())
                .withExpiresAt(expires)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(algorithm);

        ResponseAuthCompanyDTO authCompany = ResponseAuthCompanyDTO.builder().expires_in(expires.toEpochMilli()).access_token(token).build();
        return authCompany;
    }
}
