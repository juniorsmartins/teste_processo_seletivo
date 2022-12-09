package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import br.com.empresax.domain.entities.Beneficiario;
import br.com.empresax.domain.entities.funcionario.CargoEnum;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public FuncionarioDTOResponse(Beneficiario beneficiario, double valorRetorno) {
        this(beneficiario.getId(), beneficiario.getNome(), beneficiario.getMesAnoAdmissao(), beneficiario.getCargo(), valorRetorno);
    }
}
