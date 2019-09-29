package com.hh.wx.xcx.configer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
* @author hh
* @date 2019年9月18日 下午4:33:51
* @desc 类说明
*/
@Slf4j
@Configuration
public class RedisConfiger {
	
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

	//@Bean
	public JedisConnectionFactory redisConnectionFactory(){
		JedisConnectionFactory factory = new JedisConnectionFactory();

		/*String host = CustomPropertyPlaceholderConfigurer.getProperty("spring.redis.host","");
		int port = Integer.valueOf(CustomPropertyPlaceholderConfigurer.getProperty("spring.redis.port","6379"));
		String password = CustomPropertyPlaceholderConfigurer.getProperty("spring.redis.password","");
		int database = Integer.valueOf(CustomPropertyPlaceholderConfigurer.getProperty("spring.redis.database","10"));

		log.info("redis连接信息。host:{} port:{} database:{} 是否存在password:{}",host,port,database,!StringUtils.isEmpty(password));

		factory.setHostName(host);
		factory.setPort(port);
		factory.setTimeout(Integer.valueOf(CustomPropertyPlaceholderConfigurer.getProperty("spring.redis.timeout","5000")));

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(16);
		jedisPoolConfig.setMaxIdle(8);
		jedisPoolConfig.setMinIdle(1);
		factory.setPoolConfig(jedisPoolConfig);

		if(!StringUtils.isEmpty(password)){
			factory.setPassword(password);
		}

		factory.setDatabase(database);*/

		return factory;
	}
}
