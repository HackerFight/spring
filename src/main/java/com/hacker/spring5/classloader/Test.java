package com.hacker.spring5.classloader;

/**
 * @author hacker
 * @date 2018/12/26
 * @describe
 */
public class Test {

    public static void main(String[] args) throws Exception{
         MyClassLoader classLoader = new MyClassLoader();

         //
        Class<?> aClass = classLoader.loadClass("com.hacker.spring5.classloader.HelloWorld");

        System.out.println(aClass.getClassLoader());  // 为什么是 sun.misc.Launcher$AppClassLoader@18b4aac2， 而非自定义的加载器呢?
    }
}
