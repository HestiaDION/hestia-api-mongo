package com.example.hestiaapimongo.controllers;

import com.example.hestiaapimongo.models.FiltrosTags;
import com.example.hestiaapimongo.services.FiltrosTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@ControllerAdvice
@RestController
@RequestMapping("/filtros_tag")
public class FiltrosTagController {
    private final FiltrosTagService filtrosTagService;
    public FiltrosTagController(FiltrosTagService filtrosTagService) {
        this.filtrosTagService = filtrosTagService;
    }

    @GetMapping("/getFiltrosTag/{idUsuarioMoradia}")
    @Operation(summary = "Lista todos os filtros/tags de um usuário/moradia",
            description = "Retorna os filtros/tags de um usuário/moradia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtros/tags retornados com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public FiltrosTags getFiltrosTag(@Parameter(name = "idUsuarioMoradia", description = "É necessário o id do usuário/moradia", required = true) @PathVariable UUID idUsuarioMoradia) {
        return filtrosTagService.getFiltrosTagsByIdUsuarioMoradia(idUsuarioMoradia);
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
    @PutMapping("/updateFiltrosTag")
    @Operation(summary = "Atualiza os filtros/tags somente no Redis",
            description = "Retorna o filtro/tag atualizado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtro/tag atualizados com sucesso!",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public FiltrosTags updateFiltrosTagInRedis(@Parameter(name = "FiltrosTags", description = "É necessário um objeto de FiltrosTags") @RequestBody FiltrosTags filtrosTags) {
        return filtrosTagService.updateFiltrosTagInRedis(filtrosTags);
    }

    @GetMapping("/getFiltrosTag")
    @Operation(summary = "Lista todos os filtros/tags",
            description = "Retorna os filtros/tags cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtros/tags retornados com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public List<FiltrosTags> getAll() {
        return filtrosTagService.getAll();
    }
}