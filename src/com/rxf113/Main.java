package com.rxf113;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author rxf113
 */
public class Main {

    public static void main(String[] args) throws Exception{
//        com.sun.tools.javac.main.Main compiler =
//                new com.sun.tools.javac.main.Main("javac");
//        args = new String[]{"-processor", "com.rxf113.CreatePrintProcessor", "com.rxf113.Person"};
//        int reInt = compiler.compile(args).exitCode;
//        System.out.println(reInt);
        Class<?> aClass = Class.forName("com.rxf113.Person");
        Object person = aClass.getConstructor().newInstance();
        Field age = aClass.getDeclaredField("age");
        Field name = aClass.getDeclaredField("name");
        age.setAccessible(true);
        name.setAccessible(true);
        age.set(person, 200);
        name.set(person, "张三丰");
        //执行自定义生成的方法
        Method print = aClass.getMethod("print");
        print.invoke(person);
    }
}
