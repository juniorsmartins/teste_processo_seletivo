package br.com.empresax.domain.service.funcionario;

import br.com.empresax.domain.dtos.funcionario.VendedorDTORequest;
import br.com.empresax.domain.dtos.funcionario.VendedorDTOResponse;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import br.com.empresax.domain.service.MensagemPadrao;
import br.com.empresax.resources.funcionario.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VendedorService implements PolicyCrudService<VendedorDTORequest, VendedorDTOResponse, Long> {

    @Autowired
    private VendedorRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public VendedorDTOResponse cadastrar(VendedorDTORequest dto) {
        return Optional.of(dto)
                .map(Vendedor::new)
                .map(vendedor -> this.repository.saveAndFlush(vendedor))
                .map(VendedorDTOResponse::new)
                .orElseThrow();
    }

    @Override
    public VendedorDTOResponse consultarPorId(Long id) {
        return this.repository.findById(id)
                .map(VendedorDTOResponse::new)
                .orElseThrow();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public String apagarPorId(Long id) {
        return this.repository.findById(id)
                .map(vendedor -> {
                    this.repository.delete(vendedor);
                    return MensagemPadrao.RECURSO_APAGADO;
                })
                .orElseThrow();
    }
}
