package com.example.hestiaapimongo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Document("imagens_moradia")
@Schema(description = "Images da Moradia")
public class ImagensMoradia implements Serializable {
    @Id
    @Schema(description = "Representa o id automático gerado pelo Mongo", example = "f51bb4362e")
    private String id;

    @Schema(description = "id da moradia que possui as fotos", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "O id da moradia deve ser preenchido")
    @Field("id_moradia")
    private UUID idMoradia;

    @Schema(description = "Lista das imagens da moradia", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Lista não pode estar vazia!")
    private List<String> imagens;

    public ImagensMoradia(String id, UUID idMoradia, List<String> imagens) {
        this.id = id;
        this.idMoradia = idMoradia;
        this.imagens = imagens;
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

    public List<String> getImagens() {
        return imagens;
    }

    public void setImagens(List<String> imagens) {
        this.imagens = imagens;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImagensMoradia{");
        sb.append("id='").append(id).append('\'');
        sb.append(", idMoradia=").append(idMoradia);
        sb.append(", imagens=").append(imagens);
        sb.append('}');
        return sb.toString();
    }
}
