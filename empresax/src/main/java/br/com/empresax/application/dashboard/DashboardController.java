package br.com.empresax.application.dashboard;

import br.com.empresax.application.PolicyDashboardController;
import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.entities.funcionario.Funcionario;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import br.com.empresax.domain.service.PolicyDashboardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dashboard", produces = {"application/json"})
public class DashboardController implements PolicyDashboardController {

    @Autowired
    private PolicyDashboardService service;

    @GetMapping(path = "/valorTotalPago")
    @Override
    public ResponseEntity<DashboardDTOResponse> calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(request));
    }

    @GetMapping(path = "/valorSalarioPago")
    @Override
    public ResponseEntity<DashboardDTOResponse> calcularPagamentoTotalDeSalariosDaListaDeFuncionariosNoMesAnoEspecificado(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.calcularPagamentoTotalDeSalariosDaListaDeFuncionariosNoMesAnoEspecificado(request));
    }

    @GetMapping(path = "/valorBeneficioPago")
    @Override
    public ResponseEntity<DashboardDTOResponse> calcularPagamentoTotalDeBeneficiosDaListaDeBeneficiariosNoMesAnoEspecificado(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.calcularPagamentoTotalDeBeneficiosDaListaDeBeneficiariosNoMesAnoEspecificado(request));
    }





    @Override
    public ResponseEntity<Funcionario> encontrarMaiorPagamentoTotalDeSalarioAndBeneficioDalistaDeFuncionariosNoMesAnoEspecificado(String mesAno) {
        return null;
    }

    @Override
    public ResponseEntity<String> encontrarNomeDeQuemRecebeuMaiorPagamentoDeBeneficioDaListaDeBeneficiariosNoMesAnoEspecificado(String mesAno) {
        return null;
    }

    @Override
    public ResponseEntity<Vendedor> encontrarMaiorValorDeVendasDaListaDeVendedoresNoMesAnoEspecificado(String mesAno) {
        return null;
    }
}
