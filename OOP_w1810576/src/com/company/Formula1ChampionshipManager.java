package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager{
    ArrayList<Formula1Driver> formula1DriversList=new ArrayList<>();
    ArrayList<Race> raceDetailsList=new ArrayList<>();
    Scanner input=new Scanner(System.in);

    //to create a driver who is added in the championship
    @Override
    public void createDriver() {
        Formula1Driver tempFormula1Driver;
        String driverName;
        String tName;
        boolean checking=true;

        //getting driver's details from the user
        System.out.println("Now you can create a formula1 driver--");
        System.out.println("Enter the driver name : ");
        driverName=input.next();
        System.out.println("Enter the driver's location : ");
        String driverLocation= input.next();

        //checking formula 1 championship has at least one driver
        if(formula1DriversList.size()==0) {
            System.out.println("Enter the team name for the driver :");
            tName = input.next();
        }else{
            System.out.println("Enter the team name for the driver :");
            tName = input.next();
            //checking team name already exists or not
            checking=checkingTeam(tName);

            //until get valid team name from the user
            while(!checking){
                System.out.println("Enter the team name for the driver :");
                tName = input.next();
                checking=checkingTeam(tName);   //checking again team name already exists or not
            }
        }
        //creating new formula1Driver object
        tempFormula1Driver=new Formula1Driver(driverName,driverLocation,tName);
        formula1DriversList.add(tempFormula1Driver);
        System.out.println("Driver successfully added to the Formula 1 championship.");


    }
    //to check team name is already existed or not
    public boolean checkingTeam(String teamName){
        boolean checking=true;
        //iterating in formula 1 driver list
        for (int i=0;i< formula1DriversList.size();i++){
            //checking teamName is equal to existing drivers' team name
            if(teamName.toUpperCase().equals(formula1DriversList.get(i).getTeam().toUpperCase())){
                System.out.println(teamName+" already has a driver");
                checking=false;
            }
        }
        return checking;
    }

    //to delete the driver from the championship
    @Override
    public void deleteDriver(){
        int driverNameIndex;  //to hold user inputs
        //checking formula 1 championship has at least a driver to delete
        if(formula1DriversList.size()==0){
            System.out.println("No any drivers to delete in the Formula 1 Championship.");
        }else{
                System.out.println("you can delete any driver from Formula 1 Championship");
                printDriversTeams(); //printing all existing drivers name and their team names
                System.out.println("To delete the driver, Select the driver by entering the number that next to the drivers name");

                driverNameIndex=askNumber();  //getting a number from the user.(Can't add strings)

                //checking user entered number is in the range or not
                while (!(driverNameIndex <= formula1DriversList.size() && driverNameIndex >= 1)) {
                    System.out.println("\nThe number you entered is not valid try again.\n");
                    printDriversTeams();
                    System.out.print("To delete the driver, Select the driver by entering the number that next to the drivers name");
                    driverNameIndex = askNumber(); //getting again a number from the user.(Can't add strings)
                }
                driverNameIndex--; //decreasing user entered number by one to use when remove driver from the list

                //assigning driver's name and heir team name to variables
                String tempDriverName=formula1DriversList.get(driverNameIndex).getDriverName();
                String tempTeamName=formula1DriversList.get(driverNameIndex).getTeam();

                formula1DriversList.remove(driverNameIndex);
                System.out.println(tempDriverName.toUpperCase()+" that belonged to "+tempTeamName.toUpperCase()+" successfully deleted from the Formula 1 Championship");

        }
    }
    //to print drivers names and team names
    public void printDriversTeams(){
        System.out.println("---Drivers names and Team names--");
        int number=0;
        for(int i=0;i<formula1DriversList.size();i++){

            System.out.println((++number)+" : "+formula1DriversList.get(i).getDriverName().toUpperCase()+" ----> "+formula1DriversList.get(i).getTeam().toUpperCase());

        }
    }
    //to get a number from the user
    public int askNumber() {
        int number=-1;
        boolean continueInput = true;

        do {
            try{        // checking user entered input has an error or not.
                System.out.print("Enter the number next to the driver's name :");
                number = input.nextInt();
                continueInput = false;
            }
            catch (Exception ex) {      //if there is an error again user has to enter a number
                System.out.println("Error!\nEnter again :");
                input.nextLine();
            }
        }
        while (continueInput); //until user enter a number
        return number;
    }

    //to change driver's team
    @Override
    public void changeTeam(){
        int driverIndex; //to hold user inputs
        String newTeamName; //to hold new team name from the user
        boolean checkingTeamName=true;

        //checking formula 1 championship has at least a driver to change team
        if(formula1DriversList.size()==0){
            System.out.println("No any drivers to change their team in the Formula 1 Championship .");
        }else{

            System.out.println("you can change any team of the driver from below list of Formula 1 Championship");
            printDriversTeams(); // printing all existing drivers' name and their team names
            System.out.println("To change the team, first select the driver by entering the number that next to the driver's name.");
            driverIndex=askNumber();      //getting a number from the user.(Can't add strings)

            //checking user entered number is in the range or not
            while (!(driverIndex <= formula1DriversList.size() && driverIndex >= 1)) {
                System.out.println("\nThe number you entered is not valid try again.\n");
                printDriversTeams();
                System.out.println("To change the team,  first select the driver by entering the number that next to the driver's name.");
                driverIndex=askNumber();
            }
            driverIndex--;
            //assigning drivers details to variable
            String tempDriverName=formula1DriversList.get(driverIndex).getDriverName();
            String tempTeamName=formula1DriversList.get(driverIndex).getTeam();

            System.out.println("\nSuccessfully you selected the "+tempDriverName.toUpperCase()+" that belongs to "+tempTeamName.toUpperCase());

            System.out.print("Enter the new team name for "+tempDriverName.toUpperCase()+" :");
            newTeamName= input.next();  //getting new team name from the user for selected f1 driver.
            //checking team name already exist
            checkingTeamName=checkingTeam(newTeamName);

            //until get valid team name from the user
            while(checkingTeamName){
                System.out.print("Enter the new team name for "+tempDriverName.toUpperCase()+" :");
                newTeamName = input.next();
                checkingTeamName=checkingTeam(newTeamName);
            }

            formula1DriversList.get(driverIndex).setTeam(newTeamName);
            System.out.println(tempDriverName.toUpperCase()+" that belonged to "+tempTeamName.toUpperCase()+" successfully changed to "+newTeamName.toUpperCase()+" team.");

        }
    }

    //to display statics of selected driver
    @Override
    public void displayStatistics(){
        int driverIndex;
        //checking formula 1 championship has at least a driver to change team
        if(formula1DriversList.size()==0){
            System.out.println("No any drivers to view their statistics in the Formula 1 Championship .");
        }else {
            System.out.println("you can view statistics of the driver from below list of Formula 1 Championship");
            printDriversTeams();
            System.out.println("To view the statistics of the driver, first select the driver by entering the number that next to the driver's name.");
            driverIndex = askNumber(); //getting a number from the user.(Can't add strings)

            //checking user entered number is in the range or not
            while (!(driverIndex <= formula1DriversList.size() && driverIndex >= 1)) {
                System.out.println("\nThe number you entered is not valid try again.\n");
                printDriversTeams();
                System.out.println("To view the statistics of the driver, first select the driver by entering the number that next to the driver's name.");
                driverIndex = askNumber();
            }

            driverIndex--;
            String tempDriverName=formula1DriversList.get(driverIndex).getDriverName();
            System.out.println("you have selected "+tempDriverName);
            System.out.println(formula1DriversList.get(driverIndex).toString());
        }
    }

    //to display statistics of all drivers in the championship
    @Override
    public void driverTable(){
        //checking formula 1 championship has at least a driver to display driver table
        if(formula1DriversList.size()==0){
            System.out.println("No any drivers to display drivers table.");
        }else {

            //create new array list to hold sorted driver list according to descending order of their points.
            ArrayList<Formula1Driver> newDriverList=driversDescendingOrderPoints();
            //printing table with positions and points
            System.out.println("+------+---------------------+----------------------+--------+------------+-----------------+------------------+-----------------+");
            System.out.printf("|%5S |%16S     |%15S       |%7S |%11S |%16S |%17S |%16S |", "Rank", "Driver Name", "Team Name", "Points", "Total Race", "First Positions", "Second Positions", "Third Positions");
            System.out.println();
            System.out.println("+------+---------------------+----------------------+--------+------------+-----------------+------------------+-----------------+");
            int rank=1;
            for (int i=0;i< newDriverList.size();i++){
                System.out.printf("| %-5d| %-20S| %-21S| %-7d| %-11d| %-16d| %-17d| %-16d| ",rank++,newDriverList.get(i).getDriverName(),newDriverList.get(i).getTeam(),newDriverList.get(i).getPoints(),newDriverList.get(i).getTotalRaces(),newDriverList.get(i).getElementPosition(0),newDriverList.get(i).getElementPosition(1),newDriverList.get(i).getElementPosition(2));
                System.out.println("");
                System.out.println("+------+---------------------+----------------------+--------+------------+-----------------+------------------+-----------------+");

            }
        }

    }
    //creating a copy of the original formula1DriverList
    public ArrayList<Formula1Driver> createTempDriverList(){
        //new array list to hold drivers
        ArrayList<Formula1Driver> tempList=new ArrayList<>();

        //getting a copy of an original formula 1 array list
        for(int i=0;i<formula1DriversList.size();i++){
            tempList.add(formula1DriversList.get(i));
        }
        return tempList;
    }
    //to get the array list that has sorted according to descending order of drivers' points.
    public ArrayList<Formula1Driver> driversDescendingOrderPoints() {
        //getting a copy of original formula1DriverList to modify the positions
        ArrayList<Formula1Driver> tempList=createTempDriverList();

        //changing positions of list in descending order of points
        for (int i=0;i<tempList.size();i++){
            for (int j=1; j<(tempList.size());j++){
                if(tempList.get(j-1).getPoints() <  tempList.get(j).getPoints()){
                    int k=j+1;
                    Collections.swap(tempList,j-1,k-1 );

                }
                //checking both drivers has same points
                else if(tempList.get(j-1).getPoints()==tempList.get(j).getPoints()){
                    //checking who has won more first position
                    if(tempList.get(j-1).getPositions()[0]<tempList.get(j).getPositions()[0]){
                        int k=j+1;
                        Collections.swap(tempList,j-1,k-1);
                    }

                }
            }
        }
        return tempList;
    }

    //to create race
    @Override
    public void createCompletedRace(){
        //checking formula 1 championship has any drivers.
        if(formula1DriversList.size()==0){
            System.out.println("No any driver to create a completed race.");
        }else {


            //getting date from the user
            int year = 0, month = 0, day = 0,position=0;   //to hold values of years,month and day
            System.out.println("You can create a completed race.");
            boolean continueInput = true;  //checking the iteration

            //getting a year until user enter valid year
            do {
                //checking user entered year has errors or not
                try {
                    System.out.println("Enter the year that race happened : ");
                    year = input.nextInt();
                    if (year <= 2021 && year > 0) {
                        continueInput = false;
                    } else {
                        System.out.println("year that entered is not in the range");
                    }
                }
                //if there is an error
                catch (Exception e) {
                    System.out.println("Error! \nEnter again valid year :");
                    input.nextLine();
                }
            } while (continueInput);


            continueInput = true; //to start again an iteration for month
            //getting a month until user enter valid month.
            do {
                //checking user entered month has errors or not.
                try {
                    System.out.println("Enter the Month that race happened : ");
                    month = input.nextInt();
                    if (month >= 1 && month <= 12) {
                        continueInput = false;
                    } else {
                        System.out.println("Month that entered is not in the range");
                    }
                }
                //if there is an error
                catch (Exception e) {
                    System.out.println("Error! \nEnter again valid month :");
                    input.nextLine();
                }
            } while (continueInput);

            continueInput = true; //to start again an iteration for days
            //getting a day until user enter valid day.
            do {
                //checking user entered the day has errors or not.
                try {
                    System.out.println("Enter the day that race happened : ");
                    day = input.nextInt();
                    //to check which month has 31 days.
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                        //if the selected month has 31,
                        if (day >= 1 && day <= 31) {
                            continueInput = false;
                        } else {
                            System.out.println("Day that entered is not in the range");
                        }
                    }
                    //to check selected month is february.
                    else if (month == 2) {
                        //if selected month is february,it has only 28 days
                        if (day >= 1 && day <= 28) {
                            continueInput = false;
                        } else {
                            System.out.println("Day that entered is not in the range");
                        }
                    }
                    //to check which month has 30 days
                    else {
                        //if selected month has 30 days
                        if (day >= 1 && day <= 30) {
                            continueInput = false;
                        } else {
                            System.out.println("Day that entered is not in the range");
                        }
                    }
                }
                //if there is an error
                catch (Exception e) {
                    System.out.println("Error! \nEnter again valid month :");
                    input.nextLine();
                }
            } while (continueInput);

            Date raceDate = new Date(day, month, year);
            System.out.println(raceDate.toString());

            continueInput=true;
            Race tempRace= new Race(formula1DriversList.size());

            //getting that gained position in the race of each driver in formula 1championship
            for(int i=0; i<formula1DriversList.size();i++){
                continueInput=true;     //to check each input from the user for the driver's position
                do {
                    //checking user entered position of the driver has errors or not.
                    try {
                        System.out.println("Enter the position of " + formula1DriversList.get(i).getDriverName() + " :");
                        position = input.nextInt();
                        //checking position is valid or not.
                        if (position >= 1 && position <= formula1DriversList.size()) {
                            position--;
                            //to check chosen position has a driver or not.
                            if (tempRace.getRacePositions()[position].equals("")) {
                                tempRace.setAPosition(formula1DriversList.get(i).getDriverName(), position);
                                formula1DriversList.get(i).updateRaceDetails(position);
                                continueInput = false;
                            }else{
                                System.out.println("position that entered is already assigned to the driver. Choose a correct position");
                            }
                        } else {
                            System.out.println("Position that entered is not valid. please enter valid position");
                        }
                    }
                    //if there is an error
                    catch (Exception e) {
                        System.out.println("Error! \nEnter again valid position :");
                        input.nextLine();
                    }
                }while(continueInput);
            }

            tempRace.setDate(raceDate);
            raceDetailsList.add(tempRace);
            System.out.println("Successfully added the race details.\n"+tempRace.toString());


        }
    }
    //to save championship manager
    @Override
    public void saveChampionship() throws IOException {
        //From the Deshan's sir lecture
        try {
            // formula1 drivers details file
            File fileDrivers = new File("DriversDetails.txt");

            //output streams for drivers details
            FileOutputStream FODriver = new FileOutputStream(fileDrivers);
            ObjectOutputStream OOdriver = new ObjectOutputStream(FODriver);

            //to write all formula1Drivers in the formula1DriversList into file
            for (int i = 0; i < formula1DriversList.size(); i++) {
                OOdriver.writeObject(formula1DriversList.get(i));
            }

            //closing output streams
            FODriver.close();
            OOdriver.close();

            //race details file
            File fileRaces = new File("RaceDetails.txt");

            //output streams for races details
            FileOutputStream FORaces = new FileOutputStream(fileRaces);
            ObjectOutputStream OORaces = new ObjectOutputStream(FORaces);

            //to write all races details in the raceDetailsList into file
            for (int i = 0; i < raceDetailsList.size(); i++) {
                OORaces.writeObject(raceDetailsList.get(i));
            }
            //closing output streams
            FORaces.close();
            OORaces.close();


        }catch(Exception e){
            System.out.println("");
        }




    }

    //Resumed from the save position

    @Override
    public void recoverChampionship() throws IOException {
        //From the Deshan's sir lecture
        //Input Streams for drivers details
        FileInputStream FISDriver = new FileInputStream("DriversDetails.txt");
        ObjectInputStream OISDriver = new ObjectInputStream(FISDriver);
        while (true) {
            try {
                //creating Formula1driver object from the file.
                Formula1Driver f1Driver = (Formula1Driver) OISDriver.readObject();
                formula1DriversList.add(f1Driver);  //adding  Formula1driver object into list
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }

        //Input Streams for Race details
        FileInputStream FISRace = new FileInputStream("RaceDetails.txt");
        ObjectInputStream OISRace = new ObjectInputStream(FISRace);
        while (true) {
            try {
                //creating race object from the RacedDetails file
                Race race = (Race) OISRace.readObject();
                raceDetailsList.add(race);
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }

    }


    //Creating GUI for formula1 championship Manager
    @Override
    public void formula1GUI(){
        //creating a JTable to display the drivers details
        JTable driverTable=new JTable();
        //creating columns name for each column for driverTable
        Object [] columnName={"Rank", "Driver Name","Team Name","Points","Total Race","First Position","Second Position","Third Position"};
        //creating defaultTableModel for driver table
        DefaultTableModel model=new DefaultTableModel();   //https://www.youtube.com/watch?v=pybU3E-eKfw

        //creating new JFrame to display all components
        JFrame formula1Frame=new JFrame("GUI of Formula 1 Championship Manager");
        formula1Frame.setBounds(0,0,1200,720);



        //removing formula1Frame's layout manager
        formula1Frame.getContentPane().setLayout(null);
        //centering the GUI into the screen
        formula1Frame.setLocationRelativeTo(null);
        //setting the columns names for the model
        model.setColumnIdentifiers(columnName);
        //setting model to the driverTable
        driverTable.setModel(model);
        driverTable.setRowHeight(20);
        driverTable.setAutoCreateRowSorter(true);

        //creating a new scroll pane for the driverTable
        JScrollPane pane=new JScrollPane(driverTable);
        pane.setBounds(100,50,1000,200);
        formula1Frame.getContentPane().add(pane);  //adding the scroll pane to the frame

        //creating new object array to hold driverTable's records
        Object[] rowData=new Object[8];
        int position=1;

        //creating new Formula1Driver array list called descendingList according to descending order of the drivers' points.
        ArrayList<Formula1Driver> descendingList=driversDescendingOrderPoints();

        //displaying all drivers in descendingList into driver table
        for (int i=0;i<descendingList.size();i++){
            //inserting drivers' details to rowData object.
            rowData[0]=position++;
            rowData[1]=descendingList.get(i).getDriverName().toUpperCase();
            rowData[2]=descendingList.get(i).getTeam().toUpperCase();
            rowData[3]=descendingList.get(i).getPoints();
            rowData[4]=descendingList.get(i).getTotalRaces();
            rowData[5]=descendingList.get(i).getPositions()[0];
            rowData[6]=descendingList.get(i).getPositions()[1];
            rowData[7]=descendingList.get(i).getPositions()[2];

            model.addRow(rowData);  //adding each record to the driver table
        }

        //creating a new JButton to display drivers details according points of the drivers' ascending order.
        JButton pointsAscOrder=new JButton("Points Ascending Order");
        pointsAscOrder.setBounds(20, 280,175,35);
        formula1Frame.getContentPane().add(pointsAscOrder);     //adding button to the formula1Frame.
        //adding the new action to the pointsAscOrder button.
        pointsAscOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creating new Formula1Driver array list called ascendingList according to descending order of the drivers' points.
                ArrayList<Formula1Driver> ascendingList=driversAscendingOrderPoints();
                model.setRowCount(0); //removing all the rows in driverTable.
                int position=1;
                for (int i=0;i<ascendingList.size();i++){
                    //inserting drivers' details to rowData object.
                    rowData[0]=position++;
                    rowData[1]=ascendingList.get(i).getDriverName().toUpperCase();
                    rowData[2]=ascendingList.get(i).getTeam().toUpperCase();
                    rowData[3]=ascendingList.get(i).getPoints();
                    rowData[4]=ascendingList.get(i).getTotalRaces();
                    rowData[5]=ascendingList.get(i).getPositions()[0];
                    rowData[6]=ascendingList.get(i).getPositions()[1];
                    rowData[7]=ascendingList.get(i).getPositions()[2];

                    model.addRow(rowData); //adding rowData to driver table
                }
            }
        });
        //creating a new JButton to display drivers details according first positions of the drivers' in descending order.
        JButton positionButton=new JButton("Position Descending Order");
        positionButton.setBounds(235,280,200,35);
        formula1Frame.getContentPane().add(positionButton);  //adding the positionButton to the frame

        positionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creating new Formula1Driver array list called firstPositionDesc according to descending order of the drivers' first positions.
                ArrayList<Formula1Driver> firstPositionDesc=driverDescendingFirstPositions();
                model.setRowCount(0); //removing all the existing data from the driver table
                int position=1;
                for (int i=0;i<firstPositionDesc.size();i++){
                    //inserting drivers' details to rowData object.
                    rowData[0]=position++;
                    rowData[1]=firstPositionDesc.get(i).getDriverName().toUpperCase();
                    rowData[2]=firstPositionDesc.get(i).getTeam().toUpperCase();
                    rowData[3]=firstPositionDesc.get(i).getPoints();
                    rowData[4]=firstPositionDesc.get(i).getTotalRaces();
                    rowData[5]=firstPositionDesc.get(i).getPositions()[0];
                    rowData[6]=firstPositionDesc.get(i).getPositions()[1];
                    rowData[7]=firstPositionDesc.get(i).getPositions()[2];

                    model.addRow(rowData);  //adding rowData to driver table

                }


            }
        });

        //creating new JButton to generate random race
        JButton randomRace=new JButton("Random Generate Race");
        randomRace.setBounds(480,280,200,35);
        formula1Frame.getContentPane().add(randomRace);   //adding randomRace button to formula1frame


        //Creating new JTable to Display details of races
        JTable raceTable=new JTable();
        //creating columns name for each column for raceTable
        Object [] raceColumnName={"Race Date","1st Position","2nd Position","3rd Position"};
        //creating new defaultTableModel for raceTable
        DefaultTableModel raceModel=new DefaultTableModel();



        //setting the columns names for the raceModel
        raceModel.setColumnIdentifiers(raceColumnName);
        //setting raceModels into raceTable
        raceTable.setModel(raceModel);
        raceTable.setRowHeight(30);
        raceTable.setAutoCreateRowSorter(true);

        //creating new JScrollPane for raceTable
        JScrollPane racePane=new JScrollPane(raceTable);
        racePane.setBounds(100,430,1000,240);
        formula1Frame.getContentPane().add(racePane);  //adding racePane into formula1Frame.


        //adding new action to randomRace button
        randomRace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                raceModel.setRowCount(0);  //removing all the record in raceTable
                //creating new random race and assigned to race object
                Race randomTempRace=autoGenerateRace();

                //new array to hold raceTable records
                Object [] raceRecordDetails=new Object[4];

                //inserting necessary details of random race to raceRecordDetails array
                raceRecordDetails[0]=randomTempRace.getDate().toString();
                raceRecordDetails[1]=randomTempRace.getRacePositions()[0].toUpperCase();
                raceRecordDetails[2]=randomTempRace.getRacePositions()[1].toUpperCase();
                raceRecordDetails[3]=randomTempRace.getRacePositions()[2].toUpperCase();

                raceModel.addRow(raceRecordDetails); //removing all the record in raceTable


                model.setRowCount(0);  //removing all records from the driverTable

                //display new driver table's records according new random race
                for(int i=0;i<formula1DriversList.size();i++){
                    //inserting necessary details of the drivers to rowData array.
                    rowData[0]="-";
                    rowData[1]=formula1DriversList.get(i).getDriverName().toUpperCase();
                    rowData[2]=formula1DriversList.get(i).getTeam().toUpperCase();
                    rowData[3]=formula1DriversList.get(i).getPoints();
                    rowData[4]=formula1DriversList.get(i).getTotalRaces();
                    rowData[5]=formula1DriversList.get(i).getPositions()[0];
                    rowData[6]=formula1DriversList.get(i).getPositions()[1];
                    rowData[7]=formula1DriversList.get(i).getPositions()[2];

                    model.addRow(rowData); //adding to the driverTable
                }

            }

        });
        //creating new JButton to generate random race with probabilities
        JButton randomProbRace=new JButton("Random Probability Race");
        randomProbRace.setBounds(710,280,200,35);
        formula1Frame.getContentPane().add(randomProbRace);  //adding randomProbRace into formula1Frame

        randomProbRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //creating a new JButton to display race details according to ascending order of each race happened date.
        JButton raceDetailsAsce=new JButton("Ascending Order Of Race Details");
        raceDetailsAsce.setBounds(935,280,220,35);
        formula1Frame.getContentPane().add(raceDetailsAsce);  //adding raceDetailsAsce button to formula1Frame

        //creating new action listener for raceDetailsAsce button
        raceDetailsAsce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creating new Race array list called ascendingRaceDetails according to ascending order of races' dates.
                ArrayList<Race> ascendingRaceDetails=ascendingRace();

                //new object array to hold necessary race details
                Object [] raceRecords=new Object[4];

                raceModel.setRowCount(0);  //removing all records from the raceTable

                //displaying raceTable records
                for(int i=0;i<ascendingRaceDetails.size();i++){
                    //inserting necessary race details into raceRecords array.
                    raceRecords[0]=ascendingRaceDetails.get(i).getDate().toString();
                    raceRecords[1]=ascendingRaceDetails.get(i).getRacePositions()[0].toUpperCase();
                    raceRecords[2]=ascendingRaceDetails.get(i).getRacePositions()[1].toUpperCase();
                    raceRecords[3]=ascendingRaceDetails.get(i).getRacePositions()[2].toUpperCase();
                    raceModel.addRow(raceRecords);

                }

            }
        });
        //creating new JLabel for instructions of the date input
        JLabel instruction=new JLabel("Please enter the date as this format 'day/month/year'.");
        instruction.setBounds(280,335,400,30);
        formula1Frame.getContentPane().add(instruction);

        //creating new JTextField to get date from the user
        JTextField dateTextField =new JTextField();
        dateTextField.setBounds(350,360,150,30);
        formula1Frame.getContentPane().add(dateTextField);  //adding textField to formula1Frame

        //adding new label to describe dateTextField
        JLabel dateInputLabel= new JLabel("Please enter the date :");
        dateInputLabel.setBounds(210,360,130,30);
        formula1Frame.getContentPane().add(dateInputLabel); //adding the label to formula1Frame.

        //creating new JButton to search race details from the date
        JButton searchRaces=new JButton("Search The Races ");
        searchRaces.setBounds(530,360,150,30);
        formula1Frame.getContentPane().add(searchRaces);

        //adding new action listener to searchRaces button
        searchRaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //assigned user entered date in string variable
                String userEnteredDate=dateTextField.getText();
                raceModel.setRowCount(0); //removing all records from raceTable


                for(int i=0;i<raceDetailsList.size();i++){
                    //checking is there any race which happened on user entered date.
                    if(userEnteredDate.equals(raceDetailsList.get(i).getDate().toString())){

                        Object [] raceRecords=new Object[4];  //to hold race details
                        //inserting race details to raceRecords array
                        raceRecords[0]=raceDetailsList.get(i).getDate().toString();
                        raceRecords[1]=raceDetailsList.get(i).getRacePositions()[0].toUpperCase();
                        raceRecords[2]=raceDetailsList.get(i).getRacePositions()[1].toUpperCase();
                        raceRecords[3]=raceDetailsList.get(i).getRacePositions()[2].toUpperCase();
                        raceModel.addRow(raceRecords);
                    }
                }
                dateTextField.setText(""); //removing user entered text from the text field
            }
        });
        formula1Frame.setVisible(true);
        formula1Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //to get the array list that has been sorted according to ascending order of drivers' points.
    public ArrayList<Formula1Driver> driversAscendingOrderPoints(){
        ArrayList<Formula1Driver> newTempList=createTempDriverList();

        //changing positions of list in ascending order of points
        for (int i=0;i<newTempList.size();i++){
            for (int j=1; j<(newTempList.size());j++){
                if(newTempList.get(j-1).getPoints() >  newTempList.get(j).getPoints()){
                    int k=j+1;
                    Collections.swap(newTempList,j-1,k-1 );

                }
            }
        }
        return newTempList;
    }
    //to get the array list that has been sorted according to descending order of drivers' first positions.
    public ArrayList<Formula1Driver> driverDescendingFirstPositions(){
        ArrayList<Formula1Driver> firstPositionList=createTempDriverList();

        //changing positions of list in descending order of first positions
        for (int i=0;i<firstPositionList.size();i++){
            for (int j=1; j<(firstPositionList.size());j++){
                if(firstPositionList.get(j-1).getPositions()[0] <  firstPositionList.get(j).getPositions()[0]){
                    int k=j+1;
                    Collections.swap(firstPositionList,j-1,k-1 );

                }
            }
        }
        return firstPositionList;
    }

    //to create new auto generate race object
    public Race autoGenerateRace(){
        int autoYear;
        int autoMonth;
        int autoDay;
        Date autoDate;

        Random random=new Random();

        //creating new random year between 2011 and 2021
        autoYear=2011+random.nextInt(10);
        //creating new random month
        autoMonth= 1+random.nextInt(12);
        //checking randomMoth has 31 day
        if(autoMonth == 1 || autoMonth == 3 || autoMonth == 5 || autoMonth == 7 || autoMonth == 8 || autoMonth == 10 || autoMonth == 12){
            autoDay=1+random.nextInt(31);
        }else if(autoMonth==2){    //checking randomMonth is equal to february
            autoDay=1+ random.nextInt(28);
        }else{
            autoDay=1+ random.nextInt(30);
        }
        //creating new date object according new random year,month and date
        autoDate=new Date(autoDay,autoMonth,autoYear);

        //creating new race object
        Race autoRace= new Race(formula1DriversList.size());
        int position;

        for (int i=0; i<formula1DriversList.size();i++){
            boolean positionCheck=true;
            do {
                position = random.nextInt(formula1DriversList.size());
                //checking position has not selected for the driver
                if (autoRace.getRacePositions()[position].equals("")) {
                    //setting the driver name for random position
                    autoRace.setAPosition(formula1DriversList.get(i).getDriverName(),position);
                    formula1DriversList.get(i).updateRaceDetails(position);
                    positionCheck=false;
                }
            }while (positionCheck);  //until valid random position for the driver
        }
        autoRace.setDate(autoDate); //set random race to new auto generated race
        raceDetailsList.add(autoRace);  //adding the race into raceDetailsList
        return autoRace;
    }

    //to create random race according to probabilities
    public Race autoProbRandomRace(){
        int autoYear;
        int autoMonth;
        int autoDay;
        Date autoDate;

        Random random=new Random();

        //creating new random year between 2011 and 2021
        autoYear=2011+random.nextInt(10);
        //creating new random month
        autoMonth= 1+random.nextInt(12);
        //checking randomMoth has 31 day
        if(autoMonth == 1 || autoMonth == 3 || autoMonth == 5 || autoMonth == 7 || autoMonth == 8 || autoMonth == 10 || autoMonth == 12){
            autoDay=1+random.nextInt(31);
        }else if(autoMonth==2){    //checking randomMonth is equal to february
            autoDay=1+ random.nextInt(28);
        }else{
            autoDay=1+ random.nextInt(30);
        }
        //creating new date object according new random year,month and date
        autoDate=new Date(autoDay,autoMonth,autoYear);

        //creating new race object
        Race autoRace= new Race(formula1DriversList.size());
        //creating new formula1DriverList
        ArrayList<Formula1Driver> raceStartingList=createTempDriverList();

        //changing positions
        Collections.shuffle(raceStartingList);
        int position;


        return autoRace;


    }

    //to get new array list according ascending order of the race dates
    public ArrayList<Race> ascendingRace(){
        //creating new temporary Race arrayList change their position according ascending order of the race dates
        ArrayList<Race> tempRaceList =new ArrayList<>();
        //inserting all the races in raceDetailsList into tempRaceList
        for (int i=0; i< raceDetailsList.size();i++){
            tempRaceList.add(raceDetailsList.get(i));

        }

        //changing arrayList positions according ascending order of race dates
        for(int i=0;i<tempRaceList.size();i++){
            for(int j=1;j<tempRaceList.size();j++){
                if(tempRaceList.get(j-1).getDate().getYear() > tempRaceList.get(j).getDate().getYear()){
                    int k=j+1;
                    Collections.swap(tempRaceList,j-1,k-1);
                }else if(tempRaceList.get(j-1).getDate().getYear() == tempRaceList.get(j).getDate().getYear()){
                    if(tempRaceList.get(j-1).getDate().getMonth() > tempRaceList.get(j).getDate().getMonth()){
                        int k=j+1;
                        Collections.swap(tempRaceList,j-1,k-1);
                    }else if(tempRaceList.get(j-1).getDate().getMonth() == tempRaceList.get(j).getDate().getMonth()){
                        if(tempRaceList.get(j-1).getDate().getDay() > tempRaceList.get(j).getDate().getDay()){
                            int k=j+1;
                            Collections.swap(tempRaceList,j-1,k-1);
                        }
                    }
                }
            }
        }

        return tempRaceList;
    }




}

