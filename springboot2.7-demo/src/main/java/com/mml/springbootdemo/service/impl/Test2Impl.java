package com.mml.springbootdemo.service.impl;

import com.mml.springbootdemo.service.Test1Interface;
import com.mml.springbootdemo.service.Test2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test2Impl implements Test2Interface {

    @Autowired
    Test1Interface test1Impl;

}
