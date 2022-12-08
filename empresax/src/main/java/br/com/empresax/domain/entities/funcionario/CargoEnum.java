package br.com.empresax.domain.entities.funcionario;

import lombok.Getter;

@Getter
public enum CargoEnum {
    SECRETARIO("Secretário", 7000D, 1000D, 1.2D),
    VENDEDOR("Vendedor", 12000D, 1800D, 1.3D),
    GERENTE("Gerente", 20000D, 3000D, 1D);

    private String nome;
    private double salarioMensal;
    private double salarioPorAnoDeServico;
    private double beneficio;

    CargoEnum(String nome, double salarioMensal, double salarioPorAnoDeServico, double beneficio) {
        this.nome = nome;
        this.salarioMensal = salarioMensal;
        this.salarioPorAnoDeServico = salarioPorAnoDeServico;
        this.beneficio = beneficio;
    }
}
