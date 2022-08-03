package com.mml.springbootmybatisplusdemo;

import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class SpringbootMybatisplusDemoApplicationTests {

    @Test
    void contextLoads() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "北京");
        String url = "";

        String result1 = HttpUtil.post(url, paramMap);
    }

}
