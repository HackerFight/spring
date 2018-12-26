package com.hacker.spring5.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {

    /**
     * 自定义的类加载器，需要实现 findClass 方法
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        //先从缓存里查
        Class<?> c = findLoadedClass(name);
        if (null != c) {
            return c;
        }

        byte[] classData = getClassData(name);
        if (null == classData) {
            throw new ClassNotFoundException();
        } else {
            c = defineClass(name, classData, 0, classData.length);
            return c;
        }
    }

    private byte[] getClassData(String className) {
        String path = className.replace('.', '/') + ".class";

        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(path);
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int temp = 0;
            while ((temp = is.read(buffer)) != -1) {
                bos.write(buffer, 0, temp);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}

