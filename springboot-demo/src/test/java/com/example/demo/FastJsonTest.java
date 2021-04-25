package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.vo.ThirdLoginVo;
import org.junit.Test;

/**
 * @Author: meimengling
 * @Date: 2019/4/4 16:18
 */
public class FastJsonTest {

    @Test
    public void test(){
        String s = "{\"phone\":\"156444564111\"}";
        ThirdLoginVo thirdLoginVo = JSON.parseObject(s, ThirdLoginVo.class);
        System.out.println(thirdLoginVo);
    }

    @Test
    public void test2() {
        String bodyStr = "{\"user\":{\"user_unique_id\":\"6951310258099748864\",\"user_id\":6951310258099748864,\"user_type\":13,\"user_is_auth\":false,\"user_is_login\":false,\"is_upgrade_user\":false,\"web_id\":6951310258099748864,\"ip_addr_id\":0,\"ssid\":\"00564bee-48cd-4539-9aea-b4a55d115fa6\"},\"header\":{\"app_id\":10000027,\"app_name\":\"midea_mall_wap\",\"access\":\"\",\"client_ip\":\"60.7.92.138\",\"carrier\":\"\",\"os_name\":\"ios\",\"os_version\":\"12.3.1\",\"product_id\":107,\"product_name\":\"default_to_b\",\"custom\":\"{\\\"app_id\\\":10000027,\\\"screen_width\\\":414,\\\"screen_height\\\":896}\",\"trace_id\":\"16184965128890420000341244929237\",\"language\":\"zh-CN\",\"device_model\":\"iPhone\",\"resolution\":\"414x896\",\"width\":414,\"height\":896,\"timezone\":8.0,\"tz_offset\":-28800,\"platform\":\"wap\",\"browser\":\"\",\"browser_version\":\"5\",\"referrer\":\"\",\"referrer_host\":\"\"},\"params\":\"{\\\"user_unique_id\\\":\\\"\\\",\\\"page_title\\\":\\\"对开门冰箱_美的对开门冰箱-美的商城官网\\\",\\\"since_page_start_ms\\\":31325,\\\"beat_type\\\":1,\\\"is_bav\\\":1,\\\"page_total_width\\\":414,\\\"event_index\\\":1618496614721,\\\"scroll_width\\\":414,\\\"page_key\\\":\\\"https://m.midea.cn/10013.html?mtag=\\\\u0026appview=1\\\",\\\"page_manual_key\\\":\\\"\\\",\\\"app_name\\\":\\\"midea_mall_wap\\\",\\\"page_total_height\\\":3580,\\\"page_start_ms\\\":1618496481473,\\\"is_html\\\":1,\\\"uin\\\":\\\"310831441\\\",\\\"f_uid\\\":\\\"310833497\\\",\\\"scroll_height\\\":808}\",\"event_name\":\"bav2b_beat\",\"session_id\":\"a805f420-ac8c-4015-a033-25d032d01ca9\",\"datetime\":1618496512,\"server_time\":1618496512,\"rnd\":\"ne0000\",\"log_type\":\"mario_event\",\"local_time_ms\":1618496512799,\"debug_flag\":false,\"seq_id\":0}";

        JSONObject body = JSON.parseObject(bodyStr);
        JSONObject user = body.getJSONObject("user");
        JSONObject header = body.getJSONObject("header");

        body.putAll(user);
        body.remove("user");
        body.putAll(header);
        body.remove("header");

        System.out.println(body);

    }
}
