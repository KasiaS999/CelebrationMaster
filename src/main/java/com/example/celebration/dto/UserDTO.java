package com.example.celebration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    /**
     * Identyfikator użytkownika.
     */
    private Long id;

    /**
     * Nazwa użytkownika.
     */
    private String name;

    /**
     * Adres email użytkownika.
     */
    private String email;

    /**
     * Token uwierzytelniający użytkownika.
     */
    private String token;

}
