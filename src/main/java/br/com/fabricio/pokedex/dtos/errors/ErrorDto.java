package br.com.fabricio.pokedex.dtos.errors;

import java.util.Map;

import lombok.Data;

@Data

public class ErrorDto {
    String message;
    Map<String, String> errors;
    String stack;
}