package mml.Proxy.JdkProxy;

import mml.Proxy.JdkProxy.DebugInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * 工厂类，创建代理类
 *
 * @author meimengling
 * @version 1.0
 * @date 2021-6-7 18:34
 */
public class JdkProxyFactory {
    public static Object getProxy(Object target) {

        // 获取target类的代理对象
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new DebugInvocationHandler(target));
    }
}
