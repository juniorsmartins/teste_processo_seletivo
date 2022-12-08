package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.entities.funcionario.Vendedor;

public record VendedorDTOResponse
    (
        Long id,
        String nome,
        String mesAnoAdmissao
    ) implements PolicyDTO<Long>
{
    public VendedorDTOResponse(Vendedor vendedor) {
        this(vendedor.getId(), vendedor.getNome(), vendedor.getMesAnoAdmissao());
    }
}
