package br.furb.suficiencia.domain.comanda.dto;

import br.furb.suficiencia.domain.produto.ProdutoDTO;
import br.furb.suficiencia.domain.produto.ProdutoEntity;
import jakarta.validation.Valid;

import java.util.List;

public record ComandaUpdateRequest(
    @Valid
    List<ProdutoDTO> produtos
) {
}
