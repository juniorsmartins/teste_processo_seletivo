package br.com.empresax.application.funcionario;

import br.com.empresax.domain.dtos.funcionario.VendedorDTORequest;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vendedores", produces = {"application/json"})
public class VendedorController implements PolicyCrudController<VendedorDTORequest, VendedorDTOResponse, Long> {

    @PostMapping
    @Override
    public ResponseEntity<VendedorDTOResponse> cadastrar(VendedorDTORequest dto) {
        return null;
    }

    @Override
    public ResponseEntity<VendedorDTOResponse> consultarPorId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> apagarPorId(Long id) {
        return null;
    }
}
