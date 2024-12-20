package com.conver.controller;

import com.conver.DTO.UserDTO;
import com.conver.entity.User;
import com.conver.service.UserService;
import com.conver.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @Author: meimengling
 * @Date: 2019/10/18 11:05
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserDTO addUser(@Valid UserDTO userDTO, BindingResult bindingResult) {

        // 校验入参是否正确
        checkDTOParams(bindingResult);

        // 将入参转换成内部对象类
        User user = userDTO.convertToUser();
        User saveResultUser = userService.addUser(user);

        // 将内部对象转成出参类
        UserDTO result = userDTO.convertFor(saveResultUser);
        return result;
    }

    public static void main(String[] args) {
        UserDTO u1 = new UserDTO();
        u1.setUserName("name");
        UserDTO u2 = new UserDTO();
        u2.setAge("age");

        BeanUtils.copyPropertiesIgnoreNullProperty(u1, u2);
        System.out.println(u2.getUserName());
    }

    private void checkDTOParams(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //throw new 带验证码的验证错误异常
        }
    }
}
