package br.com.fabricio.pokedex.dtos;

import jakarta.validation.constraints.NotBlank;

public record PokemonTypeRecordDto(
        @NotBlank String name) {

}
