package com.ql;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Calculate {

    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            System.out.println("please input");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.next();
            String pattern = "^((([\\(][\\-]\\d+[\\)])|\\d+)([+\\-*/]))+(([\\(][\\-]\\d+[\\)])|\\d+)$";
//            String pattern = "(([\\(][\\-]\\d+[\\)])|\\d+)";
//        String pattern = "([+\\-*/])";
            boolean isMatch = Pattern.matches(pattern, text);
            System.out.println(isMatch);
        }
    }
}
