package com.hacker.spring5.classloader;

/**
 *  <pre>
 *
 * 2.JVM中类加载器的树状层次结构
     1. 引导类加载器（BootstrapClassloader）：
            它用来加载 Java 的核心库，是用原生C++代码来实现的，并不继承自java.lang.ClassLoader。
            加载扩展类和应用程序类加载器，并指定他们的父类加载器，在java中获取不到。(我说搜不到呢)

     2. 扩展类加载器（ExtClassLoader）：
            它用来加载 Java 的扩展库(jre/ext/*.jar)。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类。

     3. 系统里加载器 （AppClassLoader）：
            它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。
            可以通过 ClassLoader.getSystemClassLoader()来获取它。
     4. 自定义类加载器（custom class loader）：
            除了系统提供的类加载器以外，开发人员可以通过继承 java.lang.ClassLoader类的方式实现自己的类加载器，以满足一些特殊的需求。
     </pre>
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        /**
         * 1,获取最底层的 类加载器 AppClassLoader
         */
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        System.out.println(systemClassLoader);  // sun.misc.Launcher$AppClassLoader@18b4aac2

        System.out.println(systemClassLoader.getParent()); // sun.misc.Launcher$ExtClassLoader@610455d6

        System.out.println(systemClassLoader.getParent().getParent());  //null  C++ 实现的，无法获取
    }
}
