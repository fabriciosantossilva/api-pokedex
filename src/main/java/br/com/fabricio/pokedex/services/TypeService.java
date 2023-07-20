package br.com.fabricio.pokedex.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fabricio.pokedex.dtos.type.TypeDto;
import br.com.fabricio.pokedex.dtos.type.TypeRecordDto;

public interface TypeService {

    /**
     * LISTAR TODOS CADASTRADOS
     *
     * @return
     */
    List<TypeDto> findAll();

    /**
     * BUSCAR PELO ID
     *
     * @param id
     * @return
     */
    Optional<TypeDto> findById(UUID id);

    /**
     * CADASTRAR NOVO TIPO DE POKEMON
     *
     * @param pokemonTypeDto
     * @return
     */
    TypeDto create(TypeRecordDto pokemonTypeDto);

    /**
     * REMOVER DA BASE DE DADOS
     *
     * @param id
     */
    void delete(UUID id);

    /**
     * ATUALIZAR DADOS
     *
     * @param id
     * @param pokemonTypeDto
     * @return
     */
    TypeDto update(UUID id, TypeDto typeDto);

    /**
     * OBTER TYPES PAGINADO
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<TypeDto> getPaged(Integer page, Integer pageSize);

}
