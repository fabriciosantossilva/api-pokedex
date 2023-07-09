package br.com.fabricio.pokedex.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricio.pokedex.dtos.PokemonTypeRecordDto;
import br.com.fabricio.pokedex.models.PokemonType;
import br.com.fabricio.pokedex.repositories.PokemonTypeRepository;
import br.com.fabricio.pokedex.services.PokemonTypeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController

public class PokemonTypeController {

    @Autowired
    PokemonTypeService pokemonTypeService;

    @Autowired
    PokemonTypeRepository pokemonTypeRepository;

    @GetMapping("/pokemon_type")
    public ResponseEntity<List<PokemonType>> Get() {

        List<PokemonType> listPokemonTypes = pokemonTypeRepository.findAll();

        if (!listPokemonTypes.isEmpty()) {
            for (PokemonType pokemonType : listPokemonTypes) {
                UUID id = pokemonType.getId();

                pokemonType.add(linkTo(methodOn(PokemonTypeController.class).getById(id)).withSelfRel());
                pokemonType.add(linkTo(methodOn(PokemonTypeController.class).deletePokemonType(id)).withRel("DELETE"));
                pokemonType.add(linkTo(methodOn(PokemonTypeController.class).updatePokemonType(id, null)).withRel("PUT"));
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(pokemonTypeRepository.findAll());
    }

    @GetMapping("/pokemon_type/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id) {

        Optional<PokemonType> pokemonType = pokemonTypeRepository.findById(id);

        if (pokemonType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon Type not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pokemonTypeRepository.findById(id));
    }

    /**
     * @param pokemonTypeRecordDto
     * @return
     */
    @PostMapping("/pokemon_type")
    public ResponseEntity<Object> Post(@RequestBody @Valid PokemonTypeRecordDto pokemonTypeRecordDto) {
        PokemonType pokemonType = new PokemonType();

        // if
        // (pokemonTypeRepository.findByNameIgnoreCase(pokemonTypeRecordDto.name()).isPresent())
        // {
        // return ResponseEntity.badRequest().body("This type already exists");
        // }

        BeanUtils.copyProperties(pokemonTypeRecordDto, pokemonType);

        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonTypeRepository.save(pokemonType));
    }

    @PutMapping("/pokemon_type/{id}")
    public ResponseEntity<Object> updatePokemonType(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid PokemonTypeRecordDto pokemonTypeRecordDto) {

        Optional<PokemonType> pokemonTypeOptional = pokemonTypeRepository.findById(id);

        if (pokemonTypeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon Type not found");
        }
        PokemonType pokemonType = pokemonTypeOptional.get();

        BeanUtils.copyProperties(pokemonTypeRecordDto, pokemonType);

        return ResponseEntity.status(HttpStatus.OK).body(pokemonTypeRepository.save(pokemonType));
    }

    @DeleteMapping("/pokemon_type/{id}")
    public ResponseEntity<Object> deletePokemonType(@PathVariable("id") UUID id) {
        Optional<PokemonType> pokemonTypeOptional = pokemonTypeRepository.findById(id);

        if (pokemonTypeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon Type not found");
        }

        pokemonTypeRepository.delete(pokemonTypeOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Pokemon Type deleted successfully.");
    }

}
