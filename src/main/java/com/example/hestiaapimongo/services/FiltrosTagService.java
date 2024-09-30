package com.example.hestiaapimongo.services;

import com.example.hestiaapimongo.models.FiltrosTags;
import com.example.hestiaapimongo.repository.FiltrosTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FiltrosTagService {
    private final FiltrosTagRepository filtrosTagRepository;

    public FiltrosTagService(FiltrosTagRepository filtrosTagRepository) {
        this.filtrosTagRepository = filtrosTagRepository;
    }

    // buscar todos os filtros/tags de um usuário/moradia
    public FiltrosTags getFiltrosTagsByIdUsuarioMoradia (UUID id) throws RuntimeException{
        FiltrosTags filtrosTags = filtrosTagRepository.getFiltrosTagsByIdUsuarioMoradia(id);
        if (filtrosTags != null) {
            return filtrosTags;
        } else {
            throw new RuntimeException("Filtros/Tags não encontrados!");
        }
    }

    // salvar
    @Transactional
    public FiltrosTags addFiltrosTag(FiltrosTags filtrosTags) {
        return filtrosTagRepository.save(filtrosTags);
    }
}
