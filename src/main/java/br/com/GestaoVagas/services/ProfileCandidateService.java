package br.com.GestaoVagas.services;

import br.com.GestaoVagas.dto.CandidateProfileDTO;
import br.com.GestaoVagas.models.CandidateEntity;
import br.com.GestaoVagas.repositories.interfaces.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateProfileDTO execute(UUID idCandidate){

        CandidateEntity candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> { throw new UsernameNotFoundException("User not Found");
                });

        CandidateProfileDTO candidateProfileDTO= CandidateProfileDTO.builder().
                name(candidate.getName()).
                id(candidate.getId()).
                description(candidate.getBiography()).
                username(candidate.getUsername()).
                email(candidate.getEmail()).
                build();

        return candidateProfileDTO;
    }
}
