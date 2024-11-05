package com.example.hestiaapimongo.controllers;


import com.example.hestiaapimongo.models.FiltrosTags;
import com.example.hestiaapimongo.models.InfosUser;
import com.example.hestiaapimongo.models.MoradiasFavoritas;
import com.example.hestiaapimongo.services.InfosUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("/infosUser")
public class InfosUserController {
    private final InfosUserService infosUserService;
    public InfosUserController(InfosUserService infosUserService) {
        this.infosUserService = infosUserService;
    }

    @PostMapping("/addInfosUser")
    @Operation(summary = "Adiciona informações do universitário",
            description = "Adiciona informações do universitário para preenchimento do banco do primeiro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações do universitário adicionadas com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InfosUser.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public InfosUser addInfosUser(@Parameter(name = "Informações Universitário", description = "É necessário um objeto de InfosUser") @RequestBody InfosUser infosUser) {
        return infosUserService.addInfosUser(infosUser);
    }

    @PatchMapping("/updateInfosUser/{email}")
    @Operation(summary = "Altera foto do universitário",
            description = "Altera foto do universitário para preenchimento do banco do primeiro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto do universitário atualizada com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InfosUser.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content())
    })
    public ResponseEntity<?> updateInfosUser(@Parameter(name = "E-mail do universitário", description = "É necessário o e-mail do universitário") @PathVariable String email, @Parameter(name = "Foto do universitário", description = "É necessária a nova url da foto do universitário") @RequestBody InfosUser infosUser) {
        InfosUser infos;
        try {
            infos = infosUserService.updateInfosUser(email, infosUser.getUrlFoto());
        } catch (RuntimeException r) {
            return new ResponseEntity<>(r.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }
}
