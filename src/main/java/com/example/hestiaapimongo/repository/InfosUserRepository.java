package com.example.hestiaapimongo.repository;

import com.example.hestiaapimongo.models.InfosUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfosUserRepository extends MongoRepository<InfosUser, String> {
    InfosUser getInfosUserByEmail(String email);
}
