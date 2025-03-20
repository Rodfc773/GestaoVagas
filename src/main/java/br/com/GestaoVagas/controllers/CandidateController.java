package br.com.GestaoVagas.controllers;


import br.com.GestaoVagas.models.ApplyJobsEntity;
import br.com.GestaoVagas.models.CandidateEntity;
import br.com.GestaoVagas.models.JobEntity;
import br.com.GestaoVagas.services.ApplyJobCandidateService;
import br.com.GestaoVagas.services.CreateCandidateService;
import br.com.GestaoVagas.services.ListAllJobsByFilter;
import br.com.GestaoVagas.services.ProfileCandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateService candidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListAllJobsByFilter jobFilterService;

    @Autowired
    private ApplyJobCandidateService applyJobCandidateService;

    @PostMapping("/")
    @Operation(summary = "Cadastro do candidato", description = "Rota responsável pelo cadastro do candidato na base de dados")
            @ApiResponses({
                    @ApiResponse(
                            responseCode  = "200",
                            content = {
                                    @Content(schema = @Schema(implementation = CandidateEntity.class))
                            }
                    ),
                    @ApiResponse(responseCode = "400", description = "User already exist")
            })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

        try{
            Object result = this.candidateService.create(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/config")
    @PreAuthorize("hasRole('candidate')")
    public ResponseEntity<Object> get(HttpServletRequest request){

        var idCandidate =  request.getAttribute("candidate_id");
        try{
            var profile = this.profileCandidateService.execute(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('candidate')")
    @Operation(summary = "Listagem de vagas para o candidato", description = "Responsável por listar as vagas disponiveis para os candidatos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "lista de vagas disponivel para o usuário", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> findJobByFilter(@RequestParam String filter){
        try{
            var listJobs = this.jobFilterService.execute(filter);

            return ResponseEntity.status(HttpStatus.OK).body(listJobs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: " + e.getMessage());
        }
    }

    @PostMapping("/job/apply")
    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Aplicação de vaga pelo candidato autenticado", description = "Rota para aplicação de vaga")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Aplicação de um candidato a uma vaga", content = {
                    @Content(schema = @Schema(implementation = ApplyJobsEntity.class))
            }),
            @ApiResponse(responseCode = "404", description = "Quando a vaga ou o usuário não existe")
    })
    public ResponseEntity<Object> ApplyJob(HttpServletRequest request, @RequestBody UUID jobId){
        var candidateId = UUID.fromString(request.getAttribute("candidate_id").toString());

       try{
           var result = applyJobCandidateService.apply(candidateId, jobId);

           Map<String, Object> successMessage = new HashMap<>();

           successMessage.put("status_code", "201");
           successMessage.put("message", "the action was accomplished");
           successMessage.put("response", result);

           return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
       } catch (Exception e) {

           Map<String, String> errorMessage = new HashMap<>();

           errorMessage.put("status_code", "404");
           errorMessage.put("message", e.getMessage());

           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
       }
    }
}
