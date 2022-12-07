package br.com.empresax.domain.service.funcionario;

import br.com.empresax.domain.dtos.funcionario.GerenteDTORequest;
import br.com.empresax.domain.dtos.funcionario.GerenteDTOResponse;
import br.com.empresax.domain.entities.funcionario.Gerente;
import br.com.empresax.domain.service.MensagemPadrao;
import br.com.empresax.resources.funcionario.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class GerenteService implements PolicyCrudService<GerenteDTORequest, GerenteDTOResponse, Long> {

    @Autowired
    private GerenteRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public GerenteDTOResponse cadastrar(GerenteDTORequest dto) {
        return Optional.of(dto)
                .map(Gerente::new)
                .map(gerente -> this.repository.saveAndFlush(gerente))
                .map(GerenteDTOResponse::new)
                .orElseThrow();
    }

    @Override
    public GerenteDTOResponse consultarPorId(Long id) {
        return this.repository.findById(id)
                .map(GerenteDTOResponse::new)
                .orElseThrow();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public String apagarPorId(Long id) {
        return this.repository.findById(id)
                .map(gerente -> {
                    this.repository.deleteById(id);
                    return MensagemPadrao.RECURSO_APAGADO;
                })
                .orElseThrow();
    }
}
