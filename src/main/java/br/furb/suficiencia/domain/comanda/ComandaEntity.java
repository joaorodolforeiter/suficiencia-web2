package br.furb.suficiencia.domain.comanda;

import br.furb.suficiencia.domain.produto.ProdutoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comandas")
public class ComandaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long idUsuario;

    @NotBlank
    @Column(nullable = false)
    private String nomeUsuario;

    @NotBlank
    @Column(nullable = false)
    private String telefoneUsuario;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProdutoEntity> produtos;

}
