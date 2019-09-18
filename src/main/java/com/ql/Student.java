package com.ql;

import java.util.Arrays;

class Student{

    private String studentID;//学生学号
    private String studentName;//学生姓名
    private Integer []scores = new Integer[4];//学生成绩

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer[] getScores() {
        return scores;
    }

    public void setScores(Integer[] scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", scores=" + Arrays.toString(scores) +
                '}';
    }
}

