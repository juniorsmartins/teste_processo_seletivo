package br.com.empresax.domain.entities.funcionario;

import br.com.empresax.domain.dtos.funcionario.GerenteDTORequest;
import br.com.empresax.domain.entities.PolicyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "gerentes")
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public final class Gerente extends Funcionario implements PolicyEntity<Long> {

    public Gerente() {
        this.cargo = CargoEnum.GERENTE;
    }

    public Gerente(GerenteDTORequest dto) {
        this.nome = dto.nome();
        this.mesAnoAdmissao = dto.mesAnoAdmissao();
        this.cargo = CargoEnum.GERENTE;
    }
}

