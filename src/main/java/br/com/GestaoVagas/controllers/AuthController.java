package br.com.GestaoVagas.controllers;

import br.com.GestaoVagas.dto.AuthCompanyDTO;
import br.com.GestaoVagas.services.AuthCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class AuthController {

    @Autowired
    private AuthCompanyService service;

    @PostMapping("/auth")
    public ResponseEntity<Object> post(@RequestBody AuthCompanyDTO authCompanyDTO){

        try{
            Object token = this.service.authenticate(authCompanyDTO);
            return ResponseEntity.ok().body(token);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
