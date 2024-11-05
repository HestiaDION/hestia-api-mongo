package com.example.hestiaapimongo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.UUID;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(System.getenv("REDIS_HOST"));
        config.setPort(Integer.parseInt(System.getenv("REDIS_PORT")));
        config.setPassword(System.getenv("REDIS_PASSWORD"));

        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // Definir o serializador para as chaves
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);

        // Configurar o ObjectMapper sem o método setObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);

        // Configurar suporte a UUID no Jackson
        SimpleModule module = new SimpleModule();
        module.addSerializer(UUID.class, new UUIDSerializer());
        objectMapper.registerModule(module);

        // Definir o serializador para os valores (usando Jackson para JSON)
        Jackson2JsonRedisSerializer<ObjectMapper> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, ObjectMapper.class);

        // Definir o serializador de valor no RedisTemplate
        template.setValueSerializer(jackson2JsonRedisSerializer);

        return template;
    }
}
