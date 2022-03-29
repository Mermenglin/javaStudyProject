package com.example.demo;

import com.example.demo.vo.ThirdLoginVo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: meimengling
 * @Date: 2019/5/30 17:18
 */
public class ListStream {

    @Test
    public void testFilter() {

        List<ThirdLoginVo> list = new ArrayList<ThirdLoginVo>();
        ThirdLoginVo t1 = new ThirdLoginVo();
        ThirdLoginVo t2 = new ThirdLoginVo();
        ThirdLoginVo t3 = new ThirdLoginVo();
        ThirdLoginVo t4 = new ThirdLoginVo();
        t1.setPhone("123");
        t2.setPhone("123");
        t3.setPhone("1234");
        t4.setPhone("123");
        list.add(t1);
        list.add(t3);
        list.add(t2);
        list.add(t4);

        System.out.println(list);

        List test = list.stream().filter(l -> {
            if (l.getPhone().equals("123"))
                l.setRealName("123");
            return true;
        }).collect(Collectors.toList());

        System.out.println(test);
        System.out.println(list);
    }

}
