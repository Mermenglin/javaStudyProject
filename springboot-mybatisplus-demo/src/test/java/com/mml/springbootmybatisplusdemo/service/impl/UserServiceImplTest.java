package com.mml.springbootmybatisplusdemo.service.impl;


import com.alibaba.fastjson2.JSON;
import com.mml.springbootmybatisplusdemo.SpringbootMybatisplusDemoApplication;
import com.mml.springbootmybatisplusdemo.dao.UserMapper;
import com.mml.springbootmybatisplusdemo.entity.User;
import com.mml.springbootmybatisplusdemo.service.UserService;
import com.mml.springbootmybatisplusdemo.utils.ReflectionUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

/**
 * TODO
 *
 * @author Mei.Mengling
 * @date 2022-06-16
 * @since 1.0.0
 */
@SpringBootTest(classes = SpringbootMybatisplusDemoApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper mapper;

    @Test
    public void test1() {
        User user = new User();
        user.setName("李四");
        user.setPassword("lisi");
        user.setAge(27);
        user.setAccount(user.getName());
        user.setPhone(15898584551L);
        Long id = userService.insert(user);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(id);

        User all = userService.getByIds(ids);
//        System.out.println(all);
    }

    @Test
    public void test2() {
        User user = new User();
        user.setId(2L);
        user.setName("李四");
        user.setPassword("lisi");
        user.setAge(23);
        user.setAccount(user.getName());
        user.setPhone(15898584251L);
        userService.update(user);
    }

    @Test
    public void test3() {
        Set<Long> ids = new HashSet<>();
        ids.add(6L);
        ids.add(7L);

        userService.delete(ids);
    }

    @Test
    public void test4() {
        test1();
        test2();
        test3();
        test5();
        test6();
    }

    @Test
    public void test5() {
        List<User> users = mapper.seleteAll();
        System.out.println(users);
    }

    @Test
    public void test6() {
        String pw = "lisi";
        System.out.println(mapper.seleteByPw(pw));
    }

    @Test
    public void test7() {
        User all = mapper.getById(1L);
        System.out.println(all);
    }

    @Test
    public void test8() {
        User user = new User();
        user.setId(2L);
        List<User> users = mapper.selectByUser(user);
        System.out.println(users);
    }


    @Resource
    private ReflectionUtil reflectionUtil;

    @Test
    public void paramTest() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", "1");
        Object o = reflectionUtil.invokeService("userServiceImpl", "getById", paramMap);
        System.out.println(o);
    }

    @Test
    public void test9() {
        User user = new User();
        user.setName("王五2");
        user.setPassword("wangwu");
        user.setAge(27);
        user.setAccount(user.getName());
        user.setPhone(15898584551L);
        int insert = mapper.insert(user);
        System.out.println("插入, id = " + insert);
        User user2 = new User();
        user2.setId(2L);
        List<User> users = mapper.selectByUser(user2);
        System.out.println("批量查询：" + JSON.toJSONString(users));
        Set<Long> ids = new HashSet<>();
        ids.add(13L);
        ids.add(14L);
        Integer integer = mapper.deleteIn(ids);
        System.out.println("删除, num=" + integer);

        User byId = mapper.getById(1L);
        System.out.println("单个查询：" + JSON.toJSONString(byId));

        User user3 = new User();
        user3.setId(2L);
        user3.setName("李四");
        user3.setPassword("lisi");
        user3.setAge(23);
        user3.setAccount(user3.getName());
        user3.setPhone(15898584251L);
        int i = mapper.updateById(user3);
        System.out.println("修改：" + i);

        String account = "王五2";
        Integer age = 30;
        Integer i2 = mapper.updateByAccount(account, age);
        System.out.println("根据账号修改：" + i2);
    }

    @Test
    public void test10() {
        User user = new User();
        String[] arr = new String[]{"李四", "王五2"};
        user.setAccounts(arr);
        List<User> users = mapper.selectByAccounts(user);
        System.out.println(JSON.toJSONString(users));
    }

}