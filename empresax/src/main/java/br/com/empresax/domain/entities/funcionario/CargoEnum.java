package br.com.empresax.domain.entities.funcionario;

import br.com.empresax.domain.entities.Beneficiario;
import br.com.empresax.domain.entities.venda.Venda;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

    public double calcularPagamentoTotal(Funcionario funcionario, LocalDate dataPesquisada, List<Venda> vendas) {

        if((funcionario.getMesAnoAdmissao().getYear() < dataPesquisada.getYear())
                || (funcionario.getMesAnoAdmissao().getYear() == dataPesquisada.getYear()
                && funcionario.getMesAnoAdmissao().getMonthValue() <= dataPesquisada.getMonthValue())) {

            var salarioMaisValorPorAnosDeServico = calcularPagamentoDeSalario(funcionario, dataPesquisada);

            double valorDoBeneficio = 0;
            if(funcionario instanceof Beneficiario) {
                var beneficiario = (Beneficiario) funcionario;
                valorDoBeneficio = calcularPagamentoDeBeneficio(beneficiario, dataPesquisada, vendas);
                return salarioMaisValorPorAnosDeServico + valorDoBeneficio;
            }
            return salarioMaisValorPorAnosDeServico;
        }
        return 0;
    }

    public double calcularPagamentoDeSalario(Funcionario funcionario, LocalDate dataPesquisada) {

        if((funcionario.getMesAnoAdmissao().getYear() < dataPesquisada.getYear())
                || (funcionario.getMesAnoAdmissao().getYear() == dataPesquisada.getYear()
                && funcionario.getMesAnoAdmissao().getMonthValue() <= dataPesquisada.getMonthValue())) {

            var anosDeServico = ChronoUnit.YEARS.between(funcionario.getMesAnoAdmissao(), dataPesquisada);
            var valorPorAnosDeServico = anosDeServico * this.getSalarioPorAnoDeServico();
            var salarioMaisValorPorAnosDeServico = this.getSalarioMensal() + valorPorAnosDeServico;
            return salarioMaisValorPorAnosDeServico;
        }
        return 0;
    }

    public double calcularPagamentoDeBeneficio(Beneficiario beneficiario, LocalDate dataPesquisada, List<Venda> vendas) {

        if((beneficiario.getMesAnoAdmissao().getYear() < dataPesquisada.getYear())
                || (beneficiario.getMesAnoAdmissao().getYear() == dataPesquisada.getYear()
                && beneficiario.getMesAnoAdmissao().getMonthValue() <= dataPesquisada.getMonthValue())) {

            if(beneficiario instanceof Vendedor) {
                var valorTotalVendas = 0d;
                for (Venda venda : vendas) {
                    if(beneficiario.getId() == venda.getVendedor().getId()) {
                        if(venda.getDataVenda().getMonthValue() == dataPesquisada.getMonthValue()
                                && venda.getDataVenda().getYear() == dataPesquisada.getYear()) {
                            valorTotalVendas += venda.getValor();
                        }
                    }
                }
                return ((valorTotalVendas * this.getBeneficio()) - valorTotalVendas);
            }
            else {
                var anosDeServico = ChronoUnit.YEARS.between(beneficiario.getMesAnoAdmissao(), dataPesquisada);
                var valorPorAnosDeServico = anosDeServico * this.getSalarioPorAnoDeServico();
                var salarioMaisValorPorAnosDeServico = this.getSalarioMensal() + valorPorAnosDeServico;
                return ((salarioMaisValorPorAnosDeServico * this.getBeneficio()) - salarioMaisValorPorAnosDeServico);
            }
        }
        return 0;
    }
}
