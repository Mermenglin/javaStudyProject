package com.mmling.mockitodemo.service.impl;

import com.mmling.mockitodemo.dao.UserDao;
import com.mmling.mockitodemo.entity.User;
import com.mmling.mockitodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meimengling
 * @date 2020-11-27 14:36
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User edit(User user1) {
        return userDao.edit(user1);
    }
}
