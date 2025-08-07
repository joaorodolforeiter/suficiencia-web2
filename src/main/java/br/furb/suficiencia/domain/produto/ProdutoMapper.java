package br.furb.suficiencia.domain.produto;

import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDTO(ProdutoEntity produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco()
        );
    }

    public ProdutoEntity toEntity(ProdutoDTO produtoDTO) {
        return ProdutoEntity.builder()
                .id(produtoDTO.id())
                .nome(produtoDTO.nome())
                .preco(produtoDTO.preco())
                .build();
    }

}
