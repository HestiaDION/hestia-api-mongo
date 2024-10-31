package com.example.hestiaapimongo.services;

import com.example.hestiaapimongo.models.ImagensMoradia;
import com.example.hestiaapimongo.repository.ImagensMoradiaRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
public class ImagensMoradiaService {
    private final ImagensMoradiaRepository imagensMoradiaRepository;
    public ImagensMoradiaService(ImagensMoradiaRepository imagensMoradiaRepository) {
        this.imagensMoradiaRepository = imagensMoradiaRepository;
    }

    // cadastrar
    @Transactional
    @CachePut(value = "imagensMoradia", key = "#imagensMoradia.idMoradia")
    @CacheEvict(value = "imagensMoradia", key = "#imagensMoradia.idMoradia")
    public ImagensMoradia addImagensMoradia(ImagensMoradia imagensMoradia) {
        return imagensMoradiaRepository.save(imagensMoradia);
    }

    // get
    @Cacheable(value = "imagensMoradia", key = "#id")
    @CacheEvict(value = "imagensMoradia", key = "#id")
    public ImagensMoradia getImagensMoradiaByIdMoradia(UUID id) throws RuntimeException {
        ImagensMoradia imagensMoradia = imagensMoradiaRepository.getImagensMoradiaByIdMoradia(id);
        if (imagensMoradia != null) {
            return imagensMoradia;
        } else {
            throw new RuntimeException("Imagens da moradia não encontradas!");
        }
    }
}
