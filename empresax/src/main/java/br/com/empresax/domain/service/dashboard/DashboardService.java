package br.com.empresax.domain.service.dashboard;

import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import br.com.empresax.domain.service.PolicyDashboardService;
import br.com.empresax.resources.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService implements PolicyDashboardService {

    private double valorTotalPago;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public DashboardDTOResponse calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request) {
        valorTotalPago = 0D;
        calcularValorTotalPago(this.funcionarioRepository.findAll(), request);
        return new DashboardDTOResponse(request.mesAnoPesquisado(), valorTotalPago);
    }

        private void calcularValorTotalPago(List<Funcionario> funcionarios, DashboardDTORequest request) {
            funcionarios.forEach(funcionario -> {
                if(funcionario.getMesAnoAdmissao().isBefore(request.mesAnoPesquisado())) {
                    valorTotalPago += funcionario.getCargo().getSalarioMensal() * funcionario.getCargo().getBeneficio();
                }
            });
        }
}
