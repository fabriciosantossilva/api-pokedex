package br.com.fabricio.pokedex.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fabricio.pokedex.models.PokemonType;

public interface PokemonTypeRepository extends JpaRepository<PokemonType, UUID> {
    // @Query("SELECT * FROM TB_POKEMON_TYPE as t WHERE lower(t.name) = lower(:name)")
    // Optional<PokemonType> findByNameIgnoreCase(@Param("name") String name);
}
