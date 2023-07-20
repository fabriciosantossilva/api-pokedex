package br.com.fabricio.pokedex.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fabricio.pokedex.models.Type;

public interface TypeRepository extends JpaRepository<Type, UUID> {

}
