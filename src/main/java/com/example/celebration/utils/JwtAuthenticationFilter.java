package com.example.celebration.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserAuthenticationProvider userAuthenticationProvider;

    /**
     * Filtruje żądanie HTTP i weryfikuje token JWT w nagłówku uwierzytelniania.
     * Jeśli token jest prawidłowy, ustawia kontekst uwierzytelnienia w SecurityContextHolder.
     *
     * @param request      obiekt HttpServletRequest
     * @param response     obiekt HttpServletResponse
     * @param filterChain  obiekt FilterChain
     * @throws ServletException w przypadku wystąpienia błędu serwletu
     * @throws IOException      w przypadku wystąpienia błędu wejścia/wyjścia
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
            filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            String[] authElements = header.split(" ");

            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthenticationProvider.validateToken(authElements[1]));
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

}