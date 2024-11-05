package com.example.hestiaapimongo.services;

import com.example.hestiaapimongo.models.InformacoesAdicionaisMoradia;
import com.example.hestiaapimongo.repository.InformacoesAdicionaisMoradiaRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class InformacoesAdicionaisMoradiaService {
    private final InformacoesAdicionaisMoradiaRepository repository;
    public InformacoesAdicionaisMoradiaService (InformacoesAdicionaisMoradiaRepository repository) {
        this.repository = repository;
    }

    // pegar todos as informações de uma moradia
    @Cacheable(value = "infosAdicionais", key = "#id")
    @CacheEvict(value = "infosAdicionais", key = "#id")
    public InformacoesAdicionaisMoradia getInformacoesAdicionaisMoradiaByIdMoradia(UUID id) throws RuntimeException {
        InformacoesAdicionaisMoradia infomacoesAdicionais = repository.findInformacoesAdicionaisMoradiaByIdMoradia(id);
        if (infomacoesAdicionais != null) {
            return infomacoesAdicionais;
        } else {
            throw new RuntimeException("Informações adicionais não encontradas!");
        }
    }

    // pegar tudo
    public List<InformacoesAdicionaisMoradia> getAll() {
        return repository.findAll();
    }

    // salvar
    @Transactional
    public InformacoesAdicionaisMoradia addInformacoesAdicionaisMoradia(InformacoesAdicionaisMoradia info) {
        return repository.save(info);
    }
}
