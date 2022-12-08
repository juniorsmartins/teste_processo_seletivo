package br.com.empresax.domain.entities.venda;

import br.com.empresax.domain.dtos.venda.VendaDTORequest;
import br.com.empresax.domain.entities.PolicyEntity;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "vendas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class Venda implements PolicyEntity<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "mes_ano_venda", length = 7, nullable = false)
    private String mesAnoVenda;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id", nullable = false)
    private Vendedor vendedor;

    public Venda(VendaDTORequest dto) {
        this.valor = dto.valor();
        this.mesAnoVenda = dto.mesAnoVenda();
    }
}
