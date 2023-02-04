package com.example.schooler;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Tasks implements Serializable {
    private String taskName;
    private String taskDescription;
    private int grade = 100;
    private LocalDate givenDate = LocalDate.now();
    private LocalDate submissionDate;

    //todo get and set

    public Tasks (String taskName, String taskDescription){
        this.taskName=taskName;
        this.taskDescription=taskDescription;
    }
    public Tasks(String taskName){
        this.taskName=taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public LocalDate getGivenDate() {
        return givenDate;
    }

    public void setTaskLocalDate(LocalDate givenDate) {
        this.givenDate = givenDate;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {this.submissionDate = submissionDate;}
}