package br.com.GestaoVagas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProfileDTO {

    private String description;
    private String username;
    private UUID id;
    private String  name;
    private  String email;
}
