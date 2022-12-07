package br.com.empresax.domain.service;

import br.com.empresax.domain.entities.Beneficiario;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import br.com.empresax.domain.entities.funcionario.Vendedor;

import java.util.List;

public final class Calculadora {

    public double calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(
            List<Funcionario> funcionarios, String mesAno) {
        return 0;
    }

    public double calcularPagamentoTotalDeSalariosDaListaDeFuncionariosNoMesAnoEspecificado(
            List<Funcionario> funcionarios, String mesAno) {
        return 0;
    }

    public double calcularPagamentoTotalDeBeneficiosDaListaDeBeneficiariosNoMesAnoEspecificado(
            List<Beneficiario> beneficiarios, String mesAno) {
        return 0;
    }

    public Funcionario encontrarMaiorPagamentoTotalDeSalarioAndBeneficioDalistaDeFuncionariosNoMesAnoEspecificado(
            List<Funcionario> funcionarios, String mesAno) {
        return null;
    }

    public String encontrarNomeDeQuemRecebeuMaiorPagamentoDeBeneficioDaListaDeBeneficiariosNoMesAnoEspecificado(
            List<Beneficiario> beneficiarios, String mesAno) {
        return null;
    }

    public Vendedor encontrarMaiorValorDeVendasDaListaDeVendedoresNoMesAnoEspecificado(
            List<Vendedor> vendedores, String mesAno) {
        return null;
    }
}

