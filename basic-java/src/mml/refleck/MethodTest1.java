package mml.refleck;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Meimengling
 * @date 2021-4-12 18:53
 */
public class MethodTest1 {

    public static void main(String[] args) {
        Class fos = null;

        try {
            fos = Class.forName("mml.refleck.entity.Student1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object object = null;

        try {
            object = fos.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Method method = null;

        try {
            method = fos.getDeclaredMethod("GetScore", String.class, double.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object value = null;

        try {
            value = method.invoke(object, "lisi", 530);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(value);
    }
}
