package com.example.hestiaapimongo.constrollers;

import com.example.hestiaapimongo.models.FiltrosTags;
import com.example.hestiaapimongo.services.FiltrosTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@ControllerAdvice
@RestController
@RequestMapping("/filtros_tag")
public class FiltrosTagController {
    private final FiltrosTagService filtrosTagService;
    public FiltrosTagController(FiltrosTagService filtrosTagService) {
        this.filtrosTagService = filtrosTagService;
    }

    @GetMapping("/getFiltrosTag/{id_usuario_moradia}")
    @Operation(summary = "Lista todos os filtros/tags de um usuário/moradia",
            description = "Retorna os filtros/tags de um usuário/moradia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtros/tags retornados com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public FiltrosTags getFiltrosTag(@Parameter(name = "id_usuario_moradia", description = "É necessário o id do usuário/moradia", required = true) @PathVariable UUID id_usuario_moradia) {
        return filtrosTagService.getFiltrosTagsByIdUsuarioMoradia(id_usuario_moradia);
    }

    @PostMapping("/addFiltrosTag")
    @Operation(summary = "Adiciona um filtro/tag",
            description = "Retorna o filtro/tag adicionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtro/tag adicionado com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public FiltrosTags addFiltrosTags(@Parameter(name = "FiltrosTags", description = "É necessário um objeto de FiltrosTags") @RequestBody FiltrosTags filtrosTags) {
        return filtrosTagService.addFiltrosTag(filtrosTags);
    }
}