package com.mmling.mybatisRedisCache.entity;


import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author meiml
 * @since 2019-12-31 11:43:14
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -44322077542191854L;

    private Integer id;

    private Integer age;

    private String sex;

    private String address;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}