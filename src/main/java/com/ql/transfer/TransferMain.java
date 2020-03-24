package com.ql.transfer;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2020-03-10 10:07
 * @Version 1.0
 **/
public class TransferMain {

    public static void main(String[] args) {
        Student student = new Student("ssss");
        print(student);
        System.out.println(student.getName());
    }

    static void print(Student student) {
        System.out.println(student.getName());
//        student = new Student("dddd");
        student.setName("ddddd");
    }
}
class Student{

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
