package com.lkf.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Redis缓存服务
 * Created by kaifeng on 2017/5/13.
 */
@Configuration
@EnableCaching
public class RedisCacheService extends CachingConfigurerSupport {

	/**
	 * @MethodName: keyGenerator
	 * @Description: 生成缓存key的策略，根据方法名和参数生成key
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:53
	 * @Param: []
	 * @Return: org.springframework.cache.interceptor.KeyGenerator
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method,
			                       Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	/**
	 * @MethodName: cacheManager
	 * @Description: 缓存管理，设置过期时间
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:55
	 * @Param: [redisTemplate]
	 * @Return: org.springframework.cache.CacheManager
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		// 设置缓存过期时间
		// rcm.setDefaultExpiration(60);//秒
		// 设置value的过期时间
		Map<String, Long> map = new HashMap();
		map.put("test", 60L);
		rcm.setExpires(map);
		return rcm;
	}


	/**
	 * @MethodName: redisTemplate
	 * @Description: RedisTemplate配置
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:56
	 * @Param: [factory] redis链接工厂
	 * @Return: org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
}
