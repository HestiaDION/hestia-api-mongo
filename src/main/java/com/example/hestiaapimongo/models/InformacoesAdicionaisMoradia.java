package com.example.hestiaapimongo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document("informacoes_adicionais_moradia")
@Schema(description = "Informações adicionais da moradia")
public class InformacoesAdicionaisMoradia {
    @Id
    @Schema(description = "Representa o id automático gerado pelo Mongo", example = "f51bb4362e")
    private String id;

    @Schema(description = "id da moradia que possui as informações adicionais", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "O id da moradia deve ser preenchido")
    @Field("id_moradia")
    private UUID idMoradia;

    @Schema(description = "informações adicionais", example = "['3 banheiros', '2 quartos']")
    private List<String> topicos;

    public InformacoesAdicionaisMoradia(){}
    public InformacoesAdicionaisMoradia(String id, UUID idMoradia, List<String> topicos) {
        this.id = id;
        this.idMoradia = idMoradia;
        this.topicos = topicos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getIdMoradia() {
        return idMoradia;
    }

    public void setIdMoradia(UUID idMoradia) {
        this.idMoradia = idMoradia;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return "informacoes_adicionais_moradia{" +
                "id='" + id + '\'' +
                ", id_moradia=" + idMoradia + '\'' +
                ", topicos=" + topicos + '\'' +
                '}';
    }
}
