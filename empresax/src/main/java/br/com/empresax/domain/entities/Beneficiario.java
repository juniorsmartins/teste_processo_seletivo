package br.com.empresax.domain.entities;

import br.com.empresax.domain.entities.funcionario.CargoEnum;

import java.time.LocalDate;

public interface Beneficiario {

    LocalDate getMesAnoAdmissao();
    CargoEnum getCargo();
}
