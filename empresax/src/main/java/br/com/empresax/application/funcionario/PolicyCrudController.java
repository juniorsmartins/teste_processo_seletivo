package br.com.empresax.application.funcionario;

import br.com.empresax.domain.dtos.funcionario.PolicyDTO;
import org.springframework.http.ResponseEntity;

public interface PolicyCrudController<E extends PolicyDTO<ID>, S extends PolicyDTO<ID>, ID> {

    ResponseEntity<S> cadastrar(E dto);
    ResponseEntity<S> consultarPorId(ID id);
    ResponseEntity<?> apagarPorId(ID id);
}
