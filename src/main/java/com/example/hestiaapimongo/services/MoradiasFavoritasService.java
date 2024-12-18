package com.example.hestiaapimongo.services;

import com.example.hestiaapimongo.models.MoradiasFavoritas;
import com.example.hestiaapimongo.repository.MoradiasFavoritasRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MoradiasFavoritasService {
    private final MoradiasFavoritasRepository moradiasFavoritasRepository;
    public MoradiasFavoritasService(MoradiasFavoritasRepository moradiasFavoritasRepository) {
        this.moradiasFavoritasRepository = moradiasFavoritasRepository;
    }

    // todas as moradias salvas de um usuário
    // Recuperar as moradias favoritas do cache ou banco de dados
    @Cacheable(value = "moradiasFav", key = "#id")
    @CacheEvict(value = "moradiasFav", key = "#id")
    public MoradiasFavoritas getMoradiasFavoritasByIdUsuario(UUID id) throws RuntimeException {
        MoradiasFavoritas moradiasFavoritas = moradiasFavoritasRepository.findMoradiasFavoritasByIdUsuario(id);
        if (moradiasFavoritas != null) {
            return moradiasFavoritas;
        } else {
            throw new RuntimeException("Moradias favoritas não encontradas");
        }
    }

    // Salvar nova moradia favorita e atualizar o cache
    @Transactional
    public MoradiasFavoritas addMoradiasFavoritas(MoradiasFavoritas moradiasFavoritas) {
        // Busca o documento de favoritos pelo id do usuário
        MoradiasFavoritas favoritoExistente = moradiasFavoritasRepository.findMoradiasFavoritasByIdUsuario(moradiasFavoritas.getIdUsuario());

        if (favoritoExistente != null) {
            favoritoExistente.setMoradias_ids(moradiasFavoritas.getMoradias_ids());
            return moradiasFavoritasRepository.save(favoritoExistente);
        } else {
            // Caso o documento não exista, cria um novo
            MoradiasFavoritas novoFavorito = new MoradiasFavoritas();
            novoFavorito.setIdUsuario(moradiasFavoritas.getIdUsuario());
            novoFavorito.setMoradias_ids(moradiasFavoritas.getMoradias_ids());
            return moradiasFavoritasRepository.save(novoFavorito);
        }
    }

    // Remover moradia favorita do banco e do cache
    @Transactional
    @CacheEvict(value = "moradiasFav", key = "#id_usuario")
    public MoradiasFavoritas deleteMoradiasFavoritas(UUID id_usuario, UUID id_moradia) throws RuntimeException {
        MoradiasFavoritas moradiasFavoritas;
        try {
            moradiasFavoritas = getMoradiasFavoritasByIdUsuario(id_usuario);
        } catch (RuntimeException r) {
            throw new RuntimeException(r.getMessage());
        }

        List<UUID> moradias_ids = moradiasFavoritas.getMoradias_ids();

        if (moradias_ids.remove(id_moradia)) {
            moradiasFavoritas.setMoradias_ids(moradias_ids);
            moradiasFavoritasRepository.save(moradiasFavoritas);
            return moradiasFavoritas;
        } else {
            throw new RuntimeException("Nenhuma moradia foi encontrada para ser removida!");
        }
    }
}
