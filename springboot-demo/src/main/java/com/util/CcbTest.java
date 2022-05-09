package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CcbTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        String url = "https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_06_MB?SEC_VERSION=4.1.6&VERSION_NAME=3.0.0.0&MP_CODE=01&GLOBAL_SKEY=XFakwQ&ccbParam=EWsesIGB5T26MmCiCQ7KEGJitZBo2a6D3jyoTaxT%2FIAHjqOO62%2BceTOk45NBODnZDeASJTsomdy2XVp57K4iLX%2BHKOS1Sh8jM03pNkjucvdIsxRx724zPx%2FJUMlRAWgjYMGUb4j0ROq35GrHK%2B7mC7ra7MuGkDkNy0ZKGWhOp7M5vTv2%2FCYLY69Qoq04Nl4ZqsCHvCIY%2BBwwvyH%2Bxwcv1bhnF2xuqwEhmP3iwPVBK50dr%2BHARJnLFNCgioy%2FRE9e0ufSoEEvCTT3YEXkVbnrJpHTGuPbPe%2BacpwL2g9S4D5GgGRhHjDbb4lnq97ROfLrsa1KnWvFKtiMXP8HIm2v6Y2WShn4vOfsHtcKZXZWjn4AcmTHY8TpQYy4Nl9cUvosJKfaOjAiLi7V3tsofcHtMAN8G1qOZFu7ZrRkP2PMs5PF6RItJO7UcCbIEgvZBgDb%2FJ9KJUUcVagLMnZEYJGyCVTRVPRVY7ua1Mil5HvGW9F%2BDKsdIyhqtyG153Gyol5fmqUwS8I3vOHRYg4TVx0rTi26sQqoOMos&SYS_CODE=0760&APP_NAME=com.chinamworld.main&SKEY=SnSJgm";
        String url2 = "https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_06_MB?SEC_VERSION=4.1.6&VERSION_NAME=3.0.0.0&MP_CODE=01&GLOBAL_SKEY=XFakwQ&ccbParam=nwEO0rvVnSJmuSdVKPw6F1lJncr6KaEbAUrUoCkxoXyFKLRXCh9oWOBX8oMDHw1EOQwzATg58T%2Bx8yxWe%2B3b%2BxEOSKQ29XKC8qAB0kS2h4Z12TFZCeUc3b39VmPjrRwr%2Bk1x%2F9GPfSJ71kqYK41W50Uqq1G4od1Qp8ZlDcF3hjLtCRfSpCBRR%2FlRLg1dz7GOQtCB7z%2Ff7A2BCSoNmjjG0T%2BNp%2BcEgEY0NV%2B4Iv1qR2NMjKMq%2B74YQ5WtIBgBFo5mMYIcM%2Boe02odMzqXgzOa%2B4M6ov6qcOPfbtZxFQhDAZhcgLIt2bmWP9%2ByWnkhSDzeIaX70w6xH5Zndnv0R0KDvQBjhIs%2BapUyixRBF02in12TpKrKcWOjjo8Yiqc8yRzYQTrlcTeMU8XlUnwJ02DR6SBA43%2F6iRyVlSh9tJoD4%2FNGNV1ZKWThwU8WTuL9tnLFi0TEdjxAM9A37CWClHsQiG0ZBz0IsEGg%2FTCe668TOYXQtVgxwtVF%2BGhTWgSz%2B13I&SYS_CODE=0760&APP_NAME=com.chinamworld.main&SKEY=SnSJgm";
        String url3 = "https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_06_MB?SEC_VERSION=4.1.6&VERSION_NAME=3.0.0.0&MP_CODE=01&GLOBAL_SKEY=XFakwQ&ccbParam=nwEO0rvVnSJmuSdVKPw6F1lJncr6KaEbAUrUoCkxoXyFKLRXCh9oWOBX8oMDHw1EOQwzATg58T%2Bx8yxWe%2B3b%2BxEOSKQ29XKC8qAB0kS2h4Z12TFZCeUc3b39VmPjrRwr%2Bk1x%2F9GPfSJ71kqYK41W50Uqq1G4od1Qp8ZlDcF3hjLtCRfSpCBRR%2FlRLg1dz7GOQtCB7z%2Ff7A2BCSoNmjjG0T%2BNp%2BcEgEY0NV%2B4Iv1qR2NMjKMq%2B74YQ5WtIBgBFo5mMYIcM%2Boe02odMzqXgzOa%2B4M6ov6qcOPfbtZxFQhDAZhcgLIt2bmWP9%2ByWnkhSDzeIaX70w6xH5Zndnv0R0KDvQBjhIs%2BapUyixRBF02in12TpKrKcWOjjo8Yiqc8yRzYQTrlcTeMU8XlUnwJ02DR6SBA43%2F6iRyVlSh9tJoD4%2FNGNV1ZKWThwU8WTuL9tnLFi0TEdjxAM9Bei9%2BmwcSsDSPyJz1SpDBIqlWWxCT%2BHrIpEwARmtfohD%2FY26KxdHlJ&SYS_CODE=0760&APP_NAME=com.chinamworld.main&SKEY=SnSJgm";
        String url4 = "https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_06_MB?SEC_VERSION=4.1.6&VERSION_NAME=3.0.0.0&MP_CODE=01&GLOBAL_SKEY=XFakwQ&ccbParam=EWsesIGB5T26MmCiCQ7KEGJitZBo2a6D3jyoTaxT%2FIAHjqOO62%2BceTOk45NBODnZDeASJTsomdy2XVp57K4iLX%2BHKOS1Sh8jM03pNkjucvdIsxRx724zPx%2FJUMlRAWgjYMGUb4j0ROq35GrHK%2B7mC7ra7MuGkDkNy0ZKGWhOp7M5vTv2%2FCYLY69Qoq04Nl4ZqsCHvCIY%2BBwwvyH%2Bxwcv1bhnF2xuqwEhmP3iwPVBK50dr%2BHARJnLFNCgioy%2FRE9e0ufSoEEvCTT3YEXkVbnrJpHTGuPbPe%2BacpwL2g9S4D5GgGRhHjDbb4lnq97ROfLrsa1KnWvFKtiMXP8HIm2v6Y2WShn4vOfsHtcKZXZWjn4AcmTHY8TpQYy4Nl9cUvosJKfaOjAiLi7V3tsofcHtMAN8G1qOZFu7ZrRkP2PMs5PF6RItJO7UcCbIEgvZBgDb%2FJ9KJUUcVagLMnZEYJGyCVTRVPRVY7ua1Mil5HvGW9Gn8GknR7RrAY5qRS%2FQkWvtMENW8S7V5A81RcMKPKfsph8yfR5%2Ffdpg&SYS_CODE=0760&APP_NAME=com.chinamworld.main&SKEY=SnSJgm";

        try {

            while (true) {
                URLConnection conn = null;
                switch (RandomUtil.getRandom(4)) {
                    case 0:
                        URL realURL1 = new URL(url);
                        conn = realURL1.openConnection();
                        break;
                    case 1:
                        URL realURL2 = new URL(url2);
                        conn = realURL2.openConnection();
                        break;
                    case 3:
                        URL realURL3 = new URL(url3);
                        conn = realURL3.openConnection();
                        break;
                    default:
                        URL realURL4 = new URL(url4);
                        conn = realURL4.openConnection();
                }
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
                conn.connect();
                Map<String, List<String>> map = conn.getHeaderFields();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn
                        .getInputStream(), "UTF-8"));
                String line;
                StringBuilder sb = new StringBuilder(64 * 2);
                // 读取返回的内容
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject result = JSONObject.parseObject(sb.toString());

                JSONArray mct401 = result.getJSONArray("Array_MCT401");

                // 网络原因导致没获取到价格
                if (mct401 == null) {
                    continue;
                }
                //VARIETY_NAME -> 人民币账户原油WTI2002
                for (int i = 0; i < mct401.size(); i++) {
                    JSONObject jsonObject = mct401.getJSONObject(i);
                    if ("人民币账户原油WTI2002".equals(jsonObject.getString("VARIETY_NAME"))) {
                        System.out.println("============================");
                        System.out.println(DateUtil.getTime());
                        System.out.println("人民币账户原油WTI2002   买入价： " + jsonObject.getString("MCTS_ASK_PRICE").substring(0, 6));
                        System.out.println("人民币账户原油WTI2002   买出价： " + jsonObject.getString("MCTS_BID_PRICE").substring(0, 6));
                        //todo 输入到文件中

                        StringBuilder sbs = new StringBuilder(64).append(DateUtil.getTime())
                                .append("\n\n人民币账户原油WTI2002 买入价： ").append(jsonObject.getString("MCTS_ASK_PRICE").substring(0, 6))
                                .append("\n\n人民币账户原油WTI2002 买出价： ").append(jsonObject.getString("MCTS_BID_PRICE").substring(0, 6));

                        InfoUtil test = new InfoUtil();

                        // 使用线程池来执行弹窗效果，避免重复创建线程，消耗资源
                        executorService.execute(() -> {
                            test.show("原油价格", sbs.toString());
                        });
                    }
                }
                // 间隔(14 - 16)秒刷新一次

                Thread.sleep(1000 * (14 + RandomUtil.getRandom(3)));
            }

        } catch (Exception e) {
            System.out.println("出现错误 message ");
            e.printStackTrace();
            System.exit(1);
        }
    }
}