package com.mmling.cassandrademo.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author Meimengling
 * @date 2020-11-16 15:42
 */
@Table
public class Person {
    @PrimaryKey
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private String des;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Person(Integer id, String name, Integer age, String des) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.des = des;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", des='" + des + '\'' +
                '}';
    }
}
