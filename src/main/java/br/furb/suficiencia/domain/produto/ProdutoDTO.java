package br.furb.suficiencia.domain.produto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoDTO(
        @Nonnull
        Long id,
        @Nonnull
        String nome,
        @Nonnull
        @Positive
        BigDecimal preco
) {
}
