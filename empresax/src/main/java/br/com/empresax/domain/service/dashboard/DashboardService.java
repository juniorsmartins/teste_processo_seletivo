package br.com.empresax.domain.service.dashboard;

import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.entities.Beneficiario;
import br.com.empresax.domain.entities.funcionario.CargoEnum;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import br.com.empresax.domain.entities.funcionario.Secretario;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import br.com.empresax.domain.service.PolicyDashboardService;
import br.com.empresax.resources.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService implements PolicyDashboardService {

    private double valorRetorno;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public DashboardDTOResponse calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request) {
        calcularValorTotalPago(this.funcionarioRepository.findAll(), request);
        return new DashboardDTOResponse(request.mesAnoPesquisado(), valorRetorno);
    }

        private void calcularValorTotalPago(List<Funcionario> funcionarios, DashboardDTORequest request) {
            valorRetorno = 0D;
            funcionarios.forEach(funcionario -> {
                if(funcionario.getMesAnoAdmissao().isBefore(request.mesAnoPesquisado())) {
                    valorRetorno += funcionario.getCargo().getSalarioMensal() * funcionario.getCargo().getBeneficio();
                }
            });
        }

    @Override
    public DashboardDTOResponse calcularPagamentoTotalDeSalariosDaListaDeFuncionariosNoMesAnoEspecificado(DashboardDTORequest request) {
        calcularValorSalarioPago(this.funcionarioRepository.findAll(), request);
        return new DashboardDTOResponse(request.mesAnoPesquisado(), valorRetorno);
    }

        private void calcularValorSalarioPago(List<Funcionario> funcionarios, DashboardDTORequest request) {
            valorRetorno = 0D;
            funcionarios.forEach(funcionario -> {
                if(funcionario.getMesAnoAdmissao().isBefore(request.mesAnoPesquisado())) {
                    valorRetorno += funcionario.getCargo().getSalarioMensal();
                }
            });
        }

    @Override
    public DashboardDTOResponse calcularPagamentoTotalDeBeneficiosDaListaDeBeneficiariosNoMesAnoEspecificado(DashboardDTORequest request) {
        List<Beneficiario> beneficiarios = new ArrayList<>();
        this.funcionarioRepository.findAll()
                .stream()
                .filter(funcionario -> funcionario.getCargo().equals(CargoEnum.SECRETARIO) || funcionario.getCargo().equals(CargoEnum.VENDEDOR))
                .map(funcionario -> {
                    if(funcionario.getCargo().equals(CargoEnum.SECRETARIO))
                        return beneficiarios.add((Secretario) funcionario);
                    return beneficiarios.add((Vendedor) funcionario);
                })
                .toList();

        calcularValorBeneficioPago(beneficiarios , request);
        return new DashboardDTOResponse(request.mesAnoPesquisado(), valorRetorno);
    }

        private void calcularValorBeneficioPago(List<Beneficiario> beneficiarios, DashboardDTORequest request) {
            valorRetorno = 0D;
            beneficiarios.forEach(beneficiario -> {
                if(beneficiario.getMesAnoAdmissao().isBefore(request.mesAnoPesquisado())) {
                    valorRetorno += ((beneficiario.getCargo().getSalarioMensal() * beneficiario.getCargo().getBeneficio() - beneficiario.getCargo().getSalarioMensal()));
                }
            });
        }
}
