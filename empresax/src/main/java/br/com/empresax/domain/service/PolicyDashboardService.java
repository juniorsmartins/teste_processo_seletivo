package br.com.empresax.domain.service;

import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import org.springframework.stereotype.Service;

@Service
public interface PolicyDashboardService {

    DashboardDTOResponse calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request);
    DashboardDTOResponse calcularPagamentoTotalDeSalariosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request);
    DashboardDTOResponse calcularPagamentoTotalDeBeneficiosDaListaDeBeneficiariosNoMesAnoEspecificado(DashboardDTORequest request);

}

