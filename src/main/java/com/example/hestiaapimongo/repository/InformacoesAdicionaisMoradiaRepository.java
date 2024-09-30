package com.example.hestiaapimongo.repository;

import com.example.hestiaapimongo.models.InformacoesAdicionaisMoradia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InformacoesAdicionaisMoradiaRepository extends MongoRepository<InformacoesAdicionaisMoradia, String> {
    InformacoesAdicionaisMoradia findInformacoesAdicionaisMoradiaByIdMoradia (UUID id);

}
