package br.furb.suficiencia.domain.comanda.dto;

import br.furb.suficiencia.domain.produto.ProdutoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ComandaRequest(
    @NotNull
    Long idUsuario,

    @NotBlank
    String nomeUsuario,

    @NotBlank
    String telefoneUsuario,

    @NotEmpty
    @Valid
    List<ProdutoDTO> produtos
) {
}
