package br.com.empresax.domain.dtos.venda;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import br.com.empresax.domain.entities.venda.Venda;

public record VendaDTOResponse
    (
        Long id,
        double valor,
        String mesAnoVenda,
        VendedorDTOResponse vendedor
    ) implements PolicyDTO<Long>
{
    public VendaDTOResponse(Venda venda) {
        this(venda.getId(), venda.getValor(), venda.getMesAnoVenda(), new VendedorDTOResponse(venda.getVendedor()));
    }
}
