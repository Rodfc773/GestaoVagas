package br.com.GestaoVagas.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity(name = "candidate")
public class CandidateEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O nome de usuário não pode ter espaços")
    private String username;

    @Email(message = "O campo deve conter um email válido")
    private String email;

    @Size(min = 6, max=100, message = "A senha deve ter entre 6 á 20 caracteres")
    private String password;
    private String biography;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime created_at;

    @CreationTimestamp
    private LocalDateTime updated_at;


}
