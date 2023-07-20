package br.com.fabricio.pokedex.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fabricio.pokedex.models.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, UUID> {

}
