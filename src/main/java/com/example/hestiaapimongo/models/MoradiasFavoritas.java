package com.example.hestiaapimongo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document("moradias_favoritas")
@Schema(description = "Moradias favoritas de um universitário")
public class MoradiasFavoritas {
    @Id
    @Schema(description = "Representa o id automático gerado pelo Mongo", example = "f51bb4362e")
    private String id;

    @Schema(description = "Representa o id do universitário que favoritou as moradias", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "O id do usuário deve ser preenchido")
    @Field("idUsuario")
    private UUID idUsuario;

    @Schema(description = "Representa os ids das moradias favoritadas", example = "['1', '2']")
    private List<UUID> moradias_ids;

    public MoradiasFavoritas(){}
    public MoradiasFavoritas(String id, UUID idUsuario, List<UUID> moradias_ids) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.moradias_ids = moradias_ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<UUID> getMoradias_ids() {
        return moradias_ids;
    }

    public void setMoradias_ids(List<UUID> moradias_ids) {
        this.moradias_ids = moradias_ids;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MoradiasFavoritas{");
        sb.append("id='").append(id).append('\'');
        sb.append(", idUsuario=").append(idUsuario);
        sb.append(", moradias_ids=").append(moradias_ids);
        sb.append('}');
        return sb.toString();
    }
}
