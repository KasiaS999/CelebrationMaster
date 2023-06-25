package com.example.celebration.dto;

import lombok.Data;

@Data
public class AuthenticationDTO {

    /**
     * Adres e-mail użytkownika.
     */
    private String email;

    /**
     * Hasło użytkownika.
     */
    private String password;

}
