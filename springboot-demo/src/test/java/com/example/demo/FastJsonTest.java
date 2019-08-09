package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.vo.ThirdLoginVo;
import org.junit.Test;

/**
 * @Author: meimengling
 * @Date: 2019/4/4 16:18
 */
public class FastJsonTest {

    @Test
    public void test(){
        String s = "{\"phone\":\"156444564111\"}";
        ThirdLoginVo thirdLoginVo = JSON.parseObject(s, ThirdLoginVo.class);
        System.out.println(thirdLoginVo);
    }
}
