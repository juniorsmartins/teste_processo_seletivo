package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.entities.funcionario.Gerente;

public record GerenteDTOResponse
    (
        Long id,
        String nome,
        String mesAnoAdmissao
    ) implements PolicyDTO<Long>
{
    public GerenteDTOResponse(Gerente gerente) {
        this(gerente.getId(), gerente.getNome(), gerente.getMesAnoAdmissao());
    }
}
