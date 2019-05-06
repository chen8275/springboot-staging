 /*
  * Copyright 2019 tuhu.cn All right reserved. This software is the
  * confidential and proprietary information of tuhu.cn ("Confidential
  * Information"). You shall not disclose such Confidential Information and shall
  * use it only in accordance with the terms of the license agreement you entered
  * into with Tuhu.cn
  */
 package com.example.demo.core.configuration;
 
 import com.example.demo.model.UserInfo;
 import com.fasterxml.jackson.annotation.JsonAutoDetect;
 import com.fasterxml.jackson.annotation.PropertyAccessor;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.springframework.cache.CacheManager;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.data.redis.cache.RedisCacheConfiguration;
 import org.springframework.data.redis.cache.RedisCacheManager;
 import org.springframework.data.redis.connection.RedisConnectionFactory;
 import org.springframework.data.redis.core.RedisTemplate;
 import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
 import org.springframework.data.redis.serializer.RedisSerializationContext;
 import org.springframework.data.redis.serializer.RedisSerializer;
 import org.springframework.data.redis.serializer.StringRedisSerializer;


 import java.time.Duration;

 /**
  * @author chendesheng chendesheng@tuhu.cn
  * @since 2019/5/6 16:11
  */
 @Configuration
 public class RedisConfiguration {
    
     @Bean
     public RedisTemplate<Object, UserInfo> redisTemplate(RedisConnectionFactory redisConnectionFactory){
         RedisTemplate<Object,UserInfo> template = new RedisTemplate<Object, UserInfo>();
         template.setConnectionFactory(redisConnectionFactory);
         Jackson2JsonRedisSerializer<UserInfo> serializer = new Jackson2JsonRedisSerializer<UserInfo>(UserInfo.class);
         template.setDefaultSerializer(serializer);
         return template;
     }
    
     @Bean
     public CacheManager cacheManager(RedisConnectionFactory factory) {
         RedisSerializer<String> redisSerializer = new StringRedisSerializer();
         Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        
         //解决查询缓存转换异常的问题
         ObjectMapper om = new ObjectMapper();
         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
         jackson2JsonRedisSerializer.setObjectMapper(om);
        
         // 配置序列化（解决乱码的问题）,过期时间30秒
         RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                 .entryTtl(Duration.ofSeconds(30))
                 .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                 .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                 .disableCachingNullValues();
        
         RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                 .cacheDefaults(config)
                 .build();
         return cacheManager;
     }
     
 }
