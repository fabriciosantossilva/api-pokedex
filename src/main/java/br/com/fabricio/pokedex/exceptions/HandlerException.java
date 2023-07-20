package br.com.fabricio.pokedex.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fabricio.pokedex.dtos.errors.ErrorDto;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleGlobalException(Exception ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Ocorreu um erro no servidor.");

        return ResponseEntity.status(500).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();

        Map<String, String> errors = new HashMap<>();

        ErrorDto errorDto = new ErrorDto();

        if (bindingResult.hasErrors()) {

            List<FieldError> listFieldErrors = bindingResult.getFieldErrors();
            FieldError firstError = bindingResult.getFieldErrors().get(0);

            for (FieldError fieldError : listFieldErrors) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            errorDto.setErrors(errors);
            errorDto.setMessage(String.format("%$1s : %$2s", firstError.getField(), firstError.getDefaultMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
