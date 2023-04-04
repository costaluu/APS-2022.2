package com.ufpe.aps.exception;

import com.ufpe.aps.pojo.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<ExceptionDTO> produtoNotFoundException(ProdutoNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDTO(HttpStatus.NOT_FOUND, e.getMessage())
        );
    }

    @ExceptionHandler(QuantidadeProdutoException.class)
    public ResponseEntity<ExceptionDTO> quantidadeProdutoException(QuantidadeProdutoException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }
}
