package br.com.GestaoVagas.services;

import br.com.GestaoVagas.exceptions.UserAlreadyExistsException;
import br.com.GestaoVagas.models.CandidateEntity;
import br.com.GestaoVagas.repositories.interfaces.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateService {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity create(CandidateEntity entity){

         this.repository
                 .findByUsernameOrEmail(entity.getUsername(), entity.getEmail())
                 .ifPresent((user) -> {
                     throw new UserAlreadyExistsException();
                 });

         String password = this.passwordEncoder.encode(entity.getPassword());
         entity.setPassword(password);


         return this.repository.save(entity);
    }
}
