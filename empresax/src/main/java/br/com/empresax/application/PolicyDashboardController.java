package br.com.empresax.application;

import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import org.springframework.http.ResponseEntity;

public interface PolicyDashboardController {

    ResponseEntity<DashboardDTOResponse> calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request);
    ResponseEntity<DashboardDTOResponse> calcularPagamentoTotalDeSalariosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request);



    ResponseEntity<Double> calcularPagamentoTotalDeBeneficiosDaListaDeBeneficiariosNoMesAnoEspecificado(String mesAno);
    ResponseEntity<Funcionario> encontrarMaiorPagamentoTotalDeSalarioAndBeneficioDalistaDeFuncionariosNoMesAnoEspecificado(String mesAno);
    ResponseEntity<String> encontrarNomeDeQuemRecebeuMaiorPagamentoDeBeneficioDaListaDeBeneficiariosNoMesAnoEspecificado(String mesAno);
    ResponseEntity<Vendedor> encontrarMaiorValorDeVendasDaListaDeVendedoresNoMesAnoEspecificado(String mesAno);
}
