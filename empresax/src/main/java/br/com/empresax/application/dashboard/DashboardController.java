package br.com.empresax.application.dashboard;

import br.com.empresax.application.PolicyDashboardController;
import br.com.empresax.domain.dtos.dashboard.DashboardDTORequest;
import br.com.empresax.domain.dtos.dashboard.DashboardDTOResponse;
import br.com.empresax.domain.dtos.funcionario.FuncionarioDTOResponse;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
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

    @GetMapping(path = "/maisBemPago")
    @Override
    public ResponseEntity<FuncionarioDTOResponse> encontrarMaiorPagamentoTotalDeSalarioAndBeneficioDalistaDeFuncionariosNoMesAnoEspecificado(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.encontrarMaiorPagamentoTotalDeSalarioAndBeneficioDalistaDeFuncionariosNoMesAnoEspecificado(request));
    }





    @Override
    public ResponseEntity<String> encontrarNomeDeQuemRecebeuMaiorPagamentoDeBeneficioDaListaDeBeneficiariosNoMesAnoEspecificado(DashboardDTORequest request) {
        return null;
    }

    @Override
    public ResponseEntity<VendedorDTOResponse> encontrarMaiorValorDeVendasDaListaDeVendedoresNoMesAnoEspecificado(DashboardDTORequest request) {
        return null;
    }
}
