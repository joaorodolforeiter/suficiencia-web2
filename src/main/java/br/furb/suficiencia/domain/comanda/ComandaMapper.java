package br.furb.suficiencia.domain.comanda;

import br.furb.suficiencia.domain.comanda.dto.ComandaDetailedResponse;
import br.furb.suficiencia.domain.comanda.dto.ComandaResponse;
import br.furb.suficiencia.domain.produto.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComandaMapper {

    private final ProdutoMapper produtoMapper;

    public ComandaDetailedResponse toDetailedDTO(ComandaEntity comandaEntity) {
        return new ComandaDetailedResponse(
                comandaEntity.getId(),
                comandaEntity.getIdUsuario(),
                comandaEntity.getNomeUsuario(),
                comandaEntity.getTelefoneUsuario(),
                comandaEntity.getProdutos().stream().map(produtoMapper::toDTO).toList()
        );
    }

    public ComandaResponse toDTO(ComandaEntity comandaEntity) {
        return new ComandaResponse(
                comandaEntity.getId(),
                comandaEntity.getIdUsuario(),
                comandaEntity.getNomeUsuario(),
                comandaEntity.getTelefoneUsuario()
        );
    }

}
