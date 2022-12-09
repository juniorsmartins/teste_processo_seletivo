package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VendedorDTOResponse
    (
        Long id,
        String nome,
        LocalDate mesAnoAdmissao
    ) implements PolicyDTO<Long>
{
    public VendedorDTOResponse(Vendedor vendedor) {
        this(vendedor.getId(), vendedor.getNome(), vendedor.getMesAnoAdmissao());
    }
}
