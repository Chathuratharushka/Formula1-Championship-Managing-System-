package com.company;

import java.io.Serializable;

public class Race implements Serializable {
    private Date date;
    static int totalNumberRaces;
    private String[] racePositions;

    //constructors
    public Race(){
        totalNumberRaces++;
    }
    public Race(int NumberOfDrivers){
        racePositions=new String[NumberOfDrivers];
        for(int i=0;i<NumberOfDrivers;i++){
            racePositions[i]="";  //creating array with empty strings
        }
        totalNumberRaces++;
    }

    //getters
    public Date getDate() {
        return date;
    }

    public static int getTotalNumberRaces() {
        return totalNumberRaces;
    }

    public String[] getRacePositions() {
        return racePositions;
    }

    //setters

    public void setDate(Date date) {
        this.date = date;
    }

    public static void setTotalNumberRaces(int totalNumberRaces) {
        Race.totalNumberRaces = totalNumberRaces;
    }

    public void setRacePositions(String[] racePositions) {
        this.racePositions = racePositions;
    }
    public void setAPosition(String driverName,int positionIndex){
        racePositions[positionIndex]=driverName;
    }

    @Override
    public String toString() {
        String positions = "";
        int position=0;
        //to get position array as a single string
        for(int i=0;i<racePositions.length;i++){
            if(racePositions[i].equals("")) {
                positions += "\n" + (++position) + " position ----> " + "-";
            }else{
                positions += "\n" + (++position) + " position ----> " + racePositions[i].toUpperCase();
            }

        }
        //to update number of points that driver has gained in this season
        return  "Race " + date.toString() +

                "\nPosition : " + positions ;

    }
}
