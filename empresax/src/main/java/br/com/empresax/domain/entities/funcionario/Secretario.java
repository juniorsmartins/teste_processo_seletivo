package br.com.empresax.domain.entities.funcionario;

import br.com.empresax.domain.dtos.funcionario.SecretarioDTORequest;
import br.com.empresax.domain.entities.Beneficiario;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "secretarios")
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public final class Secretario extends Funcionario implements PolicyEntity<Long>, Beneficiario {

    public Secretario(SecretarioDTORequest dto) {
        this.nome = dto.nome();
        this.mesAnoAdmissao = dto.mesAnoAdmissao();
    }
}
