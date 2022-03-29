package mml.Proxy.Service;

import mml.Proxy.Service.SmsService;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-6-7 18:30
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message" + message);
        return message;
    }
}
