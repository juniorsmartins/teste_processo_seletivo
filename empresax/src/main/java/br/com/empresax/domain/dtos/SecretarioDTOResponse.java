package br.com.empresax.domain.dtos;

public record SecretarioDTOResponse
    (
        Long id,
        String nome,
        String mesAnoAdmissao
    ) implements PolicyDTO<Long>
{ }
