package br.com.fabricio.pokedex.dtos.type;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TypeDto extends RepresentationModel<TypeDto> {

    UUID id;

    @NotBlank(message = "Não pode ficar em branco")
    @NotNull(message = "Não pode ser nulo")
    String name;

}
