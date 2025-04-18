package br.com.GestaoVagas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAuthCompanyDTO {

    private String access_token;
    private Long expires_in;
}
