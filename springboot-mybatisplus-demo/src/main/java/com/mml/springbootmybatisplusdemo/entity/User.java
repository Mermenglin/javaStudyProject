package com.mml.springbootmybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mml.springbootmybatisplusdemo.encrypt.SensitiveData;
import com.mml.springbootmybatisplusdemo.encrypt.SensitiveField;
import lombok.Data;

/**
 * 用户表实体
 *
 * @author Mei.Mengling
 * @date 2022-06-15
 * @since 1.0.0
 */
@SensitiveData
@Data
@TableName("d_user")
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String account;
    @SensitiveField
    private String password;
    private Long phone;

    private String[] accounts;

}
