package br.com.empresax.application;

import br.com.empresax.domain.dtos.PolicyDTO;
import org.springframework.http.ResponseEntity;

public interface PolicyCrudController<E extends PolicyDTO<ID>, S extends PolicyDTO<ID>, ID> {

    ResponseEntity<S> cadastrar(E dto);
    ResponseEntity<S> consultarPorId(ID id);
    ResponseEntity<?> apagarPorId(ID id);
}
