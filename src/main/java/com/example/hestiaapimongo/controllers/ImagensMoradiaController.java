package com.example.hestiaapimongo.controllers;

import com.example.hestiaapimongo.models.ImagensMoradia;
import com.example.hestiaapimongo.services.ImagensMoradiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@ControllerAdvice
@RestController
@RequestMapping("/imagens_moradia")
public class ImagensMoradiaController {
    private final ImagensMoradiaService imagensMoradiaService;
    public ImagensMoradiaController(ImagensMoradiaService imagensMoradiaService) {
        this.imagensMoradiaService = imagensMoradiaService;
    }

    // salvar
    @PostMapping("/addImagensMoradia")
    @Operation(summary = "Adiciona as imagens de uma moradia",
            description = "Retorna as imagens de uma moradia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagens da moradia adicionadas com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImagensMoradia.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<?> addImagensMoradia(@Parameter(name = "ImagensMoradia", description = "É necessário um objeto de ImagensMoradia") @RequestBody ImagensMoradia imagensMoradia) {
        return new ResponseEntity<>(imagensMoradiaService.addImagensMoradia(imagensMoradia), HttpStatus.CREATED);
    }

    // get
    @GetMapping("/getImagensMoradia/{id_moradia}")
    @Operation(summary = "Lista todas as imagens de uma moradia",
            description = "Retorna as imagens de uma moradia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagens da moradia retornadas com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImagensMoradia.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<?> getImagensMoradia(@Parameter(name = "id_moradia", description = "É necessário o id da moradia", required = true) @PathVariable UUID id_moradia) {
        ImagensMoradia imagensMoradia;
        try {
            imagensMoradia = imagensMoradiaService.getImagensMoradiaByIdMoradia(id_moradia);
        } catch (RuntimeException r) {
            return new ResponseEntity<>(r.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(imagensMoradia, HttpStatus.OK);
    }
}
