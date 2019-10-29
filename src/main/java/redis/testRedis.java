package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class testRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("119.3.175.216", 6379);

        System.out.println(jedis.ping());
        jedis.close();

    }
    @Test
    public void testString(){
        Jedis jedis = new Jedis("119.3.175.216", 6379);
        System.out.println(jedis.set("name","lf"));
        System.out.println(jedis.get("name"));
        System.out.println(jedis.del("name"));
        //原子操作
        System.out.println(jedis.mset("k1","v1","k2","v2","k3","v3"));
        System.out.println(jedis.setnx("k4","vv"));
        System.out.println(jedis.mget("k1","k2","k3"));
    }
}
