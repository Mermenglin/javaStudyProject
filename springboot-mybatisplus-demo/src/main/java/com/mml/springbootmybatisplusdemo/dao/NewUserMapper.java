package com.mml.springbootmybatisplusdemo.dao;

import com.mml.springbootmybatisplusdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @author Mei.Mengling
 * @date 2022-06-15
 * @since 1.0.0
 */
@Mapper
public interface NewUserMapper {

    Integer deleteIn(Set<Long> ids);

    Integer insertUser(User user);

    User getById(Long id);

    List<User> selectByUser(User user);

    Integer updateById(User user);

    Integer updateByAccount(String account, Integer age);

    List<User> selectByAccounts(User user);
}
