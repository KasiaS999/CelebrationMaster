package com.example.celebration.utils;

import com.example.celebration.dto.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    /**
     * Obsługuje wejście do punktu uwierzytelniania.
     * Ustawia odpowiedni status i nagłówki odpowiedzi oraz zapisuje informacje o błędzie jako JSON.
     *
     * @param request       obiekt HttpServletRequest
     * @param response      obiekt HttpServletResponse
     * @param authException wyjątek autentykacji
     * @throws IOException      w przypadku błędu podczas zapisywania danych JSON do odpowiedzi
     * @throws ServletException w przypadku wystąpienia błędu serwletu
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        OBJECT_MAPPER.writeValue(response.getOutputStream(), new ErrorDTO("Unauthorized path"));
    }
}