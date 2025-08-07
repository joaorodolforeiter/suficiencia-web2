package br.furb.suficiencia.domain.comanda.dto;

import br.furb.suficiencia.domain.produto.ProdutoDTO;

import java.util.List;

public record ComandaDetailedResponse(
        Long id,
        Long idUsuario,
        String nomeUsuario,
        String telefoneUsuario,
        List<ProdutoDTO> produtos
) {
}
