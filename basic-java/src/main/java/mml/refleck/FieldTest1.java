package refleck;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 反射：实例化对象，获取属性详情，设置属性值
 *
 * @author Meimengling
 * @date 2021-4-12 18:23
 */
public class FieldTest1 {
    public static void main(String[] args) {
        Class first = null;

        try {
            first = Class.forName("mml.refleck.entity.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Field[] fields = first.getDeclaredFields();

        System.out.println(fields.length);

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            System.out.println("modifiers: " + Modifier.toString(modifiers));

            Class<?> type = field.getType();
            System.out.println("type: " + type.getName());
            System.out.println("field: " + field.getName());
        }

        Object o = null;
        try {
            o = first.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 设置属性
        Field f1 = null;
        Field f2 = null;
        try {
            f1 = first.getDeclaredField("name");
            f2 = first.getDeclaredField("age");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            f1.set(o, "zhangsan");

            // 获取私有属性
            f2.setAccessible(true);
            f2.setInt(o, 12);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("-------------");
            System.out.println(f1.get(o));
            System.out.println(f2.get(o));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
