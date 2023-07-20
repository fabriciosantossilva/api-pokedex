package br.com.fabricio.pokedex.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fabricio.pokedex.dtos.type.TypeDto;
import br.com.fabricio.pokedex.dtos.type.TypeRecordDto;
import br.com.fabricio.pokedex.models.Type;
import br.com.fabricio.pokedex.repositories.TypeRepository;
import br.com.fabricio.pokedex.services.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Override
    public List<TypeDto> findAll() {
        return typeRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TypeDto> getPaged(Integer page, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        Page<Type> pageTypes = typeRepository.findAll(pageRequest);

        return pageTypes.map(this::toDTO).getContent();

    }

    @Override
    public Optional<TypeDto> findById(UUID id) {

        Optional<Type> typeOptional = typeRepository.findById(id);

        return typeOptional.map(this::toDTO);
    }

    @Override
    public TypeDto create(TypeRecordDto typeDto) {

        Type typeCreated = typeRepository.save(this.toEntity(typeDto));

        return this.toDTO(typeCreated);
    }

    @Override
    public TypeDto update(UUID id, TypeDto typeDto) {

        Optional<Type> typeOptional = typeRepository.findById(id);

        if (typeOptional.isEmpty()) {
            typeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found"));
        }

        Type typeSaved = typeRepository.save(toEntity(typeDto, typeOptional));

        return toDTO(typeSaved);
    }

    @Override
    public void delete(UUID id) {

        Optional<Type> typeOptional = typeRepository.findById(id);

        if (typeOptional.isEmpty()) {
            typeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Type not found"));
        }
        typeRepository.deleteById(id);
    }

    private TypeDto toDTO(Type type) {
        TypeDto typeDto = new TypeDto();

        BeanUtils.copyProperties(type, typeDto);
        return typeDto;
    }

    private Type toEntity(TypeRecordDto typeDto) {
        Type type = new Type();

        BeanUtils.copyProperties(typeDto, type);
        return type;
    }

    private Type toEntity(TypeDto typeDto, Optional<Type> typeOptional) {
        Type type = typeOptional.get();

        BeanUtils.copyProperties(typeDto, type);
        return type;
    }

}
