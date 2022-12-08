package br.com.empresax.domain.entities.venda;

import br.com.empresax.domain.dtos.venda.VendaDTORequest;
import br.com.empresax.domain.entities.PolicyEntity;
import br.com.empresax.domain.entities.funcionario.Vendedor;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

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

    @Column(name = "data_venda", nullable = false)
    private LocalDate dataVenda;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id", nullable = false)
    private Vendedor vendedor;

    public Venda(VendaDTORequest dto) {
        this.valor = dto.valor();
        this.dataVenda = dto.dataVenda();
    }
}
