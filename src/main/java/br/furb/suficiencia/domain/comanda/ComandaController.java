package br.furb.suficiencia.domain.comanda;

import br.furb.suficiencia.domain.comanda.dto.ComandaRequest;
import br.furb.suficiencia.domain.comanda.dto.ComandaDetailedResponse;
import br.furb.suficiencia.domain.comanda.dto.ComandaResponse;
import br.furb.suficiencia.domain.comanda.dto.ComandaUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/RestAPIFurb")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Comanda", description = "API para gerenciamento de comandas")
public class ComandaController {

    private final ComandaMapper comandaMapper;
    private final ComandaService comandaService;

    @GetMapping("/comandas")
    @Operation(summary = "Listar todas as comandas", description = "Retorna lista de todas as comandas")
    public ResponseEntity<List<ComandaResponse>> getAllComandas() {
        var comandas = comandaService.getAllComandas().stream()
                .map(comandaMapper::toDTO)
                .toList();

        return ResponseEntity.ok(comandas);
    }

    @GetMapping("/comandas/{id}")
    @Operation(summary = "Buscar comanda por ID", description = "Retorna uma comanda específica pelo ID")
    public ResponseEntity<ComandaDetailedResponse> getComandaById(
            @Parameter(description = "ID da comanda", required = true)
            @PathVariable Long id
    ) {
        var comanda = comandaService.getComandaById(id).map(comandaMapper::toDetailedDTO);

        return comanda.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/comandas")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Criar nova comanda", description = "Cria uma nova comanda (requer autenticação)")
    public ResponseEntity<ComandaDetailedResponse> createComanda(
            @Parameter(description = "Dados da comanda", required = true)
            @Valid @RequestBody ComandaRequest request
    ) {
        var comanda = comandaService.createComanda(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(comandaMapper.toDetailedDTO(comanda));
    }

    @PutMapping("/comandas/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Atualizar comanda", description = "Atualiza uma comanda existente (requer autenticação)")
    public ResponseEntity<ComandaDetailedResponse> updateComanda(
            @Parameter(description = "ID da comanda", required = true)
            @PathVariable Long id,
            @Parameter(description = "Dados para atualização", required = true)
            @Valid @RequestBody ComandaUpdateRequest request
    ) {
        var updatedComanda = comandaService.updateComanda(id, request).map(comandaMapper::toDetailedDTO);

        return updatedComanda.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/comandas/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar comanda", description = "Remove uma comanda (requer autenticação de admin)")
    public ResponseEntity<?> deleteComanda(
            @Parameter(description = "ID da comanda", required = true)
            @PathVariable Long id
    ) {
        boolean deleted = comandaService.deleteComanda(id);

        if (deleted) {
            var response = Map.of(
                    "sucess", Map.of(
                            "text", "comanda removida"
                    )
            );

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
