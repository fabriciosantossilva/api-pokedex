package br.com.fabricio.pokedex.models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_POKEMONS")
@Data
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String photo;
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
