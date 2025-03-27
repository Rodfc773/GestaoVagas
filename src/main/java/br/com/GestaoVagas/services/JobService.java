package br.com.GestaoVagas.services;

import br.com.GestaoVagas.exceptions.JobAlreadyRegistered;
import br.com.GestaoVagas.exceptions.UserNotFoundException;
import br.com.GestaoVagas.models.JobEntity;
import br.com.GestaoVagas.repositories.interfaces.CompanyRepository;
import br.com.GestaoVagas.repositories.interfaces.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity create(JobEntity newJob){

        this.companyRepository.findById(newJob.getCompanyId()).orElseThrow(() -> {
            throw new UserNotFoundException("The company responsible for this job was not found");
        });
        this.repository.findByDescription(newJob.getDescription())
                .ifPresent((job) -> {throw new JobAlreadyRegistered();});
        return  this.repository.save(newJob);
    }
}
