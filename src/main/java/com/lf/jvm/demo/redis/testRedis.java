package com.lf.jvm.demo.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class testRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("119.3.175.216", 6379);
        System.out.println(jedis.ping());
        jedis.close();

    }
    private  Jedis jedis;
    @Before
    public void initJedis(){
        jedis = new Jedis("119.3.175.216", 6379);
    }
    @Test
    public void testString(){

        System.out.println(jedis.set("name","lf"));
        System.out.println(jedis.get("name"));
        System.out.println(jedis.del("name"));
        //原子操作
        System.out.println(jedis.mset("k1","v1","k2","v2","k3","v3"));
        System.out.println(jedis.setnx("k4","vv"));
        System.out.println(jedis.mget("k1","k2","k3"));
        //设置过期时间60s后自动删除
        //System.out.println(jedis.setex("kk",60,"vv"));
        //查看剩余过期时间 -1表示用不过期  -2表示已经过期
        System.out.println(jedis.ttl("kk"));
    }
    @Test
    public void testKeys(){
        //正则表达式
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.exists("k4"));
        //指定过期时间
        System.out.println(jedis.expire("k1",600));
        System.out.println(jedis.ttl("k4"));
        System.out.println(jedis.type("k4"));
        //相当于i++  针对数字类型
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        //相当于i--
        System.out.println(jedis.decr("count"));
        //步长
        System.out.println(jedis.incrBy("count",2));
        System.out.println(jedis.decrBy("count",2));
    }
    @Test
    public void testTime01(){
        long start =System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            jedis.setex("kk"+i,60,"vv"+i);
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
    @Test
    public void testTime02(){
        long start =System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            //获取管道
            Pipeline pipelined = jedis.pipelined();
            pipelined.setex("kkkk"+i,60,"vvvv"+i);
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }@Test
    public void testTime03(){
        long start =System.currentTimeMillis();
        for (int i = 0; i <100 ; i++) {
            Pipeline pipelined = jedis.pipelined();
            for (int j = 0; j <100; j++) {
                //获取管道
                pipelined.setex("kkk"+j,60,"vvv"+j);
            }
            pipelined.syncAndReturnAll();
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
    //setbit key  6 1:通过操作二进制每位上的数来改变值
    @After
    public void afterClose(){
        jedis.close();
    }
}
