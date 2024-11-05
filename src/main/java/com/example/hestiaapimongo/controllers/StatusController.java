package com.example.hestiaapimongo.controllers;

import com.example.hestiaapimongo.models.FiltrosTags;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class StatusController {

    @GetMapping("/status")
    @Operation(summary = "Verifica o status da API",
            description = "Retorna um map contendo o status da API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status retornado com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FiltrosTags.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<Map<String, String>> getStatus() {
        // Cria um mapa com informações de status
        Map<String, String> status = new HashMap<>();
        status.put("status", "API is running");

        // Retorna o mapa como JSON
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
