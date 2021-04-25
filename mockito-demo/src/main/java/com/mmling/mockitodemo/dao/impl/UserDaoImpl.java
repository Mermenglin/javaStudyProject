package com.mmling.mockitodemo.dao.impl;

import com.mmling.mockitodemo.dao.UserDao;
import com.mmling.mockitodemo.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Meimengling
 * @date 2020-11-30 10:26
 */
@Component
public class UserDaoImpl implements UserDao {
    @Override
    public User getUser(Long id) {
        return new User(id, "李四", "路人");
    }

    @Override
    public User edit(User user) {
        return new User(15L, "李四", "路人");
    }
}
