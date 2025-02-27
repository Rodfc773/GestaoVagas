package br.com.GestaoVagas.services;

import br.com.GestaoVagas.exceptions.JobAlreadyRegistered;
import br.com.GestaoVagas.models.JobEntity;
import br.com.GestaoVagas.repositories.interfaces.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    public JobEntity create(JobEntity newJob){

        this.repository.findByDescription(newJob.getDescription())
                .ifPresent((job) -> {throw new JobAlreadyRegistered();});
        return  this.repository.save(newJob);
    }
}
