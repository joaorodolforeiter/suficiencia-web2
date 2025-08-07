package br.furb.suficiencia.domain.comanda.dto;

public record ComandaResponse(
        Long id,
        Long idUsuario,
        String nomeUsuario,
        String telefoneUsuario
) {
}
