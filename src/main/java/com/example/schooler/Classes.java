package com.example.schooler;

import java.io.Serializable;
import java.util.ArrayList;

public class Classes implements Serializable {
    private String className;
    private int classId;
    private String icon;
    private ArrayList<Student> learners = new ArrayList<>();
    private ArrayList<Tasks> tasks = new ArrayList<>();

    public Classes(){}
    public Classes(String className, String icon) {
        this.className = className;
        this.icon = icon;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public ArrayList<Student> getLearners() {
        return learners;
    }
    public void setLearners(ArrayList<Student> learners) {
        this.learners = learners;
    }
    public ArrayList<Tasks> getTasks(){return tasks;}
}
