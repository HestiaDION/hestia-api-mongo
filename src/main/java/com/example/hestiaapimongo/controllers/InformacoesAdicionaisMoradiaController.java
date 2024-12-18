package com.example.hestiaapimongo.controllers;

import com.example.hestiaapimongo.models.FiltrosTags;
import com.example.hestiaapimongo.models.InformacoesAdicionaisMoradia;
import com.example.hestiaapimongo.services.InformacoesAdicionaisMoradiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> getInfosMoradias(@Parameter(name = "id_moradia", description = "É necessário o id da moradia", required = true) @PathVariable UUID id_moradia) {
        InformacoesAdicionaisMoradia informacoesAdicionaisMoradia;
        try {
            informacoesAdicionaisMoradia = informacoesAdicionaisMoradiaService.getInformacoesAdicionaisMoradiaByIdMoradia(id_moradia);
        } catch (RuntimeException r) {
            return new ResponseEntity<>(r.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(informacoesAdicionaisMoradia, HttpStatus.OK);
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
    public ResponseEntity<?> addInfosMoradias (@Parameter(name = "InformacaoAdicionalMoradia", description = "É necessário um objeto de InformacoesAdicionaisMoradia") @RequestBody InformacoesAdicionaisMoradia infoMoradias) {
        return new ResponseEntity<>(informacoesAdicionaisMoradiaService.addInformacoesAdicionaisMoradia(infoMoradias), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<InformacoesAdicionaisMoradia> getAll() {
        return informacoesAdicionaisMoradiaService.getAll();
    }
}
