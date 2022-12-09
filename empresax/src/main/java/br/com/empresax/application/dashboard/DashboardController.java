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

    @GetMapping(path = "/somarPagamentos")
    @Override
    public ResponseEntity<DashboardDTOResponse> somarSalariosAndBeneficiosDaListaDeFuncionariosNaDataEspecificada(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.somarSalariosAndBeneficiosDaListaDeFuncionariosNaDataEspecificada(request));
    }

    @GetMapping(path = "/somarSalarios")
    @Override
    public ResponseEntity<DashboardDTOResponse> somarSalariosDaListaDeFuncionariosNaDataEspecificada(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.somarSalariosDaListaDeFuncionariosNaDataEspecificada(request));
    }

    @GetMapping(path = "/somarBeneficios")
    @Override
    public ResponseEntity<DashboardDTOResponse> somarBeneficiosDaListaDeBeneficiariosNaDataEspecificada(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.somarBeneficiosDaListaDeBeneficiariosNaDataEspecificada(request));
    }

    @GetMapping(path = "/maisBemPago")
    @Override
    public ResponseEntity<FuncionarioDTOResponse> encontrarMaiorPagamentoDeSalarioAndBeneficioDalistaDeFuncionariosNaDataEspecificada(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.encontrarMaiorPagamentoDeSalarioAndBeneficioDalistaDeFuncionariosNaDataEspecificada(request));
    }

    @GetMapping(path = "/maiorBeneficiario")
    @Override
    public ResponseEntity<FuncionarioDTOResponse> encontrarMaiorBeneficiarioDaListaDeBeneficiariosNaDataEspecificada(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.encontrarMaiorBeneficiarioDaListaDeBeneficiariosNaDataEspecificada(request));
    }

    @GetMapping(path = "/maiorVendedor")
    @Override
    public ResponseEntity<VendedorDTOResponse> encontrarMaiorVendaDaListaDeVendedoresNaDataEspecificada(@RequestBody @Valid DashboardDTORequest request) {
        return ResponseEntity
                .ok()
                .body(this.service.encontrarMaiorVendaDaListaDeVendedoresNaDataEspecificada(request));
    }
}
