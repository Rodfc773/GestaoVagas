package br.com.GestaoVagas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

    private String name;
    private String password;
    private String email;
}
