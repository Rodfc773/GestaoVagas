package br.com.GestaoVagas.controllers;

import br.com.GestaoVagas.dto.AuthCandidateDTO;
import br.com.GestaoVagas.services.AuthCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateService service;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateDTO authCandidateDTO){

        try{
            var token = this.service.authenticate(authCandidateDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Message: " + e.getMessage());
        }
    }
}
