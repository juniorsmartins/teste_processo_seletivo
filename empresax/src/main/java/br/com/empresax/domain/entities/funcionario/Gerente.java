package br.com.empresax.domain.entities.funcionario;

import br.com.empresax.domain.dtos.funcionario.GerenteDTORequest;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "gerentes")
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public final class Gerente extends Funcionario implements PolicyEntity<Long> {

    public Gerente(GerenteDTORequest dto) {
        this.nome = dto.nome();
        this.mesAnoAdmissao = dto.mesAnoAdmissao();
    }
}

