package com.lkf.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.tcsl.slyun.starter.redis
 * @ClassName: RedisUtilService
 * @Description: Redis工具类封装
 * @Author: 刘凯峰
 * @Date: 2017/4/21 10:45
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
     * @Date: 2017/5/24 10:51
     * @Param: key 自增变量
     * @Return: boolean
     * @Version V1.0
     * UpDate Logs:方法变更说明
     * ****************************************************
     * Name:
     * Date:
     * Description:
     * *****************************************************
     */
    public String incr(String key, Long seed) {
        Long result = 0L;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate
                    .opsForValue();
            result = operations.increment(key, seed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }
    /**
     * @method-name: getIdentity
     * @description: 根据redis自增，生成规则编码(指定种子值)
     * @author: 刘凯峰
     * @date: 2017/5/24 16:11
     * @param: key 自增序列KEY
     * @param: seed 自增种子值
     * @param: prefix 序列前缀
     * @return: java.lang.String
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    public String getAutoSequenceCode(String key, Long seed, String prefix) {
        String result = null;/*最终结果*/
        String increment=null;/*Redis中的自增值*/
        StringBuffer stringBuffer = new StringBuffer();
        if (seed==null)
            increment= incr(key, 1L);
        else
            increment=incr(key, seed);
        synchronized (this) {
            if (increment.length() < 6) {
                for (int i = 0; i < 6 - increment.length(); i++) {
                    stringBuffer.append("0");
                }
                result = prefix + stringBuffer + increment;
            } else {
                result = prefix + increment;
            }
        }
        return result;
    }
    /**
     * @method-name: getAutoSequence
     * @description: 根据redis自增，生成规则编码（1为默认种子值）
     * @author: 刘凯峰
     * @date: 2017/5/24 16:16
     * @param: key 自增序列KEY
     * @param: prefix 序列前缀
     * @return: java.lang.String
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    public String getAutoSequenceCode(String key,String prefix){
        return getAutoSequenceCode(key,null,prefix);
    }
    /**
     * @method-name: getAutoSequenceId
     * @description: 利用redis自增获取自增序列(指定种子值)
     * @author: 刘凯峰
     * @date: 2017/5/24 16:39
     * @param: [key, seed]
     * @return: java.lang.String
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    public String getAutoSequenceId(String key, Long seed) {

        String increment=null;/*Redis中的自增值*/
        StringBuffer stringBuffer = new StringBuffer();
        if (seed==null)
            increment= incr(key, 1L);
        else
            increment=incr(key, seed);
        return increment;
    }
    /**
     * @method-name: getAutoSequenceId
     * @description: 利用redis自增获取自增序列(1为种子值)
     * @author: 刘凯峰
     * @date: 2017/5/24 16:39
     * @param: [key]
     * @return: java.lang.String
     * @version V1.0
     * update-logs:方法变更说明
     * ****************************************************
     * name:
     * date:
     * description:
     * *****************************************************
     */
    public String getAutoSequenceId(String key) {
        return getAutoSequenceId(key,1L);
    }

    public void expire(String token, Long expireTime) {
        redisTemplate.expire(token, expireTime, TimeUnit.SECONDS);
    }
}
