package br.com.empresax.domain.service;

import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.dtos.funcionario.FuncionarioDTOResponse;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import org.springframework.stereotype.Service;

@Service
public interface PolicyDashboardService {

    DashboardDTOResponse somarSalariosAndBeneficiosDaListaDeFuncionariosNaDataEspecificada(DashboardDTORequest request);
    DashboardDTOResponse somarSalariosDaListaDeFuncionariosNaDataEspecificada(DashboardDTORequest request);
    DashboardDTOResponse somarBeneficiosDaListaDeBeneficiariosNaDataEspecificada(DashboardDTORequest request);
    FuncionarioDTOResponse encontrarMaiorPagamentoDeSalarioAndBeneficioDalistaDeFuncionariosNaDataEspecificada(DashboardDTORequest request);
    FuncionarioDTOResponse encontrarMaiorBeneficiarioDaListaDeBeneficiariosNaDataEspecificada(DashboardDTORequest request);
    VendedorDTOResponse encontrarMaiorVendaDaListaDeVendedoresNaDataEspecificada(DashboardDTORequest request);
}

