package br.com.fabioporto.cashback.exception;

public class UserExistsExcetion extends RuntimeException {
    
    public UserExistsExcetion() {
        super("Já existe um usuário com esse login.");
    }
}
