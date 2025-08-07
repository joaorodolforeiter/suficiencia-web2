package br.furb.suficiencia.auth;

public record LoginResponse(
        String token,
        String username
) {
}
