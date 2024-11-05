package com.example.hestiaapimongo.repository;

import com.example.hestiaapimongo.models.MoradiasFavoritas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MoradiasFavoritasRepository extends MongoRepository<MoradiasFavoritas, String> {
    MoradiasFavoritas findMoradiasFavoritasByIdUsuario(UUID id);
}
