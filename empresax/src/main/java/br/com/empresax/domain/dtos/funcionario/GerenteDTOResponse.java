package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.entities.funcionario.Gerente;

import java.time.LocalDate;

public record GerenteDTOResponse
    (
        Long id,
        String nome,
        LocalDate mesAnoAdmissao
    ) implements PolicyDTO<Long>
{
    public GerenteDTOResponse(Gerente gerente) {
        this(gerente.getId(), gerente.getNome(), gerente.getMesAnoAdmissao());
    }
}
