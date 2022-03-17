package com.company;

import java.io.Serializable;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    //Constructors
    public Date(int day,int month,int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }
    //getters
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    //setters
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return  day + "/" +month +"/"+ year ;
    }
}
