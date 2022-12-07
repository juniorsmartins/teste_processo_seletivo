package br.com.empresax.resources.funcionario;

import br.com.empresax.domain.entities.funcionario.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> { }
