package br.com.empresax.resources.venda;

import br.com.empresax.domain.entities.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> { }
