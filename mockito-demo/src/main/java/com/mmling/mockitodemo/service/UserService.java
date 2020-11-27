package com.mmling.mockitodemo.service;

import com.mmling.mockitodemo.entity.User;

/**
 * @author Meimengling
 * @date 2020-11-27 14:35
 */
public interface UserService {
    User getUser(Long id);

    User edit(User user);
}
