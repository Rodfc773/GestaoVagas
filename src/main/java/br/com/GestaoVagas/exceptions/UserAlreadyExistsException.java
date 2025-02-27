package br.com.GestaoVagas.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(){
        super("Usuário já existe");
    }
}
