package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.entities.funcionario.CargoEnum;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record FuncionarioDTOResponse
    (
        Long id,
        String nome,
        LocalDate mesAnoAdmissao,
        CargoEnum cargo,
        double valorPago
    ) implements PolicyDTO<Long>
{
    public FuncionarioDTOResponse(Funcionario funcionario, double valorRetorno) {
        this(funcionario.getId(), funcionario.getNome(), funcionario.getMesAnoAdmissao(), funcionario.getCargo(), valorRetorno);
    }
}
