package com.techelevator;

import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
    private static Scanner in;
    //private PrintWriter out;
    
    public Menu(){
        this.in = new Scanner(System.in);
    }



    public static int runStartMenu(){
        System.out.println("Welcome! Check out our delicious options!");
        System.out.println("1) List of Products");
        System.out.println("2) Purchase");
        System.out.println("3) Exit");
        String userChoice = in.nextLine();
        int stringToInt = Integer.parseInt(userChoice);
        return stringToInt;
        }

    public static int runPurchaseMenu(){
        System.out.println("1) Feed Money");
        System.out.println("2) Make Purchase");
        System.out.println("3) End Transaction");
        String userChoice = in.nextLine();
        int stringToInt = Integer.parseInt(userChoice);
        return stringToInt;
    }

    public static int feedMoney(){ //can we simplify this?
        System.out.println("Please enter money");
        String cashInputString = in.nextLine();
        Double cashInputDouble = Double.parseDouble(cashInputString);
        long cashInputLong = Math.round(cashInputDouble);
        int cashInput = (int)cashInputLong;
        return cashInput;
    }

   public static String runDispenseMenu(){
       System.out.println("Please make a selection");
       System.out.println("1) Return to Purchase Menu");
       String selectionEntered = in.nextLine();
       return selectionEntered.toUpperCase();

   }

    } 

