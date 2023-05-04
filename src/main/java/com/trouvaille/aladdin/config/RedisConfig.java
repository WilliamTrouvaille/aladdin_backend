/**
 * @projectName: aladdin
 * @package: com.example.aladdin.config
 * @className: RedisConfig
 * @author: William_Trouvaille
 * @description: Redis设置
 * @date: 2022/7/26 14:59
 * @version: 1.0
 */
package com.trouvaille.aladdin.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    
    /**
     * @param redisConnectionFactory:
     * @return RedisTemplate < Object, Object>
     * @author William_Trouvaille
     * @description 设置Redis序列化器
     * @date 2022/07/26 15:00
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate (RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
