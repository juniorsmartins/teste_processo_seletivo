package br.com.empresax.application;

import br.com.empresax.domain.dtos.VendedorDTORequest;
import br.com.empresax.domain.dtos.VendedorDTOResponse;
import org.springframework.http.ResponseEntity;

public class VendedorController implements PolicyCrudController<VendedorDTORequest, VendedorDTOResponse, Long> {

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
