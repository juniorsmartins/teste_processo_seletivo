package br.com.empresax.resources.funcionario;

import br.com.empresax.domain.entities.funcionario.Gerente;
import br.com.empresax.domain.entities.funcionario.Secretario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretarioRepository extends JpaRepository<Secretario, Long> { }
