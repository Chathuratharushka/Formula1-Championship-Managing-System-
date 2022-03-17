package com.company;

import java.io.Serializable;

public abstract class Driver implements Serializable {
   private String driverName;
   private String driverLocation;

   //constructors
   public Driver(){
      this.driverName="";
      this.driverLocation="";

   }
   public Driver(String driverName, String driverLocation){
      this.driverName=driverName;
      this.driverLocation=driverLocation;

   }
   //setters

   public void setDriverName(String driverName) {
      this.driverName = driverName;
   }

   public void setDriverLocation(String driverLocation) {
      this.driverLocation = driverLocation;
   }



   //getters


   public String getDriverName() {
      return driverName;
   }

   public String getDriverLocation() {
      return driverLocation;
   }



   @Override
   public String toString() {
      return "Name of the driver " + driverName +
              "\nLocation of the driver " + driverLocation  ;
   }
}
