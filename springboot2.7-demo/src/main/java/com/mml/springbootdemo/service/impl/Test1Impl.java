package com.mml.springbootdemo.service.impl;

import com.mml.springbootdemo.service.Test1Interface;
import com.mml.springbootdemo.service.Test2Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test1Impl implements Test1Interface {

    @Autowired
    Test2Interface test2Impl;

}
