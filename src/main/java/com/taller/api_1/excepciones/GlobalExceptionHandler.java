package com.taller.api_1.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarArgumentosInvalidos(IllegalArgumentException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", "Petición incorrecta");
        respuesta.put("mensaje", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta); // Devuelve 400
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> manejarRecursoNoEncontrado(NoResourceFoundException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", "Recurso no encontrado");
        respuesta.put("mensaje", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta); // Devuelve 404
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarErroresInesperados(Exception ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", "Error interno del servidor");
        respuesta.put("mensaje", "Ocurrió un problema inesperado. Por favor, intente más tarde.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta); // Devuelve 500
    }
}
