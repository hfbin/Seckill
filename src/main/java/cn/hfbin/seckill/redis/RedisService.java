package cn.hfbin.seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
	
	@Autowired
	JedisPool jedisPool;
	
	/**
	 * 获取当个对象
	 * */
	public <T> T get(KeyPrefix prefix, String key,  Class<T> clazz) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			 //生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			 String  str = jedis.get(realKey);
			 T t =  stringToBean(str, clazz);
			 return t;
		 }finally {
			  returnToPool(jedis);
		 }
	}

	public  Long expice(KeyPrefix prefix,String key,int exTime){
		Jedis jedis = null;
		Long result = null;
		try {
			jedis =  jedisPool.getResource();
			result = jedis.expire(prefix.getPrefix()+key,exTime);
			return result;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 设置对象
	 * */
	public <T> boolean set(KeyPrefix prefix, String key,  T value ,int exTime) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			 String str = beanToString(value);
			 if(str == null || str.length() <= 0) {
				 return false;
			 }
			//生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			 if(exTime == 0) {
			 	 //直接保存
				 jedis.set(realKey, str);
			 }else {
			 	 //设置过期时间
				 jedis.setex(realKey, exTime, str);
			 }
			 return true;
		 }finally {
			  returnToPool(jedis);
		 }
	}
	public  Long del(KeyPrefix prefix,String key){
		Jedis jedis = null;
		Long result = null;
		try {
			jedis =  jedisPool.getResource();
			result = jedis.del(prefix.getPrefix()+key);
			return result;
		} finally {
			returnToPool(jedis);
		}
	}
	/**
	 * 判断key是否存在
	 * */
	public <T> boolean exists(KeyPrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			return  jedis.exists(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 增加值
	 * */
	public <T> Long incr(KeyPrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			return  jedis.incr(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}
	
	/**
	 * 减少值
	 * */
	public <T> Long decr(KeyPrefix prefix, String key) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			//生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			return  jedis.decr(realKey);
		 }finally {
			  returnToPool(jedis);
		 }
	}

	/**
	 * bean 转 String
	 * @param value
	 * @param <T>
	 * @return
	 */
	public static <T> String beanToString(T value) {
		if(value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if(clazz == int.class || clazz == Integer.class) {
			 return ""+value;
		}else if(clazz == String.class) {
			 return (String)value;
		}else if(clazz == long.class || clazz == Long.class) {
			return ""+value;
		}else {
			return JSON.toJSONString(value);
		}
	}


	/**
	 * string转bean
	 * @param str
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T stringToBean(String str, Class<T> clazz) {
		if(str == null || str.length() <= 0 || clazz == null) {
			 return null;
		}
		if(clazz == int.class || clazz == Integer.class) {
			 return (T)Integer.valueOf(str);
		}else if(clazz == String.class) {
			 return (T)str;
		}else if(clazz == long.class || clazz == Long.class) {
			return  (T)Long.valueOf(str);
		}else {
			return JSON.toJavaObject(JSON.parseObject(str), clazz);
		}
	}

	private void returnToPool(Jedis jedis) {
		 if(jedis != null) {
			 jedis.close();
		 }
	}

}
