package br.com.fabricio.pokedex.dtos.type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TypeRecordDto {

    @NotBlank(message = "Não pode ficar em branco")
    @NotNull(message = "Não pode ser nulo")
    private String name;

}
