package com.mml.springbootmybatisplusdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mml.springbootmybatisplusdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface UserMapper extends BaseMapper<User> {

    Integer deleteIn(Set<Long> ids);

    List<User> seleteAll();

    List<User> seleteByPw(@Param("pw") String pw);

    User getById(Long id);

    List<User> selectByUser(User user);

//    int updateById(User user);

    Integer updateByAccount(String account, Integer age);

    List<User> selectByAccounts(User user);
}
