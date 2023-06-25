package com.example.celebration.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorDTO {
    /**
     * Wiadomość błędu.
     */
    private String message;
}
