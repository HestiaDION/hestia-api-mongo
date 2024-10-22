package com.example.hestiaapimongo.services;

import com.example.hestiaapimongo.models.InfosUser;
import com.example.hestiaapimongo.repository.InfosUserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfosUserService {
    private final InfosUserRepository infosUserRepository;
    public InfosUserService(InfosUserRepository infosUserRepository) {
        this.infosUserRepository = infosUserRepository;
    }

    // salvar
    @Transactional
    @CachePut(value = "infosUser", key = "#infosUser.email")
    @CacheEvict(value = "infosUser", key = "#infosUser.email")
    public InfosUser addInfosUser(InfosUser infosUser) {
        return infosUserRepository.save(infosUser);
    }

    // get
    @Cacheable(value = "infosUser", key = "#email")
    @CacheEvict(value = "infosUser", key = "#email")
    public InfosUser getInfosUserByEmail(String email) throws RuntimeException{
        InfosUser infosUser = infosUserRepository.getInfosUserByEmail(email);
        if (infosUser != null) {
            return infosUser;
        } else {
            throw new RuntimeException("Informações do usuário não encontradas!");
        }
    }

    // atualizar foto
    @Transactional
    @CachePut(value = "infosUser", key = "#email")
    @CacheEvict(value = "infosUser", key = "#email")
    public InfosUser updateInfosUser(String email, String urlFoto) throws RuntimeException{
        InfosUser infosUser;
        try {
            infosUser = getInfosUserByEmail(email);
            infosUser.setUrlFoto(urlFoto);
        } catch (RuntimeException r) {
            throw new RuntimeException(r.getMessage());
        }
        return infosUserRepository.save(infosUser);
    }
}
