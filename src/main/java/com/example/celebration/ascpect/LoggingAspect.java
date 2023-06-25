package com.example.celebration.ascpect;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    HttpServletRequest request;

    /**
     * Pointcut definiujący metody, które będą logowane.
     */
    @Pointcut("within(com.example.celebration..*)" +
            "&& (@annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public void pointcut() {
    }

    /**
     * Aspekt przed wykonaniem metody, loguje informacje o ścieżce, metodzie i argumentach.
     *
     * @param joinPoint punkt dołączenia (wywołanie metody)
     */
    @Before("pointcut()")
    public void logMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        Class<?>[] parameters = signature.getParameterTypes();
        try {
            logger.info("INFO BEFORE - path: {}, method: {}, arguments: {} ",
                    mapper.writeValueAsString(request.getRequestURL()),
                    request.getMethod(),
                    mapper.writeValueAsString(parameters));
        } catch (JsonProcessingException e) {
            logger.error("Error", e);
        }
    }

    /**
     * Aspekt po wykonaniu metody, loguje informacje o ścieżce, metodzie i wartości zwracanej.
     *
     * @param responseEntity odpowiedź HTTP z wartością zwracaną przez metodę
     */
    @AfterReturning(pointcut = "pointcut()", returning = "responseEntity")
    public void logMethodAfter(ResponseEntity<?> responseEntity) {
        try {
            int statusCode = responseEntity.getStatusCodeValue();
            logger.info("INFO AFTER - path: {}, method: {}, returning: {}",
                    mapper.writeValueAsString(request.getRequestURL()),
                    request.getMethod(),
                    mapper.writeValueAsString(statusCode));
        } catch (JsonProcessingException e) {
            logger.error("Error", e);
        }
    }
}
