package com.example.schooler;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

class User implements Serializable {
    private String UserName;
    private String email;
    private String phone;
    public User(){};
    public User(String userName,String email,String phone) {
        this.UserName = userName;
        this.email = email;
        this.phone = phone;
    }

    public String getUserName() {return UserName;}
    public void setUserName(String userName) {UserName = userName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
}

class Student extends User implements Serializable{
    private ArrayList<Grades> myGrades = new ArrayList<>();
    private ArrayList<Classes> myClasses = new ArrayList<>();
    private ArrayList<Schedule>[] mySchedule = new ArrayList[7];
    private ArrayList<Messages> myMessages = new ArrayList<>();

    public Student(){};
    public Student(String userName,String email,String phone) {
        super(userName,email,phone);
        for (int i=0;i<mySchedule.length;i++){mySchedule[i] = new ArrayList<>();}
    }

    public ArrayList<Grades> getMyGrades() {return myGrades;}
    public ArrayList<Classes> getMyClasses() {return myClasses;}
    public ArrayList<Messages> getMyMessages(){return myMessages;}
    public ArrayList<Schedule>[] getMySchedule(){return mySchedule;}
}


class Teacher extends User implements Serializable{
    private ArrayList<Classes> myClasses = new ArrayList<Classes>();
    public Teacher(String userName,String email,String phone) {
        super(userName,email,phone);
    }

    public ArrayList<Classes> getMyClasses() {return myClasses;}
    public void addClass(String className,String icon){getMyClasses().add(new Classes(className,icon));}
}