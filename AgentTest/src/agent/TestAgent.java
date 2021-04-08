package agent;

import java.lang.instrument.Instrumentation;

/**
 * @author Meimengling
 * @date 2021-4-8 16:06
 */
public class TestAgent {

    /**
     * jvm 参数形式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain");
        System.out.println(agentArgs);
//        customLogic(inst);
    }

    public static void premain(String agentArgs) {
        System.out.println("premain2");
        System.out.println(agentArgs);
    }

    /**
     * 动态 attach 方式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain");
        customLogic(inst);
    }

    /**
     * 打印所有已加载的类名称
     * 修改字节码
     *
     * @param inst
     */
    private static void customLogic(Instrumentation inst) {
//        inst.addTransformer(new MyTransformer(), true);
        Class[] classes = inst.getAllLoadedClasses();
        for (Class cls : classes) {
            System.out.println(cls.getName());
        }
    }
}
