package mml.Proxy.JdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 *
 * @author meimengling
 * @version 1.0
 * @date 2021-6-7 16:52
 */
public class DebugInvocationHandler implements InvocationHandler {

    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method" + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after method" + method.getName());
        return result;
    }
}
