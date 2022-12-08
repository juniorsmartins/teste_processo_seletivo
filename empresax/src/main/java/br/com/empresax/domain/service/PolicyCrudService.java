package br.com.empresax.domain.service;

import br.com.empresax.domain.dtos.PolicyDTO;
import org.springframework.stereotype.Service;

@Service
public interface PolicyCrudService<E extends PolicyDTO<ID>, S extends PolicyDTO<ID>, ID> {

    S cadastrar(E dto);
    S consultarPorId(ID id);
    String apagarPorId(ID id);
}
