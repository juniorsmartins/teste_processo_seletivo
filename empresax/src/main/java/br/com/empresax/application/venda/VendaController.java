package br.com.empresax.application.venda;

import br.com.empresax.application.PolicyCrudController;
import br.com.empresax.domain.dtos.venda.VendaDTORequest;
import br.com.empresax.domain.dtos.venda.VendaDTOResponse;
import br.com.empresax.domain.service.PolicyCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/vendas", produces = {"application/json"})
public class VendaController implements PolicyCrudController<VendaDTORequest, VendaDTOResponse, Long> {

    @Autowired
    private PolicyCrudService<VendaDTORequest, VendaDTOResponse, Long> service;

    @PostMapping
    @Override
    public ResponseEntity<VendaDTOResponse> cadastrar(@RequestBody @Valid VendaDTORequest dto) {
        var response = this.service.cadastrar(dto);
        return ResponseEntity
                .created(URI.create("/" + response.id()))
                .body(response);
    }

    @Override
    public ResponseEntity<VendaDTOResponse> consultarPorId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> apagarPorId(Long id) {
        return null;
    }
}
