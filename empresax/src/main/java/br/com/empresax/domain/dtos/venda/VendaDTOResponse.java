package br.com.empresax.domain.dtos.venda;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import br.com.empresax.domain.entities.venda.Venda;

import java.time.LocalDate;

public record VendaDTOResponse
    (
        Long id,
        double valor,
        LocalDate dataVenda,
        VendedorDTOResponse vendedor
    ) implements PolicyDTO<Long>
{
    public VendaDTOResponse(Venda venda) {
        this(venda.getId(), venda.getValor(), venda.getDataVenda(), new VendedorDTOResponse(venda.getVendedor()));
    }
}
