package br.com.fabricio.pokedex.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricio.pokedex.dtos.pokemon.PokemonDto;
import br.com.fabricio.pokedex.services.PokemonService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/{controller}")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<PokemonDto> listPokemon = pokemonService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listPokemon);
    }

}
