package dev.raniery.med.voll.api.infra.Exception;

import jakarta.persistence.EntityNotFoundException;

import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.raniery.med.voll.api.infra.Security.DadosTokenJWT;

@RestControllerAdvice
public class TratarErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DadosTokenJWT> erro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Stream<Object>> erro400(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body((e.getFieldErrors()).stream().map(erro -> new Erro(erro.getField(), erro.getDefaultMessage())));
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<Object> erroRegraDeNegocio(ValidacaoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    public record Erro(String campo, String mensagem) {

    }
}
