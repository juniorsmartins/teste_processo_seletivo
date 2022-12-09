package br.com.empresax.domain.service.dashboard;

import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.dtos.funcionario.FuncionarioDTOResponse;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import br.com.empresax.domain.entities.Beneficiario;
import br.com.empresax.domain.entities.funcionario.CargoEnum;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import br.com.empresax.domain.entities.funcionario.Secretario;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import br.com.empresax.domain.service.PolicyDashboardService;
import br.com.empresax.resources.funcionario.FuncionarioRepository;
import br.com.empresax.resources.funcionario.VendedorRepository;
import br.com.empresax.resources.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService implements PolicyDashboardService {

    private double valorRetorno;
    private Funcionario funcionarioMaisBemPago;
    private Beneficiario beneficiarioMaisBemPago;
    private Vendedor maiorVendedor;
    private double somaVendas;
    private double maiorSomaVendas;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Override
    public DashboardDTOResponse somarSalariosAndBeneficiosDaListaDeFuncionariosNaDataEspecificada(DashboardDTORequest request) {
        calcularValorTotalPago(this.funcionarioRepository.findAll(), request);
        return new DashboardDTOResponse(request.mesAnoPesquisado(), valorRetorno);
    }

        private void calcularValorTotalPago(List<Funcionario> funcionarios, DashboardDTORequest request) {
            valorRetorno = 0D;
            funcionarios.forEach(funcionario -> {
                valorRetorno += funcionario.getCargo()
                        .calcularPagamentoTotal(funcionario, request.mesAnoPesquisado(), this.vendaRepository.findAll());
            });
        }

    @Override
    public DashboardDTOResponse somarSalariosDaListaDeFuncionariosNaDataEspecificada(DashboardDTORequest request) {
        calcularValorSalarioPago(this.funcionarioRepository.findAll(), request);
        return new DashboardDTOResponse(request.mesAnoPesquisado(), valorRetorno);
    }

        private void calcularValorSalarioPago(List<Funcionario> funcionarios, DashboardDTORequest request) {
            valorRetorno = 0D;
            funcionarios.forEach(funcionario -> valorRetorno += funcionario.getCargo()
                            .calcularPagamentoDeSalario(funcionario, request.mesAnoPesquisado()));
        }

    @Override
    public DashboardDTOResponse somarBeneficiosDaListaDeBeneficiariosNaDataEspecificada(DashboardDTORequest request) {
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
            beneficiarios.forEach(beneficiario -> valorRetorno += beneficiario.getCargo()
                    .calcularPagamentoDeBeneficio(beneficiario, request.mesAnoPesquisado(), this.vendaRepository.findAll()));
        }

    @Override
    public FuncionarioDTOResponse encontrarMaiorPagamentoDeSalarioAndBeneficioDalistaDeFuncionariosNaDataEspecificada(DashboardDTORequest request) {
        funcionarioMaisBemPago = null;
        calcularMaisBemPago(this.funcionarioRepository.findAll(), request);
        return new FuncionarioDTOResponse(funcionarioMaisBemPago, valorRetorno);
    }

        private void calcularMaisBemPago(List<Funcionario> funcionarios, DashboardDTORequest request) {
            valorRetorno = 0D;
            funcionarios.forEach(funcionario -> {
                var somaSalarioBeneficio = funcionario.getCargo()
                        .calcularPagamentoTotal(funcionario, request.mesAnoPesquisado(), this.vendaRepository.findAll());
                if(somaSalarioBeneficio > valorRetorno) {
                    valorRetorno = somaSalarioBeneficio;
                    funcionarioMaisBemPago = funcionario;
                }
            });
        }

    @Override
    public FuncionarioDTOResponse encontrarMaiorBeneficiarioDaListaDeBeneficiariosNaDataEspecificada(DashboardDTORequest request) {
        beneficiarioMaisBemPago = null;

        List<Beneficiario> beneficiarios = new ArrayList<>();
        this.funcionarioRepository.findAll()
                .stream()
                .filter(funcionario -> funcionario instanceof Beneficiario)
                .map(funcionario ->
                    beneficiarios.add((Beneficiario)funcionario)
                )
                .toList();

        calcularMaiorBeneficiario(beneficiarios, request);
        return new FuncionarioDTOResponse(beneficiarioMaisBemPago, valorRetorno);
    }

        private void calcularMaiorBeneficiario(List<Beneficiario> beneficiarios, DashboardDTORequest request) {
            valorRetorno = 0D;
            beneficiarios.forEach(beneficiario -> {
                var valorBeneficio = beneficiario.getCargo()
                        .calcularPagamentoDeBeneficio(beneficiario, request.mesAnoPesquisado(), this.vendaRepository.findAll());
                if(valorBeneficio > valorRetorno) {
                    valorRetorno = valorBeneficio;
                    beneficiarioMaisBemPago = beneficiario;
                }
            });
        }

    @Override
    public VendedorDTOResponse encontrarMaiorVendaDaListaDeVendedoresNaDataEspecificada(DashboardDTORequest request) {
        return new VendedorDTOResponse(calcularMaiorVendedor(this.vendedorRepository.findAll(), request));
    }

        private Vendedor calcularMaiorVendedor(List<Vendedor> vendedores, DashboardDTORequest request) {
            maiorSomaVendas = 0d;

            vendedores.forEach(vendedor -> {
               if(vendedor.getMesAnoAdmissao().getMonthValue() <= request.mesAnoPesquisado().getMonthValue() &&
                   vendedor.getMesAnoAdmissao().getYear() <= request.mesAnoPesquisado().getYear()) {
                   somaVendas = 0d;
                   this.vendaRepository.findAllByVendedorId(vendedor.getId()).forEach(venda -> {
                       if(venda.getDataVenda().getMonthValue() == request.mesAnoPesquisado().getMonthValue()
                               && venda.getDataVenda().getYear() == request.mesAnoPesquisado().getYear()) {
                           somaVendas += venda.getValor();
                       }
                   });
               }

               if(somaVendas > maiorSomaVendas) {
                   maiorSomaVendas = somaVendas;
                   maiorVendedor = null;
                   maiorVendedor = vendedor;
               }
            });
            return maiorVendedor;
        }
}
