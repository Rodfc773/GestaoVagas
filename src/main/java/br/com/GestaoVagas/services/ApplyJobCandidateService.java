package br.com.GestaoVagas.services;

import br.com.GestaoVagas.exceptions.JobNotFoundException;
import br.com.GestaoVagas.exceptions.UserNotFoundException;
import br.com.GestaoVagas.models.ApplyJobsEntity;
import br.com.GestaoVagas.repositories.interfaces.ApplyJobRepository;
import br.com.GestaoVagas.repositories.interfaces.CandidateRepository;
import br.com.GestaoVagas.repositories.interfaces.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobsEntity apply(UUID candidateId, UUID jobId){

        this.candidateRepository.findById(candidateId).orElseThrow(() -> {
           throw new UserNotFoundException("User not Found");
        });

        this.jobRepository.findById(jobId).orElseThrow(() -> {
            throw new JobNotFoundException("Job not found");
        });

        var applyjob = ApplyJobsEntity.builder().candidateId(candidateId).jobId(jobId).build();

        return applyJobRepository.save(applyjob);
    }
}
