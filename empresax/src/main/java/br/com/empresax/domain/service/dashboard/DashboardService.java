package br.com.empresax.domain.service.dashboard;

import br.com.empresax.application.excecao.ResourceNotFoundCustomException;
import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.dtos.funcionario.FuncionarioDTOResponse;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import br.com.empresax.domain.entities.Beneficiario;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import br.com.empresax.domain.entities.venda.Venda;
import br.com.empresax.domain.service.PolicyDashboardService;
import br.com.empresax.resources.funcionario.FuncionarioRepository;
import br.com.empresax.resources.funcionario.VendedorRepository;
import br.com.empresax.resources.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    // ---------- FEATURE 1 ---------- //
    @Override
    public DashboardDTOResponse somarSalariosAndBeneficiosDaListaDeFuncionariosNaDataEspecificada(DashboardDTORequest request) {
        return new DashboardDTOResponse(request.dataPesquisada(),
                calcularValorTotalPago(this.funcionarioRepository.findAll(), request.dataPesquisada()));
    }

        private double calcularValorTotalPago(List<Funcionario> funcionarios, LocalDate dataPesquisada) {
            valorRetorno = 0D;

            verificarListaVazia(funcionarios);

            funcionarios.forEach(funcionario -> valorRetorno += funcionario.getCargo().calcularPagamentoTotal(
                    funcionario, dataPesquisada, vendasPorVendedor(funcionario.getId())));
            return valorRetorno;
        }

        private void verificarListaVazia(List<?> lista) {
            if(lista.isEmpty())
                throw new ResourceNotFoundCustomException("Nenhum cadastrado!");
        }

        private List<Venda> vendasPorVendedor(Long id) {
            return this.vendaRepository.findAllByVendedorId(id);
        }

    // ---------- FEATURE 2 ---------- //
    @Override
    public DashboardDTOResponse somarSalariosDaListaDeFuncionariosNaDataEspecificada(DashboardDTORequest request) {
        return new DashboardDTOResponse(request.dataPesquisada(), calcularValorSalarioPago(
                this.funcionarioRepository.findAll(), request.dataPesquisada()));
    }

        private double calcularValorSalarioPago(List<Funcionario> funcionarios, LocalDate dataPesquisada) {
            valorRetorno = 0D;

            verificarListaVazia(funcionarios);

            funcionarios.forEach(funcionario -> valorRetorno += funcionario.getCargo()
                            .calcularPagamentoDeSalario(funcionario, dataPesquisada));
            return valorRetorno;
        }

    // ---------- FEATURE 3 ---------- //
    @Override
    public DashboardDTOResponse somarBeneficiosDaListaDeBeneficiariosNaDataEspecificada(DashboardDTORequest request) {
        var beneficiarios = selecionarFuncionariosBeneficiarios();
        var valorRetorno = calcularValorBeneficioPago(beneficiarios , request.dataPesquisada());
        return new DashboardDTOResponse(request.dataPesquisada(), valorRetorno);
    }

        private List<Beneficiario> selecionarFuncionariosBeneficiarios() {
            List<Beneficiario> beneficiarios = new ArrayList<>();
            this.funcionarioRepository.findAll()
                    .stream()
                    .filter(funcionario -> funcionario instanceof Beneficiario)
                    .map(funcionario -> beneficiarios.add((Beneficiario) funcionario))
                    .toList();

            verificarListaVazia(beneficiarios);

            return beneficiarios;
        }

        private double calcularValorBeneficioPago(List<Beneficiario> beneficiarios, LocalDate dataPesquisada) {
            valorRetorno = 0D;
            beneficiarios.forEach(beneficiario -> valorRetorno += beneficiario.getCargo()
                    .calcularPagamentoDeBeneficio(beneficiario, dataPesquisada, vendasPorVendedor(beneficiario.getId())));
            return valorRetorno;
        }

    // ---------- FEATURE 4 ---------- //
    @Override
    public FuncionarioDTOResponse encontrarMaiorPagamentoDeSalarioAndBeneficioDalistaDeFuncionariosNaDataEspecificada(DashboardDTORequest request) {
        calcularMaisBemPago(this.funcionarioRepository.findAll(), request.dataPesquisada());
        return new FuncionarioDTOResponse(funcionarioMaisBemPago, valorRetorno);
    }

        private void calcularMaisBemPago(List<Funcionario> funcionarios, LocalDate dataPesquisada) {
            valorRetorno = 0D;

            verificarListaVazia(funcionarios);

            funcionarios.forEach(funcionario -> {
                var somaSalarioBeneficio = funcionario.getCargo()
                    .calcularPagamentoTotal(funcionario, dataPesquisada, vendasPorVendedor(funcionario.getId()));
                if(somaSalarioBeneficio > valorRetorno) {
                    valorRetorno = somaSalarioBeneficio;
                    funcionarioMaisBemPago = funcionario;
                }
            });
        }

    // ---------- FEATURE 5 ---------- //
    @Override
    public FuncionarioDTOResponse encontrarMaiorBeneficiarioDaListaDeBeneficiariosNaDataEspecificada(DashboardDTORequest request) {
        calcularMaiorBeneficiario(selecionarFuncionariosBeneficiarios(), request.dataPesquisada());
        return new FuncionarioDTOResponse(beneficiarioMaisBemPago, valorRetorno);
    }

        private void calcularMaiorBeneficiario(List<Beneficiario> beneficiarios, LocalDate dataPesquisada) {
            valorRetorno = 0D;

            beneficiarios.forEach(beneficiario -> {
                var valorBeneficio = beneficiario.getCargo()
                        .calcularPagamentoDeBeneficio(beneficiario, dataPesquisada, vendasPorVendedor(beneficiario.getId()));

                if(valorBeneficio == 0)
                    throw new ResourceNotFoundCustomException("Nenhum benefício concedido!");

                if(valorBeneficio > valorRetorno) {
                    valorRetorno = valorBeneficio;
                    beneficiarioMaisBemPago = beneficiario;
                }
            });
        }

    // ---------- FEATURE 6 ---------- //
    @Override
    public VendedorDTOResponse encontrarMaiorVendaDaListaDeVendedoresNaDataEspecificada(DashboardDTORequest request) {
        return new VendedorDTOResponse(calcularMaiorVendedor(this.vendedorRepository.findAll(), request));
    }

        private Vendedor calcularMaiorVendedor(List<Vendedor> vendedores, DashboardDTORequest request) {
            maiorSomaVendas = 0d;

            verificarListaVazia(vendedores);

            vendedores.forEach(vendedor -> {
               if(vendedor.getMesAnoAdmissao().getMonthValue() <= request.dataPesquisada().getMonthValue() &&
                   vendedor.getMesAnoAdmissao().getYear() <= request.dataPesquisada().getYear()) {
                   somaVendas = 0d;
                   this.vendaRepository.findAllByVendedorId(vendedor.getId()).forEach(venda -> {
                       if(venda.getDataVenda().getMonthValue() == request.dataPesquisada().getMonthValue()
                               && venda.getDataVenda().getYear() == request.dataPesquisada().getYear()) {
                           somaVendas += venda.getValor();
                       }
                   });
               }

               if(somaVendas == 0)
                   throw new ResourceNotFoundCustomException("Nenhuma venda realizada!");

               if(somaVendas > maiorSomaVendas) {
                   maiorSomaVendas = somaVendas;
                   maiorVendedor = vendedor;
               }
            });
            return maiorVendedor;
        }
}
