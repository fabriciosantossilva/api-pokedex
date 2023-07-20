package br.com.fabricio.pokedex.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import br.com.fabricio.pokedex.dtos.pokemon.PokemonDto;
import br.com.fabricio.pokedex.models.Pokemon;
import br.com.fabricio.pokedex.repositories.PokemonRepository;
import br.com.fabricio.pokedex.services.PokemonService;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Override
    public PokemonDto findById(UUID id) {

        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        if (pokemon.isEmpty()) {
            throw new RuntimeException("Pokemon not found");
        }
        PokemonDto pokemonDto = new PokemonDto();

        BeanUtils.copyProperties(pokemonDto, pokemon);

        return pokemonDto;
    }

    @Override
    public PokemonDto findByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    @Override
    public List<PokemonDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<PokemonDto> findAllPageable(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllPageable'");
    }

    @Override
    public PokemonDto create(PokemonDto pokemonDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public PokemonDto update(PokemonDto pokemonDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(PokemonDto pokemonDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
