package br.com.GestaoVagas.controllers;

import br.com.GestaoVagas.models.CompanyEntity;
import br.com.GestaoVagas.services.CreateCompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyService service;

    @PostMapping("/")
    public ResponseEntity<Object> create (@Valid @RequestBody CompanyEntity newComapny){

        try{
            Object resultOperation = this.service.create(newComapny);

            return ResponseEntity.ok().body(resultOperation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
