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

        if(funcionario.getMesAnoAdmissao().isBefore(dataPesquisada)) {
            var anosDeServico = ChronoUnit.YEARS.between(funcionario.getMesAnoAdmissao(), dataPesquisada);
            var valorPorAnosDeServico = anosDeServico * this.getSalarioPorAnoDeServico();
            var salarioMaisValorPorAnosDeServico = this.getSalarioMensal() + valorPorAnosDeServico;

            if(funcionario.getCargo().getNome().equalsIgnoreCase(CargoEnum.GERENTE.getNome())) {
                return salarioMaisValorPorAnosDeServico * this.getBeneficio();
            } else if(funcionario.getCargo().getNome().equalsIgnoreCase(CargoEnum.SECRETARIO.getNome())) {
                return salarioMaisValorPorAnosDeServico * this.getBeneficio();
            } else if(funcionario.getCargo().getNome().equalsIgnoreCase(CargoEnum.VENDEDOR.getNome())) {
                var valorTotalVendas = 0d;
                for (Venda venda : vendas) {
                    if(funcionario.getId() == venda.getVendedor().getId()) {
                        if(venda.getDataVenda().getMonthValue() == dataPesquisada.getMonthValue()
                                && venda.getDataVenda().getYear() == dataPesquisada.getYear()) {
                            valorTotalVendas += venda.getValor();
                        }
                    }
                }
                var valorDoBeneficioSobreValorVendido = ((valorTotalVendas * this.getBeneficio()) - valorTotalVendas);
                return salarioMaisValorPorAnosDeServico + valorDoBeneficioSobreValorVendido;
            }
        }
        return 0;
    }

    public double calcularPagamentoDeSalario(Funcionario funcionario, LocalDate dataPesquisada) {

        if(funcionario.getMesAnoAdmissao().isBefore(dataPesquisada)) {
            var anosDeServico = ChronoUnit.YEARS.between(funcionario.getMesAnoAdmissao(), dataPesquisada);
            var valorPorAnosDeServico = anosDeServico * this.getSalarioPorAnoDeServico();
            var salarioMaisValorPorAnosDeServico = this.getSalarioMensal() + valorPorAnosDeServico;
            return salarioMaisValorPorAnosDeServico;
        }
        return 0;
    }

    public double calcularPagamentoDeBeneficio(Beneficiario beneficiario, LocalDate dataPesquisada, List<Venda> vendas) {

        if(beneficiario.getMesAnoAdmissao().isBefore(dataPesquisada)) {
            if(beneficiario.getCargo().equals(CargoEnum.VENDEDOR)) {
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
            } else {
                var anosDeServico = ChronoUnit.YEARS.between(beneficiario.getMesAnoAdmissao(), dataPesquisada);
                var valorPorAnosDeServico = anosDeServico * this.getSalarioPorAnoDeServico();
                var salarioMaisValorPorAnosDeServico = this.getSalarioMensal() + valorPorAnosDeServico;
                return ((salarioMaisValorPorAnosDeServico * this.getBeneficio()) - salarioMaisValorPorAnosDeServico);
            }
        }
        return 0;
    }
}
