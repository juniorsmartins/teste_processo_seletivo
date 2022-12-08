package br.com.empresax.application.funcionario;

import br.com.empresax.application.PolicyCrudController;
import br.com.empresax.domain.dtos.funcionario.GerenteDTORequest;
import br.com.empresax.domain.dtos.funcionario.GerenteDTOResponse;
import br.com.empresax.domain.service.PolicyCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/gerentes", produces = {"application/json"})
public class GerenteController implements PolicyCrudController<GerenteDTORequest, GerenteDTOResponse, Long> {

    @Autowired
    private PolicyCrudService<GerenteDTORequest, GerenteDTOResponse, Long> service;

    @PostMapping
    @Override
    public ResponseEntity<GerenteDTOResponse> cadastrar(@RequestBody @Valid GerenteDTORequest dto) {
        var response = this.service.cadastrar(dto);
        return ResponseEntity
                .created(URI.create("/" + response.id()))
                .body(response);
    }

    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<GerenteDTOResponse> consultarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity
                .ok()
                .body(this.service.consultarPorId(id));
    }

    @DeleteMapping(path = "/{id}")
    @Override
    public ResponseEntity<String> apagarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity
                .ok()
                .body(this.service.apagarPorId(id));
    }
}
