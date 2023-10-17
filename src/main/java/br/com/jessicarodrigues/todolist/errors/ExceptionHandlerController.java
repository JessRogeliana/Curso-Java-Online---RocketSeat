package br.com.jessicarodrigues.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// Handler criada para tratativa de erros

@ControllerAdvice // Anotação spring usada para definir classes globais no momento de tratamento de exceções. Toda exceção que tiver ela vai passar pelo controller advice e se classificar em um dos nossos parametros, ele tratará esse erro. 
public class ExceptionHandlerController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());
    }
}
