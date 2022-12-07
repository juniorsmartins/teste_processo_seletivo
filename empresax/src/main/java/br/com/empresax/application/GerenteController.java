package br.com.empresax.application;

import br.com.empresax.domain.dtos.GerenteDTORequest;
import br.com.empresax.domain.dtos.GerenteDTOResponse;
import org.springframework.http.ResponseEntity;

public class GerenteController implements PolicyCrudController<GerenteDTORequest, GerenteDTOResponse, Long> {

    @Override
    public ResponseEntity<GerenteDTOResponse> cadastrar(GerenteDTORequest dto) {
        return null;
    }

    @Override
    public ResponseEntity<GerenteDTOResponse> consultarPorId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> apagarPorId(Long id) {
        return null;
    }
}
