package com.example.hestiaapimongo.repository;


import com.example.hestiaapimongo.models.FiltrosTags;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FiltrosTagRepository extends MongoRepository<FiltrosTags, String> {
    // buscar pelo id do usuário/moradia
    FiltrosTags getFiltrosTagsByIdUsuarioMoradia(UUID id);
}
