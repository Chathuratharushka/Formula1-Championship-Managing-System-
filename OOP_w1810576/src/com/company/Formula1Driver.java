package com.company;

import java.io.Serializable;
import java.util.Arrays;

public  class Formula1Driver extends Driver implements Serializable {
    private String team;
    private int points;
    private int totalRaces;
    private int [] positions=new int[10];

    //constructors
    public Formula1Driver(){
        super();
    }
    public Formula1Driver(String driverName,String driverLocation,String team){
        super(driverName, driverLocation);
        this.team=team;
    }
    public Formula1Driver(String driverName,String driverLocation,String team, int points, int totalRaces,int[] positions){
        super(driverName,driverLocation);
        this.team=team;
        this.points=points;
        this.totalRaces=totalRaces;
        this.positions=positions;
    }
    public Formula1Driver(Formula1Driver driver){
        super(driver.getDriverName(), driver.getDriverLocation());
        this.team=driver.getTeam();
        this.points=driver.getPoints();
        this.totalRaces= driver.getTotalRaces();
        this.positions= driver.getPositions();
    }

    //setters
    public void setTeam(String team) {
        this.team = team;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTotalRaces(int totalRaces) {
        this.totalRaces = totalRaces;
    }

    public void setPositions(int[] positions) {
        this.positions = positions;
    }

    //to calculate the total points that driver has gained in the season
    public void calculatePoints(){
        points=positions[0]*25+positions[1]*18 +positions[2]*15 +positions[3]*12 +positions[4]*10+ positions[5]*8 +positions[6]*6+ positions[7]*4+ positions[8]*2 +positions[9]*1;
    }

    //getters
    public String getTeam() {
        return team;
    }

    public int getPoints() {
        calculatePoints();
        return points;
    }

    public int getTotalRaces() {
        return totalRaces;
    }

    public int[] getPositions() {
        return positions;
    }

    //to print Formula1 drivers all details
    @Override
    public String toString() {
        String position = "";
        //to get position array as a single string
        for(int i=1;i<=positions.length;i++){
            position +="\nDriver's  "+ i +" positions : " +positions[--i];
            i++;
        }
        calculatePoints(); //to update number of points that driver has gained in this season
        return  "Name of the driver : " + super.getDriverName().toUpperCase() +
                "\nLocation of the driver : " + super.getDriverLocation()+
                "\nTeam of the driver : " + team +
                "\nTotal points of the driver : " +  points+
                "\nTotal Number of Races of the driver : " + totalRaces +
                 position;
    }

    public int getElementPosition(int index){
        int value=positions[index];
        return value;
    }

    public void updateRaceDetails(int position){
        int newValuePosition=getElementPosition(position);
        positions[position]=++newValuePosition;
        totalRaces++;
    }

}
