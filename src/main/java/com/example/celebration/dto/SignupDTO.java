package com.example.celebration.dto;

import lombok.Data;

@Data
public class SignupDTO {

    /**
     * Nazwa użytkownika.
     */
    private String name;

    /**
     * Adres email użytkownika.
     */
    private String email;

    /**
     * Hasło użytkownika.
     */
    private String password;

}

