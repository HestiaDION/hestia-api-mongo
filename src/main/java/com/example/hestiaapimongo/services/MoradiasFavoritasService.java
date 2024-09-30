package com.example.hestiaapimongo.services;

import com.example.hestiaapimongo.models.MoradiasFavoritas;
import com.example.hestiaapimongo.repository.MoradiasFavoritasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MoradiasFavoritasService {
    private final MoradiasFavoritasRepository moradiasFavoritasRepository;
    public MoradiasFavoritasService(MoradiasFavoritasRepository moradiasFavoritasRepository) {
        this.moradiasFavoritasRepository = moradiasFavoritasRepository;
    }

    // todas as moradias salvas de um usuário
    public MoradiasFavoritas getMoradiasFavoritasByIdUsuario(UUID id) throws RuntimeException{
        MoradiasFavoritas moradiasFavoritas = moradiasFavoritasRepository.findMoradiasFavoritasByIdUsuario(id);
        if (moradiasFavoritas != null) {
            return moradiasFavoritas;
        } else {
            throw new RuntimeException("Moradias favoritas não encontradas");
        }
    }

    // salvar
    @Transactional
    public MoradiasFavoritas addMoradiasFavoritas(MoradiasFavoritas moradiasFavoritas) {
        return moradiasFavoritasRepository.save(moradiasFavoritas);
    }

    // deletar
    @Transactional
    public UUID deleteMoradiasFavoritas(UUID id_usuario, UUID id_moradia) throws RuntimeException{
        MoradiasFavoritas moradiasFavoritas = getMoradiasFavoritasByIdUsuario(id_usuario);
        List<UUID> moradias_ids = moradiasFavoritas.getMoradias_ids();

        if (moradias_ids.remove(id_moradia)) {
            return id_moradia;
        } else {
            throw new RuntimeException("Nenhuma moradia foi encontrada para ser removida!");
        }
    }
}
