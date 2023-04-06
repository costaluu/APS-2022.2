package com.ufpe.aps.exception;

import com.ufpe.aps.pojo.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccountAlreadyRegisteredException.class)
    public ResponseEntity<ExceptionDTO> contaAlreadyRegistered(AccountAlreadyRegisteredException e){

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ExceptionDTO(HttpStatus.FORBIDDEN, e.getMessage())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDTO> illegalArgument(IllegalArgumentException e){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ExceptionDTO(HttpStatus.UNAUTHORIZED, e.getMessage())
        );
    }

    @ExceptionHandler({AccountNotFoundException.class, ProdutoNotFoundException.class})
    public ResponseEntity<ExceptionDTO> accountNotFound(AccountNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDTO(HttpStatus.NOT_FOUND, e.getMessage())
        );
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ExceptionDTO> serverError(ServerErrorException e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())
        );
    }
}
