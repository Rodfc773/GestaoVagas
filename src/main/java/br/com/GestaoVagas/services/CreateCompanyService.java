package br.com.GestaoVagas.services;

import br.com.GestaoVagas.exceptions.UserAlreadyExistsException;
import br.com.GestaoVagas.models.CompanyEntity;
import br.com.GestaoVagas.repositories.interfaces.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public CompanyEntity create(CompanyEntity company){

        this.repository.findByNameOrEmail(company.getName(), company.getEmail())
                .ifPresent((user) -> { throw new UserAlreadyExistsException(); } );

        String password = passwordEncoder.encode(company.getPassword());

        company.setPassword(password);

        return this.repository.save(company);
    }
}
