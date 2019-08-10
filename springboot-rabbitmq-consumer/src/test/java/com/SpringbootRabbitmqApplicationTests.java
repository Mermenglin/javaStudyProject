package com;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@WebAppConfiguration
public class SpringbootRabbitmqApplicationTests {

    @Test
    public void contextLoads() {
    }

    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext wac;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testQ1() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("address", "合肥");

        String url = "/producer/send";

        // post
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSONObject.toJSONString(map).getBytes())
//                .accept(MediaType.APPLICATION_JSON)
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();

        // get
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
