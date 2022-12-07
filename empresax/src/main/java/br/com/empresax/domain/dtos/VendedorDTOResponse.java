package br.com.empresax.domain.dtos;

public record VendedorDTOResponse
    (
        Long id,
        String nome,
        String mesAnoAdmissao
    ) implements PolicyDTO<Long>
{ }
