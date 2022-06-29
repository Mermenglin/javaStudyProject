/*
 * COPYRIGHT. ShenZhen JiMi Technology Co., Ltd. 2022.
 * ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
 * on any form or by any means, electronic, mechanical, photocopying, recording,
 * or otherwise, without the prior written permission of ShenZhen JiMi Network Technology Co., Ltd.
 *
 * Amendment History:
 *
 * Date                   By              Description
 * -------------------    -----------     -------------------------------------------
 * 2022-06-15             Mei.Mengling   Create the class
 * http://www.jimilab.com/
 */


package com.mml.springbootmybatisplusdemo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.mml.springbootmybatisplusdemo.dao.NewUserMapper;
import com.mml.springbootmybatisplusdemo.dao.UserMapper;
import com.mml.springbootmybatisplusdemo.entity.User;
import com.mml.springbootmybatisplusdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * TODO
 *
 * @author Mei.Mengling
 * @date 2022-06-15
 * @since 1.0.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    NewUserMapper newUserMapper;

    @Override
    public User getByIds(List<Long> ids) {
        List<User> users = userMapper.selectBatchIds(ids);
        log.info("select batch list:{}", JSON.toJSONString(users));
        return users.get(0);
    }

    @Override
    public Long insert(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public boolean update(User user) {
        int result = userMapper.updateById(user);
        return result == 1;
    }

    @Override
    public void delete(Set<Long> ids) {
        userMapper.deleteIn(ids);
    }

    @Override
    public Integer deleteIn(Map<String, Object> map) {
        Object ids = map.get("ids");
        if (ids instanceof String) {
            JSONArray jsonArray = JSON.parseArray((String) ids);
            Set set = new HashSet(jsonArray);
            return newUserMapper.deleteIn(set);
        }
        return 0;
    }

    @Override
    public Integer insertUser(Map<String, Object> map) {
        User user = JSON.parseObject(JSON.toJSONString(map), User.class);
        newUserMapper.insertUser(user);
        return user.getId().intValue();
    }

    @Override
    public List<Map> selectByUser(Map<String, Object> map) {
        User user = JSON.parseObject(JSON.toJSONString(map), User.class);
        List<User> users = newUserMapper.selectByUser(user);
        List<Map> list = new ArrayList<>();
        for (User u : users) {
            list.add(BeanUtil.beanToMap(u));
        }
        return list;
    }

    @Override
    public Integer updateById(Map<String, Object> map) {
        User user = JSON.parseObject(JSON.toJSONString(map), User.class);
        int i = newUserMapper.updateById(user);
        return i;
    }

    @Override
    public List<Map> getById(Map<String, Object> map) {
        Object id = map.get("id");
        try {
            if (id instanceof String) {
                Long idL = Long.valueOf((String) id);
                User user = newUserMapper.getById(idL);

                List<Map> list = new ArrayList<>();
                list.add(BeanUtil.beanToMap(user));
                return list;
            }
        } catch (NumberFormatException e) {
        }
        return null;
    }

    @Override
    public Integer updateByAccount(Map<String, Object> map) {
        String account = (String) map.get("account");
        Integer age = Integer.valueOf((String) map.get("age"));

        Integer i = newUserMapper.updateByAccount(account, age);
        return i;
    }

    @Override
    public List<Map> selectByAccounts(Map<String, Object> map) {
        User user = JSON.parseObject(JSON.toJSONString(map), User.class);
        List<User> users = newUserMapper.selectByAccounts(user);
        List<Map> list = new ArrayList<>();
        for (User u : users) {
            list.add(BeanUtil.beanToMap(u));
        }
        return list;
    }
}
