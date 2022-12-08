package br.com.empresax.domain.service.venda;

import br.com.empresax.domain.dtos.venda.VendaDTORequest;
import br.com.empresax.domain.dtos.venda.VendaDTOResponse;
import br.com.empresax.domain.entities.venda.Venda;
import br.com.empresax.domain.service.PolicyCrudService;
import br.com.empresax.resources.funcionario.VendedorRepository;
import br.com.empresax.resources.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VendaService implements PolicyCrudService<VendaDTORequest, VendaDTOResponse, Long> {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public VendaDTOResponse cadastrar(VendaDTORequest dto) {
        return Optional.of(dto)
                .map(Venda::new)
                .map(venda -> {
                    var vendedor = this.vendedorRepository.findById(dto.vendedorId()).orElseThrow();
                    venda.setVendedor(vendedor);
                    return this.repository.saveAndFlush(venda);
                })
                .map(VendaDTOResponse::new)
                .orElseThrow();
    }

    @Override
    public VendaDTOResponse consultarPorId(Long aLong) {
        return null;
    }

    @Override
    public String apagarPorId(Long aLong) {
        return null;
    }
}
