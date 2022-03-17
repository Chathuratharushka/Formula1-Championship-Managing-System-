package com.company;

import java.io.IOException;

public interface ChampionshipManager {
   //to create a driver who is added in the championship
   void createDriver();

   //to delete the driver from the championship
   void deleteDriver();

   //to change driver's team
   void changeTeam();

   //to display statics of selected driver
   void displayStatistics();

   //to display statistics of all drivers in the championship
   void driverTable();

   //to create race
   void createCompletedRace();

   //to save championship manager
   void saveChampionship() throws IOException;

   //Resumed from the save position
   void recoverChampionship() throws IOException;

   //Creating GUI for formula1 championship Manager
   void formula1GUI();
}
