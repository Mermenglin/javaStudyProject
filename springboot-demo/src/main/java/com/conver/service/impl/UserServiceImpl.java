package com.conver.service.impl;

import com.conver.entity.User;
import com.conver.service.UserService;

/**
 * @Author: meimengling
 * @Date: 2019/10/18 11:07
 */
public class UserServiceImpl implements UserService {

    @Override
    public User addUser(User user) {

        // 业务实现

        // mapper.insert(user);
        User user1 = new User().setName("小明").setAge("22");

        User user2 = User.ofName("小红");

        User user3 = User.ofName("小蓝").setAge("18");        // 支持链式调用

        User user4 = User.builder().name("小天").age("20").build();      // 建造者模式

        return user1;
    }
}
