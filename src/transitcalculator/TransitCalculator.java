/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transitcalculator;
import java.util.Arrays;

/**
 *
 * @author Asus
 */
public class TransitCalculator {
  static int numberOfDays;
  int individualRides, cityCode;
  String city;
  String[] FareOptionsNames = {"Pay-per-ride","7-day Unlimited Rides", "30-day Unlimited Rides"};
  double[][] regularFarePrices = {{2.75, 2.50}, {33.00, 30.00}, { 127.00, 120.00}} ;
  double[][] reducedFarePrices = {{1.35, 1.00}, {16.50, 15.00}, {63.50, 60.00}};
  double[][] FareOptions = regularFarePrices ;
  int age;
  boolean isDisabled, reduced = false;
  
    public TransitCalculator(String cityName,int daysCount, int rides, int umur, boolean dis){
       
       numberOfDays = daysCount;
       individualRides = rides;
       age = umur;
       isDisabled = dis;
       city = cityName;
       
       switch(city){
           case "New York" : cityCode = 0;
           break;
           case "Mexico City" : cityCode =1;
           break;
           default : System.out.println("No city available!");
       }
       
       if(age >= 65 || isDisabled){
           reduced = true;
           FareOptions = reducedFarePrices ;
       }
    }
    //This method calculates the price per ride for 7-day Unlimited Rides 
    public double unlimited7Price(int cityCode){
       double perRideFee =  Math.ceil((double)numberOfDays / 7) * FareOptions[1][cityCode] / individualRides;
       return perRideFee;
    }
    //This method calculated the price per ride for each available package
    public double[] getRidePrices(){
      double daily = FareOptions[0][cityCode];
      double weekly = unlimited7Price(cityCode);
      double monthly = Math.ceil((double)numberOfDays / 30) * FareOptions[2][cityCode] / individualRides;
      double[] ridePrices = {daily,weekly,monthly};
      return ridePrices;
    }
    //This method gives a suggestion for the cheapest per-ride ticket
    public String getBestFare(){
      int n = getRidePrices().length;
      int index =0;
      double bestfare = getRidePrices()[index];
      for(int i=index+1; i< n;i++){
          if(getRidePrices()[i] < bestfare){
              bestfare = getRidePrices()[i];
              index = i;
          }
      }
      String infoFare = "The fare is not reduced ";
      if(reduced){
          infoFare = "The fare has been reduced ";
      }
       return infoFare + "You should get the " + FareOptionsNames [index] + " option at "+ bestfare + " per ride.";
    }

    
    public static void main(String[] args) {
        // TODO code application logic here
        TransitCalculator t1 = new TransitCalculator("Mexico City",2,5,30,false);
        
        if(numberOfDays > 30){
            System.out.println("Number of days are not compatible");
        }else{
             System.out.println(Arrays.toString(t1.getRidePrices()));
             System.out.println(t1.getBestFare());
       }
        
        
    }
    
}
