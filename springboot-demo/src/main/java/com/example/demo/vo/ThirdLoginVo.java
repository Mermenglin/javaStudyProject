package com.example.demo.vo;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: meimengling
 * @Date: 2019/4/4 15:44
 */
@Slf4j
@Data
@ToString
public class ThirdLoginVo {

    private String phone;

    private String realName;

    private String idCardNo;
}
