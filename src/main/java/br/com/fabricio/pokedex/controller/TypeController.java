package br.com.fabricio.pokedex.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricio.pokedex.dtos.type.TypeDto;
import br.com.fabricio.pokedex.dtos.type.TypeRecordDto;
import br.com.fabricio.pokedex.services.TypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController

public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/type")
    public ResponseEntity<?> getPaged(
            @RequestParam @Min(1) Optional<Integer> page,
            @Min(1) @Max(10) @RequestParam Optional<Integer> pageSize) {

        List<TypeDto> listTypeDtos = new ArrayList<>();

        listTypeDtos = page.isPresent() && pageSize.isPresent()
                ? typeService.getPaged(page.get(), pageSize.get())
                : typeService.findAll();

        if (!listTypeDtos.isEmpty()) {
            for (TypeDto pokemonType : listTypeDtos) {
                UUID id = pokemonType.getId();
                pokemonType.add(linkTo(methodOn(TypeController.class).getById(id)).withSelfRel());
                pokemonType.add(linkTo(methodOn(TypeController.class).deletePokemonType(id)).withRel("DELETE"));
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(listTypeDtos);
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id) {

        Optional<TypeDto> pokemonType = typeService.findById(id);

        if (pokemonType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(typeService.findById(id));
    }

    /**
     * @param pokemonTypeRecordDto
     * @return
     */
    @PostMapping("/type")
    public ResponseEntity<?> Post(@RequestBody @Valid TypeRecordDto pokemonTypeRecordDto) {

        TypeDto typeCreated = typeService.create(pokemonTypeRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(typeCreated);
    }

    @PutMapping("/type/{id}")
    public ResponseEntity<?> updatePokemonType(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid TypeDto typeDto) {

        TypeDto typeUpdated = typeService.update(id, typeDto);

        return ResponseEntity.status(HttpStatus.OK).body(typeUpdated);
    }

    @DeleteMapping("/type/{id}")
    public ResponseEntity<Object> deletePokemonType(@PathVariable("id") UUID id) {

        typeService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body("Pokemon Type deleted successfully.");
    }

}
