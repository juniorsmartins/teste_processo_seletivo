package br.com.empresax.resources.venda;

import br.com.empresax.domain.entities.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findAllByVendedorId(Long vendedorId);
}
