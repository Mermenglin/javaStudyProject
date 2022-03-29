package refleck;

/**
 * @author Meimengling
 * @date 2021-4-12 18:00
 */
public class ReflectTest1 {

    public static void main(String[] args) {
        Class s1 = null;
        try {
            //通过反射机制获取Class
            s1 = Class.forName("mml.refleck.entity.User");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object a = null;
        try {
            a = s1.newInstance();//用反射获得的Class,创建一个对象
            System.out.println(a);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

