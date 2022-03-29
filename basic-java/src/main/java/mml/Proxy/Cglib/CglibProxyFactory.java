package mml.Proxy.Cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author meimengling
 * @version 1.0
 * @date 2021-6-7 18:51
 */
public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        return enhancer.create();
    }

}
