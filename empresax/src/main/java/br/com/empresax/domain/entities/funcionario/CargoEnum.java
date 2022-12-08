package br.com.empresax.domain.entities.funcionario;

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
            var valorPorAnosDeServico = anosDeServico * funcionario.getCargo().getSalarioPorAnoDeServico();
            var salarioMaisValorPorAnosDeServico = funcionario.getCargo().getSalarioMensal() + valorPorAnosDeServico;

            if(funcionario.getCargo().getNome().equalsIgnoreCase(CargoEnum.GERENTE.getNome())) {
                return salarioMaisValorPorAnosDeServico * funcionario.getCargo().getBeneficio();
            } else if(funcionario.getCargo().getNome().equalsIgnoreCase(CargoEnum.SECRETARIO.getNome())) {
                return salarioMaisValorPorAnosDeServico * funcionario.getCargo().getBeneficio();
            } else if(funcionario.getCargo().getNome().equalsIgnoreCase(CargoEnum.VENDEDOR.getNome())) {
                var valorTotalVendas = 0d;
                for (Venda venda : vendas) {
                    if(funcionario.getId() == venda.getVendedor().getId()) {
                        //TODO separar venda por data pesquisada
                        if(venda.getDataVenda().getMonthValue() == dataPesquisada.getMonthValue()
                                && venda.getDataVenda().getYear() == dataPesquisada.getYear()) {
                            valorTotalVendas += venda.getValor();
                        }
                    }
                }
                var valorDoBeneficioSobreValorVendido = ((valorTotalVendas * funcionario.getCargo().getBeneficio()) - valorTotalVendas);
                return salarioMaisValorPorAnosDeServico + valorDoBeneficioSobreValorVendido;
            }
        }
        return 0;
    }
}
