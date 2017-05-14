package com.lkf.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by kaifeng on 2017/5/13.
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtilService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;


	/**
	 * @MethodName: remove
	 * @Description: 批量删除对应的value
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:46
	 * @Param: [keys] 被删除的key
	 * @Return: void
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * @MethodName: removePattern
	 * @Description: 批量删除对应的value
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:46
	 * @Param: [pattern] 被删除的key
	 * @Return: void
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}


	/**
	 * @MethodName: remove
	 * @Description: 删除对应的value
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:48
	 * @Param: [key] 被删除的key
	 * @Return: void
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}


	/**
	 * @MethodName: exists
	 * @Description: 判断缓存中是否有对应的value
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:48
	 * @Param: [key] 被判断的key
	 * @Return: boolean
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}


	/**
	 * @MethodName: get
	 * @Description: 读取缓存
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:49
	 * @Param: [key] 获取缓存的key
	 * @Return: java.lang.Object
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate
				.opsForValue();
		result = operations.get(key);
		return result;
	}


	/**
	 * @MethodName: set
	 * @Description: 写入缓存
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:50
	 * @Param: [key, value] 写入缓存的键值
	 * @Return: boolean
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * @MethodName: set
	 * @Description: 写入缓存并设置过期时间
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:51
	 * @Param: [key, value, expireTime] 写入缓存的键值和过期时间（秒）
	 * @Return: boolean
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @MethodName: incr
	 * @Description: 将自增变量写入缓存
	 * @Author: 刘凯峰
	 * @Date: 2017/4/21 10:51
	 * @Param: [key, seqNo] 自增变量和增长值
	 * @Return: boolean
	 * @Version V1.0
	 * UpDate Logs:方法变更说明
	 * ****************************************************
	 * Name:
	 * Date:
	 * Description:
	 * *****************************************************
	 */
	public String incr(String key) {
		Long result = 0L;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			result=operations.increment(key, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(result);
	}


}
