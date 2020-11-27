package com.mmling.mockitodemo.dao;

import com.mmling.mockitodemo.entity.User;

/**
 * @author Meimengling
 * @date 2020-11-27 17:19
 */
public interface UserDao {

    User getUser(Long id);

    User edit(User user);
}
