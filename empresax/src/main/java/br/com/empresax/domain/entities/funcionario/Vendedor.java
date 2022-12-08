package br.com.empresax.domain.entities.funcionario;

import br.com.empresax.domain.dtos.funcionario.VendedorDTORequest;
import br.com.empresax.domain.entities.Beneficiario;
import br.com.empresax.domain.entities.CargoEnum;
import br.com.empresax.domain.entities.PolicyEntity;
import br.com.empresax.domain.entities.venda.Venda;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vendedores")
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public final class Vendedor extends Funcionario implements PolicyEntity<Long>, Beneficiario {

    @OneToMany(targetEntity = Venda.class, mappedBy = "vendedor", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Venda> vendas;

    public Vendedor() {
        this.cargo = CargoEnum.VENDEDOR;
    }

    public Vendedor(VendedorDTORequest dto) {
        this.nome = dto.nome();
        this.mesAnoAdmissao = dto.mesAnoAdmissao();
        this.cargo = CargoEnum.VENDEDOR;
    }
}
