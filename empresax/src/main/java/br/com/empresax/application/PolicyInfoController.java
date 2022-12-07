package br.com.empresax.application;

import br.com.empresax.domain.entities.Funcionario;
import br.com.empresax.domain.entities.Vendedor;
import org.springframework.http.ResponseEntity;

public interface PolicyInfoController {

    ResponseEntity<Double> calcularPagamentoTotalDeSalariosAndBeneficiosDaListaDeFuncionariosNoMesAnoEspecificado(String mesAno);
    ResponseEntity<Double> calcularPagamentoTotalDeSalariosDaListaDeFuncionariosNoMesAnoEspecificado(String mesAno);
    ResponseEntity<Double> calcularPagamentoTotalDeBeneficiosDaListaDeBeneficiariosNoMesAnoEspecificado(String mesAno);
    ResponseEntity<Funcionario> encontrarMaiorPagamentoTotalDeSalarioAndBeneficioDalistaDeFuncionariosNoMesAnoEspecificado(String mesAno);
    ResponseEntity<String> encontrarNomeDeQuemRecebeuMaiorPagamentoDeBeneficioDaListaDeBeneficiariosNoMesAnoEspecificado(String mesAno);
    ResponseEntity<Vendedor> encontrarMaiorValorDeVendasDaListaDeVendedoresNoMesAnoEspecificado(String mesAno);
}
