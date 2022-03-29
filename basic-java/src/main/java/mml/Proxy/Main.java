package Proxy;

import mml.Proxy.Cglib.CglibProxyFactory;
import mml.Proxy.JdkProxy.JdkProxyFactory;
import mml.Proxy.Service.SmsService;
import mml.Proxy.Service.SmsServiceImpl;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-6-7 18:53
 */
public class Main {

    public static void main(String[] args) {

        jdkProxy();

        cglibProxy();
    }

    public static void jdkProxy() {
        SmsService proxy = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        proxy.send("java");
    }

    public static void cglibProxy() {
        SmsService proxy = (SmsService) CglibProxyFactory.getProxy(SmsService.class);
        proxy.send("java");
    }
}
