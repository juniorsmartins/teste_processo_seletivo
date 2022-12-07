package br.com.empresax.domain.dtos;

import jakarta.validation.constraints.NotBlank;
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
