package com.example.celebration.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.example.celebration.dto.UserDTO;
import com.example.celebration.services.jwt.UserDetailsServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    private String secretKey = "secret-key";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    /**
     * Inicjalizuje komponent.
     * Dokonuje kodowania klucza tajnego (secret key) za pomocą Base64.
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Tworzy token JWT dla podanego użytkownika.
     *
     * @param name nazwa użytkownika (issuer) do umieszczenia w tokenie
     * @return wygenerowany token JWT
     */
    public String createToken(String name) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(name)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    /**
     * Sprawdza i weryfikuje poprawność podanego tokena JWT.
     * Jeśli token jest poprawny, zwraca obiekt UsernamePasswordAuthenticationToken
     * zawierający zautoryzowanego użytkownika.
     *
     * @param token token JWT do zweryfikowania
     * @return obiekt UsernamePasswordAuthenticationToken z zautoryzowanym użytkownikiem
     */
    public UsernamePasswordAuthenticationToken validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);
        UserDetails user = userDetailsService.loadUserByUsername(decoded.getIssuer());

        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}