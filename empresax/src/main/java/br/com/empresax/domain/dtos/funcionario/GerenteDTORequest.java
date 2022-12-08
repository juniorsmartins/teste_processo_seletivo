package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.entities.CargoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record GerenteDTORequest
    (
        @NotBlank
        @Length(max = 50)
        String nome,

        @NotBlank
        @Length(max = 7)
        String mesAnoAdmissao
    ) implements PolicyDTO<Long>
{ }
