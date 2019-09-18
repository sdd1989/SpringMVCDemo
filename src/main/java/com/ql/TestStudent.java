package com.ql;

import java.util.Arrays;
import java.util.Scanner;

public class TestStudent {

    static Student[] students = new Student[2];

    /**
     * @param args
     */
    public static void main(String [] args) {

        Scanner input = new Scanner(System.in);

        for(int j=0; j<2;j++){
            students[j] = new Student();

            System.out.println("请输入学生姓名: ");
            students[j].setStudentName(input.next());

            System.out.println("请输入学生学号: ");
            students[j].setStudentID(input.next());

            System.out.println("请输入四门课的成绩，以空格分隔: ");
            input.nextLine();
            String score = input.nextLine();
            String[] part = score.split(" ");
            for(int i=0; i<4; i++) {
                students[j].getScores()[i] = Integer.parseInt(part[i]);
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("学生成绩:"+ Arrays.toString(students));
        System.out.println("------------------------------------------");

    }

}
