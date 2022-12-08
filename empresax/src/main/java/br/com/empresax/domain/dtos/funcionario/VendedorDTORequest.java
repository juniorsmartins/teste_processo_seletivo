package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record VendedorDTORequest
    (
        @NotBlank
        @Length(max = 50)
        String nome,

        @NotBlank
        @Length(max = 7)
        String mesAnoAdmissao
    ) implements PolicyDTO<Long>
{ }
