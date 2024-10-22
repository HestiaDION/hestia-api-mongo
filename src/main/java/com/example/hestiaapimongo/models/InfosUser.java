package com.example.hestiaapimongo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("info_user")
@Schema(description = "Informações do usuário para preencher banco do primeiro")
public class InfosUser {
    @Id
    @Schema(description = "Representa o id automático gerado pelo Mongo", example = "f51bb4362e")
    private String id;

    @Schema(description = "E-mail do universitário", example = "teste@gmail.com")
    @NotNull(message = "E-mail do usuário deve ser preenchido")
    @Field("email")
    private String email;
    @Schema(description = "Url da foto do universitário")
    @NotNull(message = "Url da foto do universitário deve ser preenchida")
    @Field("urlFoto")
    private String urlFoto;
    @Schema(description = "Senha do universitário", example = "senha1")
    @NotNull(message = "Senha do usuário deve ser preenchida")
    @Field("senha")
    private String senha;

    public InfosUser(){}

    public InfosUser(String id, String email, String urlFoto, String senha) {
        this.id = id;
        this.email = email;
        this.urlFoto = urlFoto;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("infosUser{");
        sb.append("id='").append(id).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", urlFoto='").append(urlFoto).append('\'');
        sb.append(", senha='").append(senha).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
