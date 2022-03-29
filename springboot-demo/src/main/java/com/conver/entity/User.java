package com.conver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 数据实体类
 *
 * @Author: meimengling
 * @Date: 2019/10/18 10:59
 */
@Accessors(chain = true)        // 启用链式调用
@RequiredArgsConstructor(staticName = "ofName")     // 静态构造方法
@NoArgsConstructor          // 创建一个无参构造函数
@AllArgsConstructor         // 创建一个构造函数，该构造函数含有所有已声明字段属性参数
@Builder                    // 启用Builder模式
@Data
public class User {

    @NonNull
    private String name;

    private String age;

    /**
     * 链式调用
     */
//    public User setAge(String age){
//        this.age = age;
//        return this;
//    }
//
//    public User setName(String name){
//        this.name = name;
//        return this;
//    }

}
