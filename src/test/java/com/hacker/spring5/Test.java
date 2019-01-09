package com.hacker.spring5;

import java.util.Arrays;
import java.util.List;

/**
 * @author hacker
 * @date 2019/1/3
 * @describe
 */
public class Test {

    public static int show() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (i == 5) {
                return 999;
            }
        }
        return 10001;
    }

    public static void test2() {
        List<String> data = Arrays.asList("2", "8","5", "1");
        System.out.println(data.stream().mapToDouble(v -> Double.valueOf(v)).sum());
    }

    public static void test3(int index) {
        int i = index;
        do {
            i = parseIndex(i);
        }while (i < 10);
    }

    public  static  void test4() {
        Integer index = null;
        System.out.println(index);
    }

    private static int parseIndex(int index) {
        return index + 1;
    }

    public static void main(String[] args) {
//        show();
//        test2();
//        test3(2);
//        test4();
        Double actual = null;
        System.out.println(50 > actual);
    }

}
