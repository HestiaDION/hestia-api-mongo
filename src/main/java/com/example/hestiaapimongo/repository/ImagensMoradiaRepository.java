package com.example.hestiaapimongo.repository;

import com.example.hestiaapimongo.models.ImagensMoradia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ImagensMoradiaRepository extends MongoRepository<ImagensMoradia, String> {
    ImagensMoradia getImagensMoradiaByIdMoradia(UUID id_moradia);
}
