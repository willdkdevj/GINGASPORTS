package br.com.supernova.tech.gingasports.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.List;

@RestControllerAdvice
public class TreatmentErrors {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity treatment400(MethodArgumentNotValidException mEx){
        List<FieldError> fieldErrors = mEx.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream().map(ValidationError::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatment404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity treatment400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity treatmentBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity treatmentAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity treatmentAcessoNegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity treatment500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }

    @ExceptionHandler(QueryException.class)
    public ResponseEntity treatmentSQL(Exception ex) {
        return ResponseEntity.badRequest().body("Erro: " + ex.getMessage());
    }

    private record ValidationError(String field, String message){
        public ValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
