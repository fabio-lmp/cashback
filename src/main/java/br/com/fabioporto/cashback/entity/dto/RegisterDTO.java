package br.com.fabioporto.cashback.entity.dto;

import br.com.fabioporto.cashback.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
    
}
