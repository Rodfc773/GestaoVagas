package br.com.GestaoVagas.unit.candidate;


import br.com.GestaoVagas.exceptions.JobNotFoundException;
import br.com.GestaoVagas.exceptions.UserNotFoundException;
import br.com.GestaoVagas.models.ApplyJobsEntity;
import br.com.GestaoVagas.models.CandidateEntity;
import br.com.GestaoVagas.models.JobEntity;
import br.com.GestaoVagas.repositories.interfaces.ApplyJobRepository;
import br.com.GestaoVagas.repositories.interfaces.CandidateRepository;
import br.com.GestaoVagas.repositories.interfaces.JobRepository;
import br.com.GestaoVagas.services.ApplyJobCandidateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ApplyJobServiceTest {

    @InjectMocks
    private ApplyJobCandidateService applyJobCandidateService;

    @Mock
    private CandidateRepository candidateRepository;


    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply for a job if the candidate was not found")
    public void shouldNotBeAbleToApplyJobIfUserNotExist(){

        try{
            applyJobCandidateService.apply(null,null);
        } catch (Exception e) {
            Assertions.assertInstanceOf(UserNotFoundException.class, e);
        }
    }

    @Test
    @DisplayName("Should not be able to apply for a job if that job doesn't exist")
    public void shouldNotBeAbleToApplyJobIfJobNotExists(){

        var idCandidate = UUID.randomUUID();

        var candidate = new  CandidateEntity();

        candidate.setId(idCandidate);


        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try{
            applyJobCandidateService.apply(idCandidate, null);
        }catch (Exception e){

            Assertions.assertInstanceOf(JobNotFoundException.class, e);
        }

    }

   /*@Test
    public void should_be_able_to_create_a_new_apply_job(){

        var candidateId = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobsEntity.builder().candidateId(candidateId).jobId(idJob).build();


        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(new ApplyJobsEntity());

        var response = applyJobCandidateService.apply(candidateId,idJob);

        System.out.println(response);

        org.assertj.core.api.Assertions.assertThat(response).hasFieldOrProperty("id");
        Assertions.assertNotNull(response.getId());
    }*/
}
