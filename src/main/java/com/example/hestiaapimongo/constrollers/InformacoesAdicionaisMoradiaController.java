package com.example.hestiaapimongo.constrollers;

import com.example.hestiaapimongo.models.FiltrosTags;
import com.example.hestiaapimongo.models.InformacoesAdicionaisMoradia;
import com.example.hestiaapimongo.services.InformacoesAdicionaisMoradiaService;
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
@RequestMapping("/infosMoradias")
public class InformacoesAdicionaisMoradiaController {
    private final InformacoesAdicionaisMoradiaService informacoesAdicionaisMoradiaService;
    public InformacoesAdicionaisMoradiaController(InformacoesAdicionaisMoradiaService informacoesAdicionaisMoradiaService) {
        this.informacoesAdicionaisMoradiaService = informacoesAdicionaisMoradiaService;
    }

    @GetMapping("/getInfosMoradias/{id_moradia}")
    @Operation(summary = "Lista todos as informações adicionais de uma moradia",
            description = "Retorna as informações adicionais de uma moradia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações da moradia retornadas com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public InformacoesAdicionaisMoradia getInfosMoradias(@Parameter(name = "id_moradia", description = "É necessário o id da moradia", required = true) @PathVariable UUID id_moradia) {
        return informacoesAdicionaisMoradiaService.getInformacoesAdicionaisMoradiaByIdMoradia(id_moradia);
    }

    @PostMapping("/addInfosMoradias")
    @Operation(summary = "Adiciona uma informação adicional de uma moradia",
            description = "Retorna a informação adicional adicionada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informação adicional adicionada com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public InformacoesAdicionaisMoradia addInfosMoradias (@Parameter(name = "InformacaoAdicionalMoradia", description = "É necessário um objeto de InformacoesAdicionaisMoradia") @RequestBody InformacoesAdicionaisMoradia infoMoradias) {
        return informacoesAdicionaisMoradiaService.addInformacoesAdicionaisMoradia(infoMoradias);
    }
}
