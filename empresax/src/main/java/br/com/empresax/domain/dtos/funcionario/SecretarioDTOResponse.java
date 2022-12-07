package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.entities.funcionario.Secretario;

public record SecretarioDTOResponse
    (
        Long id,
        String nome,
        String mesAnoAdmissao
    ) implements PolicyDTO<Long>
{
    public SecretarioDTOResponse(Secretario secretario) {
        this(secretario.getId(), secretario.getNome(), secretario.getMesAnoAdmissao());
    }
}
