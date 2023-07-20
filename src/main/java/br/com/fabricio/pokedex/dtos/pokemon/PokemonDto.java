package br.com.fabricio.pokedex.dtos.pokemon;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PokemonDto {
    private UUID id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String photo;
    @NotNull
    @NotBlank
    private String description;
    private List<String> pokemonType;
    private Double hp;
    private Double attack;
    private Double defense;
    private Double specialAttack;
    private Double specialDefense;
    private Double speed;
    private List<String> weaknesses;
}