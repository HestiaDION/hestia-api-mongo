package com.example.hestiaapimongo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document("filtros_tags")
@Schema(description = "Filtros/tags dos universitários e moradias")
public class FiltrosTags {
    @Id
    @Schema(description = "Representa o id automático gerado pelo Mongo", example = "f51bb4362e")
    private String id;
    @Schema(description = "Representa o id do usuário", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "O id do usuário/moradia deve ser preenchido")
    @Field("idUsuarioMoradia")
    private UUID idUsuarioMoradia;
    @Schema(description = "Representa o tipo dos filtros/tags", example = "universitario")
    @NotNull(message = "O tipo deve ser preenchido")
    private String tipo;
    @Schema(description = "Representa os animais de estimação que os usuários podem aceitar ou não", example = "['cachorro', 'gato']")
    private List<String> animais_estimacao;
    @Schema(description = "Representa o gênero que o usuário se sente mais confortável em morar junto", example = "['masculino', 'feminino']")
    private List<String> preferencia_genero;
    @Schema(description = "Representa o número máximo de pessoas que podem morar no local", example = "1")
    private String numero_maximo_pessoas;
    @Schema(description = "Representa a frequência de fumo aceita pelo usuário", example = "'Não fumo'")
    private String frequencia_fumo;
    @Schema(description = "Representa a frequência de consumo de bebida aceita pelo usuário", example = "'Bebo frequentemente'")
    private String frequencia_bebida;
    @Schema(description = "Representa as preferências em relação a preferência de imóveis e outros", example = "['Quintal', 'Garagem']")
    private List<String> preferencias_moveis_outro;

    // construtores
    public FiltrosTags(){}
    public FiltrosTags(String id, UUID idUsuarioMoradia, String tipo, List<String> animais_estimacao, List<String> preferencia_genero, String numero_maximo_pessoas, String frequencia_fumo, String frequencia_bebida, List<String> preferencias_moveis_outro) {
        this.id = id;
        this.idUsuarioMoradia = idUsuarioMoradia;
        this.tipo = tipo;
        this.animais_estimacao = animais_estimacao;
        this.preferencia_genero = preferencia_genero;
        this.numero_maximo_pessoas = numero_maximo_pessoas;
        this.frequencia_fumo = frequencia_fumo;
        this.frequencia_bebida = frequencia_bebida;
        this.preferencias_moveis_outro = preferencias_moveis_outro;
    }

    // getters e setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getIdUsuarioMoradia() {
        return idUsuarioMoradia;
    }

    public void setIdUsuarioMoradia(UUID idUsuarioMoradia) {
        this.idUsuarioMoradia = idUsuarioMoradia;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getAnimais_estimacao() {
        return animais_estimacao;
    }

    public void setAnimais_estimacao(List<String> animais_estimacao) {
        this.animais_estimacao = animais_estimacao;
    }

    public List<String> getPreferencia_genero() {
        return preferencia_genero;
    }

    public void setPreferencia_genero(List<String> preferencia_genero) {
        this.preferencia_genero = preferencia_genero;
    }

    public String getNumero_maximo_pessoas() {
        return numero_maximo_pessoas;
    }

    public void setNumero_maximo_pessoas(String numero_maximo_pessoas) {
        this.numero_maximo_pessoas = numero_maximo_pessoas;
    }

    public String getFrequencia_fumo() {
        return frequencia_fumo;
    }

    public void setFrequencia_fumo(String frequencia_fumo) {
        this.frequencia_fumo = frequencia_fumo;
    }

    public String getFrequencia_bebida() {
        return frequencia_bebida;
    }

    public void setFrequencia_bebida(String frequencia_bebida) {
        this.frequencia_bebida = frequencia_bebida;
    }

    public List<String> getPreferencias_moveis_outro() {
        return preferencias_moveis_outro;
    }

    public void setPreferencias_moveis_outro(List<String> preferencias_moveis_outro) {
        this.preferencias_moveis_outro = preferencias_moveis_outro;
    }

    // toString
    @Override
    public String toString() {
        return "filtros_tags{" +
                "id='" + id + '\'' +
                ", idUsuarioMoradia=" + idUsuarioMoradia + '\'' +
                ", animais_estimacao=" + animais_estimacao + '\'' +
                ", preferencia_genero=" + preferencia_genero + '\'' +
                ", numero_maximo_pessoas=" + numero_maximo_pessoas + '\'' +
                ", frequencia_fumo='" + frequencia_fumo + '\'' +
                ", frequencia_bebida='" + frequencia_bebida + '\'' +
                ", preferencias_moveis_outro=" + preferencias_moveis_outro + '\'' +
                '}';
    }

}
