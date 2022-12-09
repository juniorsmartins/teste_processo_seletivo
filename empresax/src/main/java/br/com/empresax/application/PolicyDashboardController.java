package br.com.empresax.application;

import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.dtos.funcionario.FuncionarioDTOResponse;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import org.springframework.http.ResponseEntity;

public interface PolicyDashboardController {

    ResponseEntity<DashboardDTOResponse> somarSalariosAndBeneficiosDaListaDeFuncionariosNaDataEspecificada(DashboardDTORequest request);
    ResponseEntity<DashboardDTOResponse> somarSalariosDaListaDeFuncionariosNaDataEspecificada(DashboardDTORequest request);
    ResponseEntity<DashboardDTOResponse> somarBeneficiosDaListaDeBeneficiariosNaDataEspecificada(DashboardDTORequest request);
    ResponseEntity<FuncionarioDTOResponse> encontrarMaiorPagamentoDeSalarioAndBeneficioDalistaDeFuncionariosNaDataEspecificada(DashboardDTORequest request);
    ResponseEntity<FuncionarioDTOResponse> encontrarMaiorBeneficiarioDaListaDeBeneficiariosNaDataEspecificada(DashboardDTORequest request);
    ResponseEntity<VendedorDTOResponse> encontrarMaiorVendaDaListaDeVendedoresNaDataEspecificada(DashboardDTORequest request);
}
