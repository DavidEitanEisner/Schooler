package com.example.schooler;

public class Schedule {
    private String className;
    private int classHour;
    public Schedule(){}
    public Schedule(String className, int classHour){
        this.className=className;
        this.classHour=classHour;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public int getClassHour() {
        return classHour;
    }
    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }
}
