package br.com.GestaoVagas.services;

import br.com.GestaoVagas.models.JobEntity;
import br.com.GestaoVagas.repositories.interfaces.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilter {

    @Autowired
    private JobRepository repository;

    public List<JobEntity> execute(String filter){
        return this.repository.findByDescriptionContaining(filter);
    }
}
