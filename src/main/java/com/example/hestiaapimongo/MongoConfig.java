package com.example.hestiaapimongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        ConnectionString connectionString = new ConnectionString(System.getenv("MONGO_URI"));

        // Definir a representação do UUID
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)  // Definindo a representação UUID
                .build();

        MongoClient mongoClient = MongoClients.create(mongoClientSettings);

        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient, System.getenv("MONGO_DB")));
    }
}
