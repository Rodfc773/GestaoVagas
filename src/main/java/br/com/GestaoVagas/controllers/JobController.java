package br.com.GestaoVagas.controllers;

import br.com.GestaoVagas.dto.CreateJobDTO;
import br.com.GestaoVagas.models.JobEntity;
import br.com.GestaoVagas.services.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/company/jobs")
public class JobController {
    @Autowired
    private JobService service;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO newJobDTO, HttpServletRequest request){

        try{
            var companyId = request.getAttribute("company_id");

           JobEntity newJob =  JobEntity.builder()
                   .level(newJobDTO.getLevel())
                   .description(newJobDTO.getDescription())
                   .benefits(newJobDTO.getBenefits())
                   .build();

           System.out.println(companyId);
            newJob.setCompanyId(UUID.fromString(companyId.toString()));
            Object resultOperation = this.service.create(newJob);

            return ResponseEntity.ok().body(resultOperation);

        }catch (Exception e){

            Map<String, String> erroResponse = new HashMap<>();

            erroResponse.put("Code", "400");
            erroResponse.put("Message", e.getMessage());
            return ResponseEntity.badRequest().body(erroResponse);
        }
    }
}
