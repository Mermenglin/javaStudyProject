package com.mmling.mockitodemo;

import com.mmling.mockitodemo.controller.UserController;
import com.mmling.mockitodemo.dao.UserDao;
import com.mmling.mockitodemo.entity.User;
import com.mmling.mockitodemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Meimengling
 * @date 2020-11-27 14:38
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MockitoDemoApplication.class)
public class UserBeanTest {

    @Autowired
    UserController controller;

    @Autowired
    UserService userService;

    @MockBean   //需要mock的bean，会自动注入到调用的对象中
    private UserDao userDao;

    MockMvc mockMvc;


    /**
     * 测试 service 层
     */
    @Test
    public void test() {
        // 定义未实现的 service 返回
        when(userDao.getUser(anyLong())).thenReturn(new User(anyLong(), "张三", "路人"));
        System.out.println(userService.getUser(12L).toString());
        verify(userDao, times(1)).getUser(anyLong());
    }

    /**
     * 测试 controller 时，需要构建 mvc 环境
     */
    @BeforeEach
    public void setup() {
        //构建mvc环境
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    /**
     * .perform() : 执行一个MockMvcRequestBuilders的请求；MockMvcRequestBuilders有.get()、.post()、.put()、.delete()等请求。
     * .andDo() : 添加一个MockMvcResultHandlers结果处理器,可以用于打印结果输出(MockMvcResultHandlers.print())。
     * .andExpect : 添加MockMvcResultMatchers验证规则，验证执行结果是否正确。
     */
    @Test
    public void testGetUser() throws Exception {
        // 定义未实现的 service 返回
        when(userDao.getUser(anyLong())).thenReturn(new User(12L, "张三", "路人"));

        //模拟接口调用
        ResultActions perform = this.mockMvc.perform(get("/api/v1/user/12"));

        //对接口响应进行验证
        perform.andExpect(status().isOk())
                .andExpect(content().json("{id:12,name:张三,desc:路人}"));  // 可以不用写成转义后的json格式

        System.out.println(perform.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testEditUser() throws Exception {
        // 定义未实现的 service 返回
        when(userDao.edit(any(User.class))).thenReturn(new User(12L, "张三", "路人"));

        //模拟接口调用
        ResultActions perform = this.mockMvc.perform(post("/api/v1/user/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":12,\"name\":\"张三\",\"desc\":\"路人\"}"));   // 必须写成转义后的json格式，否则没法转换

        //对接口响应进行验证
        perform.andExpect(status().isOk())
                .andExpect(content().json("{id:12,name:张三,desc:路人}"));  // 可以不用写成转义后的json格式

        System.out.println(perform.andReturn().getResponse().getContentAsString());
    }

}
