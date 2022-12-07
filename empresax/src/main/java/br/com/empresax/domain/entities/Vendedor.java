package br.com.empresax.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vendedores")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public final class Vendedor extends Funcionario implements Beneficiario {

    @OneToMany(targetEntity = Venda.class, mappedBy = "vendedor", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Venda> vendas;
}
