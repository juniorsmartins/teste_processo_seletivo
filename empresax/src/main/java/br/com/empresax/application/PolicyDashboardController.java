package br.com.empresax.application;

import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.dtos.funcionario.FuncionarioDTOResponse;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import org.springframework.http.ResponseEntity;

public interface PolicyDashboardController {

    ResponseEntity<DashboardDTOResponse> calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request);
    ResponseEntity<DashboardDTOResponse> calcularPagamentoTotalDeSalariosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request);
    ResponseEntity<DashboardDTOResponse> calcularPagamentoTotalDeBeneficiosDaListaDeBeneficiariosNoMesAnoEspecificado(DashboardDTORequest request);



    ResponseEntity<FuncionarioDTOResponse> encontrarMaiorPagamentoTotalDeSalarioAndBeneficioDalistaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request);



    ResponseEntity<String> encontrarNomeDeQuemRecebeuMaiorPagamentoDeBeneficioDaListaDeBeneficiariosNoMesAnoEspecificado(DashboardDTORequest request);
    ResponseEntity<VendedorDTOResponse> encontrarMaiorValorDeVendasDaListaDeVendedoresNoMesAnoEspecificado(DashboardDTORequest request);
}
