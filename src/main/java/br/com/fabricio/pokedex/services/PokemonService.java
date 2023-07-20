package br.com.fabricio.pokedex.services;

import java.util.List;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import br.com.fabricio.pokedex.dtos.pokemon.PokemonDto;

public interface PokemonService {
    PokemonDto findById(UUID id);

    PokemonDto findByName(String name);

    List<PokemonDto> findAll();

    List<PokemonDto> findAllPageable(Pageable pageable);

    PokemonDto create(PokemonDto pokemonDto);

    PokemonDto update(PokemonDto pokemonDto);

    void delete(PokemonDto pokemonDto);

}
