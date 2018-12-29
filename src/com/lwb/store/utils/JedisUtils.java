package com.lwb.store.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	//创建连接池
	private static JedisPoolConfig config; //定义连接池配置项config(JedisPoolConfig)
	private static JedisPool pool;		  //定义连接池pool(JedisPool)
	
	static{
		config=new JedisPoolConfig();  //创建连接池配置对象
		config.setMaxTotal(30);			//设置配置项:最大连接数
		config.setMaxIdle(2);			//设置配置项:最大空闲连接数 
		
		pool=new JedisPool(config, "127.0.0.1", 6379);
	}
	
	
	//获取连接的方法
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	
	//释放连接
	public static void closeJedis(Jedis j){
		j.close();
	}
}
