package br.com.empresax.application;

import br.com.empresax.domain.dtos.SecretarioDTORequest;
import br.com.empresax.domain.dtos.SecretarioDTOResponse;
import org.springframework.http.ResponseEntity;

public class SecretarioController implements PolicyCrudController<SecretarioDTORequest, SecretarioDTOResponse, Long> {

    @Override
    public ResponseEntity<SecretarioDTOResponse> cadastrar(SecretarioDTORequest dto) {
        return null;
    }

    @Override
    public ResponseEntity<SecretarioDTOResponse> consultarPorId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> apagarPorId(Long id) {
        return null;
    }
}
