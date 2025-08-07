package br.furb.suficiencia.domain.produto;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos")
public class ProdutoEntity {

    @Id
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false)
    private BigDecimal preco;

}
