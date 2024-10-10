package com.example.hestiaapimongo.controllers;

import com.example.hestiaapimongo.models.FiltrosTags;
import com.example.hestiaapimongo.models.MoradiasFavoritas;
import com.example.hestiaapimongo.services.MoradiasFavoritasService;
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
@RequestMapping("/moradiasFavoritas")
public class MoradiasFavoritasController {
    private final MoradiasFavoritasService moradiasFavoritasService;
    public MoradiasFavoritasController(MoradiasFavoritasService moradiasFavoritasService) {
        this.moradiasFavoritasService = moradiasFavoritasService;
    }

    @GetMapping("/getMoradiasFavoritas/{id_usuario}")
    @Operation(summary = "Lista todos as moradias favoritadas de um usuário",
            description = "Retorna os ids das moradias favoritadas de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Moradias favoritas retornadas com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public MoradiasFavoritas getMoradiasFavoritas(@Parameter(name = "id_usuario", description = "É necessário o id do universitário", required = true) @PathVariable UUID id_usuario) {
        return moradiasFavoritasService.getMoradiasFavoritasByIdUsuario(id_usuario);
    }

    @PostMapping("/addMoradiasFavoritas")
    @Operation(summary = "Adiciona uma moradia favorita",
            description = "Retorna a moradia favorita adicionada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Moradia favorita adicionada com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public MoradiasFavoritas addMoradiasFavoritas (@Parameter(name = "MoradiasFavoritas", description = "É necessário um objeto de MoradiasFavoritas") @RequestBody MoradiasFavoritas moradiasFavoritas) {
        return moradiasFavoritasService.addMoradiasFavoritas(moradiasFavoritas);
    }

    @DeleteMapping("/deleteMoradiasFavoritas/{id_usuario}/{id_moradia}")
    @Operation(summary = "Remove uma moradia favorita",
            description = "Retorna o id da moradia deletada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Moradia favorita deletada com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public UUID deleteMoradiasFavoritas (@Parameter(name = "id_usuario", description = "É necessário o id do usuário") @PathVariable UUID id_usuario, @Parameter(name = "id_moradia", description = "É necessário o id da moradia a ser deletada") @PathVariable UUID id_moradia) {
        return moradiasFavoritasService.deleteMoradiasFavoritas(id_usuario, id_moradia);
    }
}
