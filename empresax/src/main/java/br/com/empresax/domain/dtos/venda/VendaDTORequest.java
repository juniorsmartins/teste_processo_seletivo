package br.com.empresax.domain.dtos.venda;

import br.com.empresax.domain.dtos.PolicyDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record VendaDTORequest
    (
        @NotNull
        @Positive
        double valor,

        @NotBlank
        @Length(max = 7)
        String mesAnoVenda,

        @NotNull
        Long vendedorId
    ) implements PolicyDTO<Long>
{ }
