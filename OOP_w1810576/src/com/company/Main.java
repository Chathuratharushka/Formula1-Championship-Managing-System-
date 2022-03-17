package com.company;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Formula1ChampionshipManager f1 = new Formula1ChampionshipManager();
        String option;
        //printing menu
        System.out.println( "------Welcome!,to the Formula1 Championship Manager----- ");
        System.out.println( "please enter \n" +
                "A  : to add a F1 driver to the championship\n" +
                "D  : to delete a F1 driver from the championship\n"+
                "C  : to change drivers team\n"+
                "DS : to display statistics for specific driver\n"+
                "DT : to display driver table\n" +
                "R  : to create completed race\n"+
                "S  : to save the F1 Championship Manager\n" +
                "L  : to load the F1 Championship Manager\n" +
                "G  : to open Graphical User Interface of F1 Championship Manager\n"+
                "Q  : to Quit");

        option = input.next().toUpperCase();        //to select menu option from the user

        while(!option.equals("Q")){     //checking menu option is valid or not.
            if (option.equals("A")){
                f1.createDriver();
            }else if (option.equals("D")){
                f1.deleteDriver();
            }else if (option.equals("C")){
                f1.changeTeam();
            }else if (option.equals("DS")){
                f1.displayStatistics();
            } else if (option.equals("DT")){
                f1.driverTable();
            }else if (option.equals("R")){
                f1.createCompletedRace();
            }else if (option.equals("S")){
                f1.saveChampionship();
            } else if (option.equals("L")){
                f1.recoverChampionship();
            }else if(option.equals("G")){
                f1.formula1GUI();
            }
            System.out.println( "please enter \n" +
                    "A  : to add a F1 driver to the championship\n" +
                    "D  : to delete a F1 driver from the championship\n"+
                    "C  : to change drivers team\n"+
                    "DS : to display statistics for specific driver\n"+
                    "DT : to display driver table\n" +
                    "R  : to create completed race\n"+
                    "S  : to save the F1 Championship Manager\n" +
                    "L  : to load the F1 Championship Manager\n" +
                    "Q  : to Quit");
            option=input.next().toUpperCase();
        }




    }
}
