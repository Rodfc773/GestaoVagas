package br.com.GestaoVagas.exceptions;

public class JobAlreadyRegistered extends RuntimeException {
    public JobAlreadyRegistered() {
        super("Essa vaga ja foi registrado no portal de vagas");
    }
}
