package com.example.schooler;
public class Grades {
    private String prof;
    private String subject;
    private String grade;

    public Grades(){}
    public Grades (String prof, String subject, String grade){
        this.prof = prof;
        this.subject = subject;
        this.grade = grade;
    }

    public String getProf() {
        return prof;
    }
    public String getSubject() {
        return subject;
    }
    public String getGrade() {
        return grade;
    }

    public void setProf(String prof) {this.prof = prof;}
    public void setSubject(String subject) {this.subject = subject;}
    public void setGrade(String grade) {this.grade = grade;}
}
