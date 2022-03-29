package com.example.demo.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * @Author: meimengling
 * @Date: 2019/4/9 10:31
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
public class RedisLockTestTest {

    @Autowired
    RedisLockTest redisLockTest;

    @Test
    public void setEx() {
        boolean result = redisLockTest.setEx("10", "12", 10);
        System.out.println("---------------hello put  : " + result);
    }

    @Test
    public void setNx() {
        boolean result = redisLockTest.setNx("12", "23");
        System.out.println("---------------hello  : " + result);
    }

    @Test
    public void set() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);    // 设置连接属性
//        jedis.auth("password");     // 设置连接密码
        String set = jedis.set("13", "12", "NX", "PX", 10 * 1000);
        System.out.println(set);
    }
}