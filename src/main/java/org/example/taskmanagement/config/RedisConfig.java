package org.example.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {
    
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        
        RedisCacheConfiguration baseConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                                       .serializeValuesWith(
                                               RedisSerializationContext.SerializationPair
                                                       .fromSerializer(new GenericJackson2JsonRedisSerializer())
                                       )
                                       .disableCachingNullValues();
        
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        
        cacheConfigs.put(
                "users",
                baseConfig.entryTtl(Duration.ofMinutes(10))
        );
        
        cacheConfigs.put(
                "tasks",
                baseConfig.entryTtl(Duration.ofMinutes(2))
        );
        
        return RedisCacheManager.builder(connectionFactory)
                                .cacheDefaults(baseConfig)
                                .withInitialCacheConfigurations(cacheConfigs)
                                .build();
    }
}
