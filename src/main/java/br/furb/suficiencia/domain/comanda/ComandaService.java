package br.furb.suficiencia.domain.comanda;

import br.furb.suficiencia.domain.comanda.dto.ComandaRequest;
import br.furb.suficiencia.domain.comanda.dto.ComandaUpdateRequest;
import br.furb.suficiencia.domain.produto.ProdutoDTO;
import br.furb.suficiencia.domain.produto.ProdutoEntity;
import br.furb.suficiencia.domain.produto.ProdutoMapper;
import br.furb.suficiencia.domain.produto.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ComandaService {

    private final ComandaRepository comandaRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public List<ComandaEntity> getAllComandas() {
        return comandaRepository.findAll();
    }

    public Optional<ComandaEntity> getComandaById(Long id) {
        return comandaRepository.findById(id);
    }

    public ComandaEntity createComanda(ComandaRequest request) {
        ComandaEntity comanda = new ComandaEntity();

        comanda.setIdUsuario(request.idUsuario());
        comanda.setNomeUsuario(request.nomeUsuario());
        comanda.setTelefoneUsuario(request.telefoneUsuario());

        List<ProdutoEntity> produtos = request.produtos().stream()
                .map(produtoDTO -> produtoRepository
                        .findById(produtoDTO.id())
                        .orElseGet(() -> criarProduto(produtoDTO))
                )
                .toList();

        comanda.setProdutos(produtos);

        return comandaRepository.save(comanda);
    }

    public Optional<ComandaEntity> updateComanda(Long id, ComandaUpdateRequest request) {
        return comandaRepository.findById(id).map(comanda -> {

            List<ProdutoEntity> p = request.produtos().stream()
                    .map(produtoDTO -> produtoRepository
                            .findById(produtoDTO.id())
                            .orElseGet(() -> criarProduto(produtoDTO))
                    )
                    .toList();

            comanda.getProdutos().addAll(p);

            return comandaRepository.save(comanda);
        });
    }

    private ProdutoEntity criarProduto(ProdutoDTO produtoDTO) {
        return produtoRepository.save(ProdutoEntity.builder()
                .id(produtoDTO.id())
                .nome(produtoDTO.nome())
                .preco(produtoDTO.preco())
                .build());
    }

    public boolean deleteComanda(Long id) {
        if (comandaRepository.existsById(id)) {
            comandaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
