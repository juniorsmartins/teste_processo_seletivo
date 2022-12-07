package br.com.empresax.domain.dtos;

import br.com.empresax.domain.entities.CargoEnum;

public record GerenteDTOResponse
    (
        Long id,
        String nome,
        String mesAnoAdmissao
    ) implements PolicyDTO<Long>
{ }
